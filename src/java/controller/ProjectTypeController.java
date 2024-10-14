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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProjectType;
import service.GroupService;

/**
 *
 * @author kelma
 */
public class ProjectTypeController extends HttpServlet {

    private GroupService groupService;

    @Override
    public void init() throws ServletException {
        this.groupService = new GroupService();
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

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
    }

    private void insertProjectType(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
    }

    private void updateProjectType(HttpServletRequest request, HttpServletResponse response) {
    }
    
//    HuyenPTNHE160769 
//    14/10/2024 
//    Change status Project types
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

}
