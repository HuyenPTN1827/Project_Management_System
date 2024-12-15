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
import model.Setting;
import model.User;
import service.ProjectTypeService;
import service.SettingService;
import service.UserService;

/**
 *
 * @author kelma
 */
public class ProjectTypeController extends HttpServlet {

    private ProjectTypeService ptService;
    private UserService userService;
    private SettingService settingService;

    @Override
    public void init() throws ServletException {
        this.ptService = new ProjectTypeService();
        this.userService = new UserService();
        this.settingService = new SettingService();
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

                // Project Type Setting Management
                case "/add-project-type-setting" ->
                    showNewFormPTSetting(request, response); // Show form insert project type setting
                case "/insert-project-type-setting" ->
                    insertPTSetting(request, response); // Insert project type setting
                case "/edit-project-type-setting" ->
                    showEditFormPTSetting(request, response); // Show form edit project type setting
                case "/update-project-type-setting" ->
                    updatePTSetting(request, response); // Update project type setting
                case "/change-status-project-type-setting" ->
                    changeStatusPTSetting(request, response); // Change status project type setting

                // Project Type User Management    
                case "/add-project-type-user" ->
                    showNewFormPTUser(request, response); // Show form insert project type user
                case "/insert-project-type-user" ->
                    insertPTUser(request, response); // Insert project type user
                case "/edit-project-type-user" ->
                    showEditFormPTUser(request, response); // Show form edit project type user
                case "/update-project-type-user" ->
                    updatePTUser(request, response); // Update project type user
                case "/change-status-project-type-user" ->
                    changeStatusPTUser(request, response); // Change status project type user

                // Project Type Criteria Management
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

                // Project Phase Management
                case "/add-project-phase" ->
                    showNewFormProjectPhase(request, response); // Show form insert project phase
                case "/insert-project-phase" ->
                    insertProjectPhase(request, response); // Insert project phase
                case "/edit-project-phase" ->
                    showEditFormProjectPhase(request, response); // Show form edit project phase
                case "/update-project-phase" ->
                    updateProjectPhase(request, response); // Update project phase
                case "/change-status-project-phase" ->
                    changeStatusProjectPhase(request, response); // Change status project phase
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
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectType pt = new ProjectType();
        pt.setName(name);
        pt.setCode(code);
        pt.setDetails(details);
        pt.setStatus(status);

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
        ptService.changeStatusProjectType(pt);
        // Redirect to the project-type-management page
        response.sendRedirect("project-type-management");
    }

//    HuyenPTNHE160769 
//    14/10/2024 
//    All List of project type configs (setting, user, phase, criteria)
    private void listProjectTypeConfig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String activeTab = request.getParameter("activeTab");
        ProjectType projectType = ptService.getProjectTypeById(id);
        List<ProjectType> listPjType = ptService.getAllProjectTypes(null, null);
        request.setAttribute("projectType", projectType);
        request.setAttribute("listPjType", listPjType);

        // Nếu không có activeTab, mặc định là "setting"
        if (activeTab == null || activeTab.isEmpty()) {
            activeTab = "setting"; // Tab mặc định là "setting"
        }
        request.setAttribute("activeTab", activeTab);

        //Project Type Setting
        String keywordSetting = request.getParameter("keywordSetting");
        String type = request.getParameter("type");
        String statusSettingStr = request.getParameter("statusSetting");
        Boolean statusSetting = statusSettingStr != null && !statusSettingStr.isEmpty() ? Boolean.valueOf(statusSettingStr) : null;
        List<ProjectTypeSetting> listType = ptService.getTypeList(id);
        List<ProjectTypeSetting> listSetting = ptService.getAllProjectTypeSettings(keywordSetting, statusSetting, type, id);
        request.setAttribute("sl", listSetting);
        request.setAttribute("listType", listType);
        request.setAttribute("keywordSetting", keywordSetting);
        request.setAttribute("type", type);
        request.setAttribute("statusSetting", statusSetting);

        //Project Type User
        String keywordUser = request.getParameter("keywordUser");
        String roleIdStr = request.getParameter("roleId");
        String statusUserStr = request.getParameter("statusUser");
        Integer roleId = roleIdStr != null && !roleIdStr.isEmpty() ? Integer.valueOf(roleIdStr) : null;
        Boolean statusUser = statusUserStr != null && !statusUserStr.isEmpty() ? Boolean.valueOf(statusUserStr) : null;
        List<ProjectType_User> ptUser = ptService.getAllProjectTypeUsers(keywordUser, roleId, statusUser, id);
//        List<Setting> ptSetting = settingService.getUserRoleList();
        List<User> listPMO = userService.getAllUsers(null, null, 2, 1);
        List<User> listQA = userService.getAllUsers(null, null, 5, 1);
//        request.setAttribute("ptSetting", ptSetting);
        request.setAttribute("ptUser", ptUser);
        request.setAttribute("keywordUser", keywordUser);
        request.setAttribute("roleId", roleId);
        request.setAttribute("statusUser", statusUser);
        request.setAttribute("listPMO", listPMO);
        request.setAttribute("listQA", listQA);

        //Project Phase
        String keywordPhase = request.getParameter("keywordPhase");
        String statusPhaseStr = request.getParameter("statusPhase");
        Boolean statusPhase = statusPhaseStr != null && !statusPhaseStr.isEmpty() ? Boolean.valueOf(statusPhaseStr) : null;
        List<ProjectPhase> phase = ptService.getAllProjectPhase(id, keywordPhase, statusPhase);
        request.setAttribute("phase", phase);
        request.setAttribute("keywordPhase", keywordPhase);
        request.setAttribute("statusPhase", statusPhase);

        //Project Type Criteria
        String keywordCriteria = request.getParameter("keywordCriteria");
        String phaseIdStr = request.getParameter("phaseId");
        String statusCriteriaStr = request.getParameter("statusCriteria");
        Integer phaseId = phaseIdStr != null && !phaseIdStr.isEmpty() ? Integer.valueOf(phaseIdStr) : null;
        Boolean statusCriteria = statusCriteriaStr != null && !statusCriteriaStr.isEmpty() ? Boolean.valueOf(statusCriteriaStr) : null;
        List<ProjectTypeCriteria> ptCriteria = ptService.getAllProjectTypeCriteria(keywordCriteria, phaseId, statusCriteria, id);
        List<ProjectPhase> listPhase = ptService.getAllProjectPhase(id, null, null);
        request.setAttribute("listPhase", listPhase);
        request.setAttribute("ptCriteria", ptCriteria);
        request.setAttribute("keywordCriteria", keywordCriteria);
        request.setAttribute("phaseId", phaseId);
        request.setAttribute("statusCriteria", statusCriteria);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-config.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormPTSetting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        List<ProjectTypeSetting> type = ptService.getTypeList(typeId);
        request.setAttribute("type", type);
        request.setAttribute("typeId", typeId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-setting-detail.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPTSetting(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String description = request.getParameter("description");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectTypeSetting setting = new ProjectTypeSetting();
        setting.setName(name);
        setting.setType(type);
        setting.setValue(value);
        setting.setPriority(priority);
        setting.setDescription(description);
        setting.setStatus(status);

        ProjectType pt = new ProjectType();
        pt.setId(typeId);
        setting.setPjType(pt);

        ptService.createProjectTypeSetting(setting);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=setting");
    }

    private void showEditFormPTSetting(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int id = Integer.parseInt(request.getParameter("id"));
        
        ProjectTypeSetting setting = ptService.getProjectTypeSettingById(id);
        List<ProjectTypeSetting> type = ptService.getTypeList(typeId);

        request.setAttribute("setting", setting);
        request.setAttribute("type", type);
        request.setAttribute("typeId", typeId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-setting-detail.jsp");
        dispatcher.forward(request, response);
    }

    private void updatePTSetting(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String description = request.getParameter("description");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectTypeSetting setting = new ProjectTypeSetting();
        setting.setId(id);
        setting.setName(name);
        setting.setType(type);
        setting.setValue(value);
        setting.setPriority(priority);
        setting.setDescription(description);
        setting.setStatus(status);

        ptService.updateProjectTypeSetting(setting);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=setting");
    }

    private void changeStatusPTSetting(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ptService.changeStatusProjectTypeSettingById(id, !status);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=setting");
    }

//    HuyenPTNHE160769 
//    22/10/2024 
//    Show form insert project type user
    private void showNewFormPTUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int typeId = Integer.parseInt(request.getParameter("typeId"));
//        String keyword = request.getParameter("keyword");
//        List<String> errors = new ArrayList<>();
//
//        User userType = userService.findUserByFullNameOrEmail(keyword);
//        List<ProjectTypeSetting> ptSetting = ptService.getProjectRoleList(typeId);
//
//        if (userType == null && keyword != null) {
//            errors.add("No user found.");
//        }
//
//        request.setAttribute("typeId", typeId);
//        request.setAttribute("ptSetting", ptSetting);
//        request.setAttribute("userType", userType);
//        request.setAttribute("keyword", keyword);
//        request.setAttribute("errorMessages", errors);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-user-add.jsp");
//        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    22/10/2024 
//    Insert project type user
    private void insertPTUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        ProjectType_User ptUser = new ProjectType_User();

        User u = new User();
        u.setId(userId);
        ptUser.setUser(u);

        ProjectType pt = new ProjectType();
        pt.setId(typeId);
        ptUser.setPjType(pt);

        Setting r = new Setting();
        r.setId(roleId);
        ptUser.setSetting(r);

        ptService.insertProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=manager");
    }

//    HuyenPTNHE160769 
//    23/10/2024 
//    Show form edit project type user
    private void showEditFormPTUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        ProjectType_User ptUser = ptService.getProjectTypeUserById(id);
//        List<ProjectTypeSetting> ptSetting = ptService.getProjectRoleList(typeId);

        request.setAttribute("ptUser", ptUser);
//        request.setAttribute("ptSetting", ptSetting);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-type-user-detail.jsp");
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

        Setting ptSetting = new Setting();
        ptSetting.setId(roleId);
        ptUser.setSetting(ptSetting);

        ptService.updateProjectTypeUser(ptUser);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=manager");
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
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=manager");
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
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=criteria");
    }

    private void showNewFormPTCriteria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        List<ProjectPhase> phase = ptService.getAllProjectPhase(typeId, null, null);

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
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=criteria");
    }

    private void showEditFormPTCriteria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        ProjectTypeCriteria ptCriteria = ptService.getProjectTypeCriteriaById(id);
        List<ProjectPhase> phase = ptService.getAllProjectPhase(typeId, null, null);

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
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=criteria");
    }

    //chiennkhe161554
    // Show form to add new Project Phase
    private void showNewFormProjectPhase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        request.setAttribute("typeId", typeId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-phase-detail.jsp");
        dispatcher.forward(request, response);
    }

    // Insert new Project Phase
    private void insertProjectPhase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String name = request.getParameter("name");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String details = request.getParameter("details");
                boolean status = Boolean.parseBoolean(request.getParameter("status"));


        ProjectPhase phase = new ProjectPhase();
        phase.setName(name);
        phase.setPriority(priority);
        phase.setDetails(details);
        phase.setStatus(status);

        ProjectType pt = new ProjectType();
        pt.setId(typeId);
        phase.setPjType(pt);

        ptService.insertProjectPhase(phase);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=phase");
    }

    // Show form to edit Project Phase
    private void showEditFormProjectPhase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectPhase phase = ptService.getProjectPhaseById(id);
        request.setAttribute("phase", phase);
        request.setAttribute("typeId", typeId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/project-phase-detail.jsp");
        dispatcher.forward(request, response);
    }

    // Update Project Phase
    private void updateProjectPhase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String details = request.getParameter("details");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectPhase phase = new ProjectPhase();
        phase.setId(id);
        phase.setName(name);
        phase.setPriority(priority);
        phase.setDetails(details);
        phase.setStatus(status);

        ptService.updateProjectPhase(phase);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=phase");
    }

    // Change status of Project Phase
    private void changeStatusProjectPhase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProjectPhase phase = new ProjectPhase();
        phase.setId(id);
        phase.setStatus(!status);

        ptService.changeStatusProjectPhase(phase);
        response.sendRedirect("project-type-config?id=" + typeId + "&activeTab=phase");
    }

}
