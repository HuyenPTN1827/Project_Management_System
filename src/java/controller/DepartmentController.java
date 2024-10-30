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
import service.DepartmentService;

/**
 *
 * @author kelma
 */
public class DepartmentController extends HttpServlet {

    private DepartmentService deptService;

    @Override
    public void init() throws ServletException {
        this.deptService = new DepartmentService();
    }

//    HuyenPTNHE160769 
//    13/10/2024 
//    Department management controller by Admin
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
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
//    Show list of departments
    private void listDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String statusStr = request.getParameter("status");

        // Process the filter value, convert to number or null if not selected
        Boolean status = statusStr != null && !statusStr.isEmpty() ? Boolean.valueOf(statusStr) : null;

        List<Department> listDept = deptService.getAllDepartments(keyword, status);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/dept-list.jsp");
//        if (listDept.isEmpty()) {
//            request.setAttribute("message", "No results found!");
//        }
        request.setAttribute("listDept", listDept);
        request.setAttribute("keyword", keyword);
        request.setAttribute("status", status);
        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Show form insert department
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call service to get all department for Department dropdown list
        List<Department> dept = deptService.getDepartmentList();

        // Path to user information input form page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/dept-detail.jsp");
        request.setAttribute("dept", dept);
        request.setAttribute("department", null);
        dispatcher.include(request, response);
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Insert department
    private void insertDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String parent = request.getParameter("parent");
        String details = request.getParameter("details");

        // Process the filter value, convert to number or null if not selected
        Integer parentId = parent != null && !parent.isEmpty() ? Integer.valueOf(parent) : null;

        Department d = new Department();
        d.setName(name);
        d.setCode(code);
        if (parentId != null) {
            d.setParentId(parentId);
        }
        d.setDetails(details);

        deptService.insertDepartment(d, parentId);
        response.sendRedirect("department-management");
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Show form edit department
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = deptService.getDepartmentById(id);
        List<Department> dept = deptService.getDepartmentList();

        request.setAttribute("department", department);
        request.setAttribute("dept", dept);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/dept-detail.jsp");
        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    15/10/2024 
//    Update department
    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String parent = request.getParameter("parent");
        String details = request.getParameter("details");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Process the filter value, convert to number or null if not selected
        Integer parentId = parent != null && !parent.isEmpty() ? Integer.valueOf(parent) : null;

        Department d = new Department();
        d.setId(id);
        d.setName(name);
        d.setCode(code);
        if (parentId != null) {
            d.setParentId(parentId);
        }
        d.setDetails(details);
        d.setStatus(status);

        deptService.updateDepartment(d, parentId);
        // Redirect to user-management url
        response.sendRedirect("department-management");
    }

//    HuyenPTNHE160769 
//    14/10/2024 
//    Change status Department
    private void changeStatusDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Department d = new Department();
        d.setId(id);

        // If status is true, set to false; if false, set to true
        d.setStatus(!status);

        // Change the status of a dept by id
        deptService.changeStatusDepartment(d);
        // Redirect to the department-management page
        response.sendRedirect("department-management");
    }

}
