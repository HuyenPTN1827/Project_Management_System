/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
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

        try {
            switch (action) {
                case "/add-issue" ->
                    showNewForm(request, response); // Show form insert issue
                case "/insert-issue" ->
                    insertIssue(request, response); // Insert issue
                case "/edit-issue" ->
                    showEditForm(request, response); // Show form edit issue
                case "/update-issue" ->
                    updateIssue(request, response); // Update issue 
                default -> {
                    listIssue(request, response); // List of issues
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void listIssue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
        String typeStr = request.getParameter("type");
        String projectStr = request.getParameter("projectId");
        String milestoneStr = request.getParameter("milestone");
//        String scopeStr = request.getParameter("scope");
        String assigneeStr = request.getParameter("assignee");
        String assignerStr = request.getParameter("assigner");
        String statusStr = request.getParameter("status");
        String keyword = request.getParameter("keyword");

        // Process the filter value, convert to number or null if not selected
        Integer type = typeStr != null && !typeStr.isEmpty() ? Integer.valueOf(typeStr) : null;
        Integer project = projectStr != null && !projectStr.isEmpty() ? Integer.valueOf(projectStr) : null;
        Integer milestone = milestoneStr != null && !milestoneStr.isEmpty() ? Integer.valueOf(milestoneStr) : null;
//        Integer scope = scopeStr != null && !scopeStr.isEmpty() ? Integer.valueOf(scopeStr) : null;
        Integer assignee = assigneeStr != null && !assigneeStr.isEmpty() ? Integer.valueOf(assigneeStr) : null;
        Integer assigner = assignerStr != null && !assignerStr.isEmpty() ? Integer.valueOf(assignerStr) : null;
        Integer status = statusStr != null && !statusStr.isEmpty() ? Integer.valueOf(statusStr) : null;

        List<Issue> listIssue = issueService.getAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        List<Setting> listType = settingService.getIssueTypeList();
        List<Project> listPj = pjService.getAllProjects();
        List<Milestone> listMilestone = pjService.getMilestonesByProjectId(project);
//        List<WorkPackage> listScope = scopeService.getWorkPackageByProjectId(userId, project);
        List<User> listAssignee = issueService.getMemberListByProjectId(project);
        List<User> listAssigner = issueService.getMemberListByProjectId(project);

        request.setAttribute("listIssue", listIssue);
        request.setAttribute("listType", listType);
        request.setAttribute("listPj", listPj);
        request.setAttribute("listMilestone", listMilestone);
//        request.setAttribute("listScope", listScope);
        request.setAttribute("listAssignee", listAssignee);
        request.setAttribute("listAssigner", listAssigner);
        request.setAttribute("type", type);
        request.setAttribute("projectId", project);
        request.setAttribute("milestone", milestone);
//        request.setAttribute("scope", scope);
        request.setAttribute("assignee", assignee);
        request.setAttribute("assigner", assigner);
        request.setAttribute("status", status);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/issue-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("userId");
        if (user != null && !user.isEmpty()) {
            int userId = Integer.parseInt(user);
            String projectStr = request.getParameter("projectId");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String deadline = request.getParameter("deadline");
            String statusStr = request.getParameter("status");
            String description = request.getParameter("description");

            Integer project = projectStr != null && !projectStr.isEmpty() ? Integer.valueOf(projectStr) : null;
            Integer typeId = type != null && !type.isEmpty() ? Integer.valueOf(type) : null;
            Integer status = statusStr != null && !statusStr.isEmpty() ? Integer.valueOf(statusStr) : null;

            List<Setting> listType = settingService.getIssueTypeList();
            List<Project> listPj = pjService.getProjectListByUserID(userId, null);
            List<Project> listAllPj = pjService.getAllProjects();
            List<Milestone> listMilestone = pjService.getMilestonesByProjectId(project);
            List<User> listAssignee = issueService.getMemberListByProjectId(project);

            request.setAttribute("userId", userId);
            request.setAttribute("projectId", project);
            request.setAttribute("name", name);
            request.setAttribute("type", typeId);
            request.setAttribute("deadline", deadline);
            request.setAttribute("status", status);
            request.setAttribute("description", description);
            request.setAttribute("listType", listType);
            request.setAttribute("listPj", listPj);
            request.setAttribute("listAllPj", listAllPj);
            request.setAttribute("listMilestone", listMilestone);
            request.setAttribute("listAssignee", listAssignee);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/issue-detail.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("logout");
        }
    }

    private void insertIssue(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String user = request.getParameter("userId");
        if (user != null && !user.isEmpty()) {
            int userId = Integer.parseInt(user);
            String name = request.getParameter("name");
            int type = Integer.parseInt(request.getParameter("type"));
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            int milestone = Integer.parseInt(request.getParameter("milestone"));
            int assignee = Integer.parseInt(request.getParameter("assignee"));
            LocalDate deadline = LocalDate.parse(request.getParameter("deadline"));
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");

            Issue i = new Issue();
            i.setName(name);
            i.setDeadline(deadline);
            i.setStatus(status);
            i.setDetails(description);

            User u1 = new User();
            u1.setId(userId);
            i.setCreated_by(u1);

            Setting s = new Setting();
            s.setId(type);
            i.setType(s);

            Project p = new Project();
            p.setId(projectId);
            i.setProject(p);

            Milestone m = new Milestone();
            m.setId(milestone);
            i.setMilestone(m);

            User u2 = new User();
            u2.setId(assignee);
            i.setAssignee(u2);

            // Validate dates
            List<String> errors = issueService.validateDeadline(i);

            if (errors.isEmpty()) {
                issueService.insertIssue(i);
                response.sendRedirect("issue-management?projectId=" + projectId);
            } else {
                request.setAttribute("errorMessages", errors);
//            request.setAttribute("userId", userId);
//            request.setAttribute("name", name);
//            request.setAttribute("type", type);
//            request.setAttribute("projectId", projectId);
            request.setAttribute("milestone", milestone);
            request.setAttribute("assignee", assignee);
//            request.setAttribute("deadline", deadline);
//            request.setAttribute("description", description);
                showNewForm(request, response);
            }
        } else {
            response.sendRedirect("logout");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String user = request.getParameter("userId");
        if (user != null && !user.isEmpty()) {
            int userId = Integer.parseInt(user);
            int id = Integer.parseInt(request.getParameter("id"));
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            Issue issue = issueService.getIssueById(id);
            List<Setting> listType = settingService.getIssueTypeList();
            List<Project> listPj = pjService.getProjectListByUserID(userId, null);
            List<Milestone> listMilestone = pjService.getMilestonesByProjectId(projectId);
            List<User> listAssignee = issueService.getMemberListByProjectId(projectId);

            request.setAttribute("action", action);
            request.setAttribute("projectId", projectId);
            request.setAttribute("issue", issue);
            request.setAttribute("listType", listType);
            request.setAttribute("listPj", listPj);
            request.setAttribute("listMilestone", listMilestone);
            request.setAttribute("listAssignee", listAssignee);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/issue-detail.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("logout");
        }
    }

    private void updateIssue(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String user = request.getParameter("userId");
        if (user != null && !user.isEmpty()) {
            int userId = Integer.parseInt(user);
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int type = Integer.parseInt(request.getParameter("type"));
            int project = Integer.parseInt(request.getParameter("project"));
            int milestone = Integer.parseInt(request.getParameter("milestone"));
            int assignee = Integer.parseInt(request.getParameter("assignee"));
            LocalDate deadline = LocalDate.parse(request.getParameter("deadline"));
            String description = request.getParameter("description");
            int status = Integer.parseInt(request.getParameter("status"));

            Issue i = new Issue();
            i.setId(id);
            i.setName(name);
            i.setDeadline(deadline);
            i.setDetails(description);
            i.setStatus(status);

            Setting s = new Setting();
            s.setId(type);
            i.setType(s);

            Project p = new Project();
            p.setId(project);
            i.setProject(p);

            Milestone m = new Milestone();
            m.setId(milestone);
            i.setMilestone(m);

            User u = new User();
            u.setId(assignee);
            i.setAssignee(u);

            issueService.updateIssue(i);
            response.sendRedirect("issue-management?projectId=" + project);
        } else {
            response.sendRedirect("logout");
        }
    }
}
