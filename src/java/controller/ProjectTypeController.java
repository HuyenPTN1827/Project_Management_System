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
import model.ProjectPhase;
import model.ProjectType;
import model.ProjectTypeCriteria;
import model.ProjectTypeSetting;
import model.ProjectType_User;
import model.User;
import service.ProjectTypeService;
import service.UserService;

/**
 *
 * @author kelma
 */
public class ProjectTypeController extends HttpServlet {

    private ProjectTypeService ptService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.ptService = new ProjectTypeService();
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
                case "/add-project-type-criteria" ->
                    showNewFormPTCriteria(request, response); // Show form insert project type criteria
                case "/insert-project-type-criteria" ->
                    insertPTCriteria(request, response); // Insert project type criteria
                case "/edit-project-type-criteria" ->
                    showEditFormPTCriteria(request, response); // Show form edit project type criteria
                case "/update-project-type-criteria" ->
                    updatePTCriteria(request, response); // Update project type criteria
                case "/change-status-project-type-criteria" ->
                    changeStatusPTCriteria(request, response); // Change status project type criteria
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
        List<ProjectType> listType = ptService.getAllProjectTypes(keyword, status);

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

        ptService.insertProjectType(pt);
        response.sendRedirect("project-type-management");
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Show form edit project type
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectType projectType = ptService.getProjectTypeById(id);

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

        ptService.updateProjectType(pt);
//        response.sendRedirect("project-type-management");
        response.sendRedirect(request.getHeader("referer"));
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
        ptService.changeStatusProjectType(pt);
        // Redirect to the project-type-management page
        response.sendRedirect("project-type-management");
    }

//    HuyenPTNHE160769 
//    14/10/2024 
//    List of project type configs
    private void listProjectTypeConfig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String keyword = request.getParameter("keyword");
        String keywordUser = request.getParameter("keywordUser");
        String roleIdStr = request.getParameter("roleId");
        String statusUserStr = request.getParameter("statusUser");
        String keywordCriteria = request.getParameter("keywordCriteria");
        String phaseIdStr = request.getParameter("phaseId");
        String statusCriteriaStr = request.getParameter("statusCriteria");

        // Process the filter value, convert to number or null if not selected
        Boolean statusFilter = request.getParameter("statusFilter") == null ? null : Boolean.valueOf(request.getParameter("statusFilter"));
        Integer roleId = roleIdStr != null && !roleIdStr.isEmpty() ? Integer.valueOf(roleIdStr) : null;
        Boolean statusUser = statusUserStr != null && !statusUserStr.isEmpty() ? Boolean.valueOf(statusUserStr) : null;
        Integer phaseId = phaseIdStr != null && !phaseIdStr.isEmpty() ? Integer.valueOf(phaseIdStr) : null;
        Boolean statusCriteria = statusCriteriaStr != null && !statusCriteriaStr.isEmpty() ? Boolean.valueOf(statusCriteriaStr) : null;

        ProjectType projectType = ptService.getProjectTypeById(id);
        List<ProjectTypeSetting> ptSetting = ptService.getProjectRoleList(id);
        List<ProjectType_User> ptUser = ptService.getAllProjectTypeUsers(keywordUser, roleId, statusUser, id);
        List<ProjectPhase> phase = ptService.getPhaseList(id);
        List<ProjectTypeCriteria> ptCriteria = ptService.getAllProjectTypeCriteria(keywordCriteria, phaseId, statusCriteria, id);

        request.setAttribute("sl", ptService.getAllProjectTypeSettings(keyword, statusFilter));
        request.setAttribute("projectType", projectType);
        request.setAttribute("ptSetting", ptSetting);
        request.setAttribute("ptUser", ptUser);
        request.setAttribute("keywordUser", keywordUser);
        request.setAttribute("roleId", roleId);
        request.setAttribute("statusUser", statusUser);
        request.setAttribute("phase", phase);
        request.setAttribute("ptCriteria", ptCriteria);
        request.setAttribute("keywordCriteria", keywordCriteria);
        request.setAttribute("phaseId", phaseId);
        request.setAttribute("statusCriteria", statusCriteria);

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
        List<ProjectTypeSetting> ptSetting = ptService.getProjectRoleList(typeId);

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

        ptService.insertProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

//    HuyenPTNHE160769 
//    23/10/2024 
//    Show form edit project type user
    private void showEditFormPTUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        ProjectType_User ptUser = ptService.getProjectTypeUserById(id);
        List<ProjectTypeSetting> ptSetting = ptService.getProjectRoleList(typeId);

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

        ptService.updateProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

//    HuyenPTNHE160769 
//    18/10/2024 
//    Change status project types user
    private void changeStatusPTUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectType_User ptUser = new ProjectType_User();
        ptUser.setId(id);
        // If status is true, set to false; if false, set to true
        ptUser.setStatus(!status);

        ProjectType pt = new ProjectType();
        pt.setId(typeId);
        ptUser.setPjType(pt);

        ptService.changeStatusProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

//    HuyenPTNHE160769 
//    29/10/2024 
//    Change status project types criteria
    private void changeStatusPTCriteria(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectTypeCriteria ptc = new ProjectTypeCriteria();
        ptc.setId(id);
        ptc.setStatus(!status);

        ptService.changeStatusProjectTypeCriteria(ptc);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

    private void showNewFormPTCriteria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        List<ProjectPhase> phase = ptService.getPhaseList(typeId);

        request.setAttribute("typeId", typeId);
        request.setAttribute("phase", phase);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-criteria-detail.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPTCriteria(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String name = request.getParameter("name");
        var weight = Float.parseFloat(request.getParameter("weight"));
        String description = request.getParameter("description");
        int phaseId = Integer.parseInt(request.getParameter("phaseId"));

        ProjectTypeCriteria ptc = new ProjectTypeCriteria();
        ptc.setName(name);
        ptc.setWeight(weight);
        ptc.setDescription(description);

        ProjectPhase pp = new ProjectPhase();
        pp.setId(phaseId);
        ptc.setPjPhase(pp);

        ptService.insertProjectTypeCriteria(ptc);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

    private void showEditFormPTCriteria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        ProjectTypeCriteria ptCriteria = ptService.getProjectTypeCriteriaById(id);
        List<ProjectPhase> phase = ptService.getPhaseList(typeId);

        request.setAttribute("typeId", typeId);
        request.setAttribute("ptCriteria", ptCriteria);
        request.setAttribute("phase", phase);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-criteria-detail.jsp");
        dispatcher.forward(request, response);
    }

    private void updatePTCriteria(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        var weight = Float.parseFloat(request.getParameter("weight"));
        String description = request.getParameter("description");
        int phaseId = Integer.parseInt(request.getParameter("phaseId"));

        ProjectTypeCriteria ptc = new ProjectTypeCriteria();
        ptc.setId(id);
        ptc.setName(name);
        ptc.setWeight(weight);
        ptc.setDescription(description);

        ProjectPhase pp = new ProjectPhase();
        pp.setId(phaseId);
        ptc.setPjPhase(pp);

        ptService.updateProjectTypeCriteria(ptc);
        response.sendRedirect("project-type-config?id=" + typeId);
    }

}
