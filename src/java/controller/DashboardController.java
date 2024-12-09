/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Department;
import model.Issue;
import model.Project;
import model.Setting;
import model.User;
import service.DepartmentService;
import service.IssueService;
import service.ProjectService;
import service.SettingService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/member-dashboard"})
public class DashboardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //BachHD
    //28/9
    // xử lý yêu cầu truy cập trang dashboard của người dùng
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không có session

        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                // Chuyển đến trang dashboard.jsp
                String deptIdStr = request.getParameter("deptId");
                String bizTermStr = request.getParameter("bizTerm");

                Integer deptId = deptIdStr != null && !deptIdStr.isEmpty() ? Integer.valueOf(deptIdStr) : null;
                Integer bizTerm = bizTermStr != null && !bizTermStr.isEmpty() ? Integer.valueOf(bizTermStr) : null;

                DepartmentService deptService = new DepartmentService();
                List<Department> dept = deptService.getAllActiveDepartments();
                SettingService settingService = new SettingService();
                List<Setting> setting = settingService.getBizTermsList();

                issueChart(request, response, deptId, bizTerm);
                projectList(request, response, deptId, bizTerm, user);
                issueList(request, response);

//                request.setAttribute("issues", issues);
                request.setAttribute("deptId", deptId);
                request.setAttribute("bizTerm", bizTerm);
                request.setAttribute("dept", dept);
                request.setAttribute("setting", setting);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/dashboard.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu không có người dùng, chuyển đến trang đăng nhập
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            // Nếu không có session, chuyển đến trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void issueList(HttpServletRequest request, HttpServletResponse response) {
        IssueService issueService = new IssueService();
        List<Issue> listIssue = issueService.get10LastestIssues();
        request.setAttribute("listIssue", listIssue);
    }

    private void issueChart(HttpServletRequest request, HttpServletResponse response, Integer deptId, Integer bizTerm) {
        IssueService issueService = new IssueService();
        List<Issue> issues = issueService.countIssues(deptId, bizTerm);
        List<String> labels = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        for (Issue issue : issues) {
            labels.add("" + issue.getStatus());
            data.add(issue.getCount());
        }

        request.setAttribute("issues", issues);
        request.setAttribute("labels", labels);
        request.setAttribute("data", data);
    }

    private void projectList(HttpServletRequest request, HttpServletResponse response, Integer deptId, Integer bizTerm, User user) {
        ProjectService pjService = new ProjectService();
        List<Project> project = pjService.getProjectListByUserID(user.getId(), bizTerm);
        request.setAttribute("project", project);
    }

}
