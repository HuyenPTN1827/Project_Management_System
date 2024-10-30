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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProjectType;
import model.ProjectTypeSetting;
import model.ProjectType_User;
import model.User;
import service.GroupService;
import service.ProjectTypeSettingService;
import service.UserService;

/**
 *
 * @author kelma
 */
public class ProjectTypeController extends HttpServlet {

    private GroupService groupService;
    private ProjectTypeSettingService ptSettingService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.groupService = new GroupService();
        this.ptSettingService = new ProjectTypeSettingService();
        this.userService = new UserService();
    }

//    HuyenPTNHE160769 
//    13/10/2024 
//    Project type management controller by Admin
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-project-type" ->
                    showNewForm(request, response); // Show form insert project type
                case "/insert-project-type" ->
                    insertProjectType(request, response); // Insert project type
                case "/edit-project-type" ->
                    showEditForm(request, response); // Show form edit project type
                case "/update-project-type" ->
                    updateProjectType(request, response); // Update project type
                case "/change-status-project-type" ->
                    changeStatusProjectType(request, response); // Change status project type
                case "/project-type-config" ->
                    listProjectTypeConfig(request, response); // List of project type configs
                case "/add-project-type-user" ->
                    showNewFormPTUser(request, response); // Show form insert project type users
                case "/insert-project-type-user" ->
                    insertPTUser(request, response); // Insert project type user
                case "/edit-project-type-user" ->
                    showEditFormPTUser(request, response); // Show form edit project type user
                case "/update-project-type-user" ->
                    updatePTUser(request, response); // Update project type user
                case "/change-status-project-type-user" ->
                    changeStatusPTUser(request, response); // Change status project type user
                default -> {
                    listProjectType(request, response); // List of project types
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

//    HuyenPTNHE160769 
//    13/10/2024 
//    Show list of Project types
    private void listProjectType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String statusStr = request.getParameter("status");

        // Process the filter value, convert to number or null if not selected
        Boolean status = statusStr != null && !statusStr.isEmpty() ? Boolean.valueOf(statusStr) : null;

        // Send search results and list depts, roles to JSP page
        List<ProjectType> listType = groupService.getAllProjectTypes(keyword, status);

        // Path to user list page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-list.jsp");
//        if (listDept.isEmpty()) {
//            request.setAttribute("message", "No results found!");
//        }
        request.setAttribute("listType", listType);
        request.setAttribute("keyword", keyword);
        request.setAttribute("status", status);
        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Show form insert project type
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("projectType", null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-detail.jsp");
//        dispatcher.forward(request, response);
        dispatcher.include(request, response);
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Insert project type
    private void insertProjectType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String details = request.getParameter("details");

        ProjectType pt = new ProjectType();
        pt.setName(name);
        pt.setCode(code);
        pt.setDetails(details);

        groupService.insertProjectType(pt);
        response.sendRedirect("project-type-management");
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Show form edit project type
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectType projectType = groupService.getProjectTypeById(id);

        request.setAttribute("projectType", projectType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-detail.jsp");
        dispatcher.forward(request, response); // Use forward to load the edit form with data
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Update project type
    private void updateProjectType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String details = request.getParameter("details");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectType pt = new ProjectType();
        pt.setId(id);
        pt.setName(name);
        pt.setCode(code);
        pt.setDetails(details);
        pt.setStatus(status);

        groupService.updateProjectType(pt);
        response.sendRedirect("project-type-management");
//        response.sendRedirect(request.getHeader("referer"));
    }

//    HuyenPTNHE160769 
//    14/10/2024 
//    Change status project types
    private void changeStatusProjectType(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectType pt = new ProjectType();
        pt.setId(id);

        // If status is true, set to false; if false, set to true
        pt.setStatus(!status);

        // Change the status of a project type by id
        groupService.changeStatusProjectType(pt);
        // Redirect to the project-type-management page
        response.sendRedirect("project-type-management");
    }

//    HuyenPTNHE160769 
//    14/10/2024 
//    List of project type configs
    private void listProjectTypeConfig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String keywordUser = request.getParameter("keywordUser");
        String keyword = request.getParameter("keyword");
        String roleIdStr = request.getParameter("roleId");
        String statusStr = request.getParameter("status");

        // Process the filter value, convert to number or null if not selected
        Integer roleId = roleIdStr != null && !roleIdStr.isEmpty() ? Integer.valueOf(roleIdStr) : null;
        Boolean status = statusStr != null && !statusStr.isEmpty() ? Boolean.valueOf(statusStr) : null;
        Boolean statusFilter = request.getParameter("statusFilter") == null ? null : Boolean.valueOf(request.getParameter("statusFilter"));

        ProjectType projectType = groupService.getProjectTypeById(id);
        List<ProjectTypeSetting> ptSetting = ptSettingService.getProjectRoleList();
        List<ProjectType_User> ptUser = groupService.getAllProjectTypeUsers(keywordUser, roleId, status, id);
        
        request.setAttribute("sl", groupService.getAllProjectTypeSettings(keyword, statusFilter));
        request.setAttribute("projectType", projectType);
        request.setAttribute("ptSetting", ptSetting);
        request.setAttribute("ptUser", ptUser);
        request.setAttribute("keywordUser", keywordUser);
        request.setAttribute("roleId", roleId);
        request.setAttribute("status", status);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-config.jsp");
        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    22/10/2024 
//    Show form insert project type user
    private void showNewFormPTUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String keyword = request.getParameter("keyword");
        List<String> errors = new ArrayList<>();

        User userType = userService.findUserByFullNameOrEmail(keyword);
        List<ProjectTypeSetting> ptSetting = ptSettingService.getProjectRoleList();

        if (userType == null && keyword != null) {
            errors.add("No user found.");
        }

        request.setAttribute("typeId", typeId);
        request.setAttribute("ptSetting", ptSetting);
        request.setAttribute("userType", userType);
        request.setAttribute("keyword", keyword);
        request.setAttribute("errorMessages", errors);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-user-add.jsp");
        dispatcher.forward(request, response);
    }
    
//    HuyenPTNHE160769 
//    22/10/2024 
//    Insert project type user
    private void insertPTUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int roleId = Integer.parseInt(request.getParameter("pjRole"));

        ProjectType_User ptUser = new ProjectType_User();

        User u = new User();
        u.setId(id);
        ptUser.setUser(u);

        ProjectType pt = new ProjectType();
        pt.setId(typeId);
        ptUser.setPjType(pt);

        ProjectTypeSetting ptSetting = new ProjectTypeSetting();
        ptSetting.setId(roleId);
        ptUser.setPtSetting(ptSetting);

        groupService.insertProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId);
    }
    
//    HuyenPTNHE160769 
//    23/10/2024 
//    Show form edit project type user
    private void showEditFormPTUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectType_User ptUser = groupService.getProjectTypeUserById(id);
        List<ProjectTypeSetting> ptSetting = ptSettingService.getProjectRoleList();

        request.setAttribute("ptUser", ptUser);
        request.setAttribute("ptSetting", ptSetting);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-user-edit.jsp");
        dispatcher.forward(request, response);
    }
    
//    HuyenPTNHE160769 
//    23/10/2024 
//    Update project type user
    private void updatePTUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int roleId = Integer.parseInt(request.getParameter("pjRole"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectType_User ptUser = new ProjectType_User();
        ptUser.setId(id);
        ptUser.setStatus(status);

        User u = new User();
        u.setId(userId);
        ptUser.setUser(u);

        ProjectType pt = new ProjectType();
        pt.setId(typeId);
        ptUser.setPjType(pt);

        ProjectTypeSetting ptSetting = new ProjectTypeSetting();
        ptSetting.setId(roleId);
        ptUser.setPtSetting(ptSetting);

        groupService.updateProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

//    HuyenPTNHE160769 
//    18/10/2024 
//    Change status project types user
    private void changeStatusPTUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int recordId = Integer.parseInt(request.getParameter("id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectType_User ptUser = new ProjectType_User();
        ptUser.setId(recordId);
        // If status is true, set to false; if false, set to true
        ptUser.setStatus(!status);

        ProjectType pt = new ProjectType();
        pt.setId(typeId);
        ptUser.setPjType(pt);

        groupService.changeStatusProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

}
