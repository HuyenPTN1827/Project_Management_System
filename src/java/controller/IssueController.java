/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Issue;
import model.Milestone;
import model.Project;
import model.Setting;
import model.User;
import model.WorkPackage;
import service.IssueService;
import service.ProjectService;
import service.SettingService;
import service.WorkPackageService;

/**
 *
 * @author HuyenPTNHE160769
 */
public class IssueController extends HttpServlet {

    private IssueService issueService;
    private SettingService settingService;
    private ProjectService pjService;
    private WorkPackageService scopeService;

    @Override
    public void init() throws ServletException {
        this.issueService = new IssueService();
        this.settingService = new SettingService();
        this.pjService = new ProjectService();
        this.scopeService = new WorkPackageService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

//        try {
        switch (action) {
            case "/add-issue" ->
                showNewForm(request, response); // Show form insert issue
            case "/insert-issue" ->
                insertIssue(request, response); // Insert issue
            case "/edit-issue" ->
                showEditForm(request, response); // Show form edit issue
            case "/update-issue" ->
                updateIssue(request, response); // Update issue
            case "/change-status-issue" ->
                changeStatusIssue(request, response); // Change status issue
            default -> {
                listIssue(request, response); // List of issues
            }
        }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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

    private void listIssue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String typeStr = request.getParameter("type");
        String projectStr = request.getParameter("projectId");
        String milestoneStr = request.getParameter("milestone");
        String scopeStr = request.getParameter("scope");
        String assigneeStr = request.getParameter("assignee");
        String statusStr = request.getParameter("status");
        String keyword = request.getParameter("keyword");

        // Process the filter value, convert to number or null if not selected
        Integer type = typeStr != null && !typeStr.isEmpty() ? Integer.valueOf(typeStr) : null;
        Integer project = projectStr != null && !projectStr.isEmpty() ? Integer.valueOf(projectStr) : null;
        Integer milestone = milestoneStr != null && !milestoneStr.isEmpty() ? Integer.valueOf(milestoneStr) : null;
        Integer scope = scopeStr != null && !scopeStr.isEmpty() ? Integer.valueOf(scopeStr) : null;
        Integer assignee = assigneeStr != null && !assigneeStr.isEmpty() ? Integer.valueOf(assigneeStr) : null;
        Integer status = statusStr != null && !statusStr.isEmpty() ? Integer.valueOf(statusStr) : null;

        List<Issue> listIssue = issueService.getAllIssues(userId, keyword, project, type, milestone, scope, assignee, status);
        List<Setting> listType = settingService.getIssueTypeList();
        List<Project> listPj = pjService.getProjectListByUserID(userId);
        List<Milestone> listMilestone = pjService.getMilestonesByProjectId(userId, project);
        List<WorkPackage> listScope = scopeService.getWorkPackageByProjectId(userId, project);
        List<User> listAssignee = issueService.getAssigneeListByProjectId(userId, project);

        request.setAttribute("listIssue", listIssue);
        request.setAttribute("listType", listType);
        request.setAttribute("listPj", listPj);
        request.setAttribute("listMilestone", listMilestone);
        request.setAttribute("listScope", listScope);
        request.setAttribute("listAssignee", listAssignee);
        request.setAttribute("type", type);
        request.setAttribute("projectId", project);
        request.setAttribute("milestone", milestone);
        request.setAttribute("scope", scope);
        request.setAttribute("assignee", assignee);
        request.setAttribute("status", status);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/issue-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void insertIssue(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void updateIssue(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void changeStatusIssue(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
