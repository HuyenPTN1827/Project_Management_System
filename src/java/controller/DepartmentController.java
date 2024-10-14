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
import model.Department;
import service.GroupService;

/**
 *
 * @author kelma
 */
public class DepartmentController extends HttpServlet {

    private GroupService groupService;

    @Override
    public void init() throws ServletException {
        this.groupService = new GroupService();
    }

//    HuyenPTNHE160769 
//    13/10/2024 
//    Department management controller by Admin
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

//        try {
            switch (action) {
                case "/add-department" ->
                    showNewForm(request, response); // Show form insert department
                case "/insert-department" ->
                    insertDepartment(request, response); // Insert department
                case "/edit-department" ->
                    showEditForm(request, response); // Show form edit department
                case "/update-department" ->
                    updateDepartment(request, response); // Update department
                case "/change-status-department" ->
                    changeStatusDepartment(request, response); // Change status department
                default -> {
                    listDepartment(request, response); // List of departments
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

//    HuyenPTNHE160769 
//    13/10/2024 
//    Show list of departments
    private void listDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String statusStr = request.getParameter("status");

        // Process the filter value, convert to number or null if not selected
        Boolean status = statusStr != null && !statusStr.isEmpty() ? Boolean.valueOf(statusStr) : null;

        // Send search results and list depts, roles to JSP page
        List<Department> listDept = groupService.getAllDepartments(keyword, status);
        List<Department> dept = groupService.getDepartmentList();

        // Path to user list page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/dept-list.jsp");
//        if (listDept.isEmpty()) {
//            request.setAttribute("message", "No results found!");
//        }
        request.setAttribute("listDept", listDept);
        request.setAttribute("dept", dept);
        request.setAttribute("keyword", keyword);
        request.setAttribute("status", status);
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void insertDepartment(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void changeStatusDepartment(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
