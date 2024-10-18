package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Project;
import service.ProjectService;


public class ProjectListController extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService(); // Khởi tạo ProjectService
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath(); // Lấy action từ URL

        try {
            switch (action) {
                case "/projectlist":
                    listProjects(request, response); // Hiển thị danh sách dự án
                    break;
                case "/add-project":
                    projectDetail(request, response); // Hiển thị form thêm mới dự án
                    break;
                default:
                    listProjects(request, response); // Mặc định hiển thị danh sách dự án
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Project> projects = projectService.getAllProjects(); // Lấy danh sách dự án
        request.setAttribute("listProjects", projects); // Lưu danh sách vào request
        request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response); // Chuyển tiếp đến JSP
    }

    private void projectDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/member/project-detail.jsp").forward(request, response); // Chuyển đến trang thêm dự án
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Xử lý request
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Xử lý request
    }

    @Override
    public String getServletInfo() {
        return "Project List Controller";
    }
}
