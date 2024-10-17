/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProjectTypeSetting;
import service.GroupService;

/**
 *
 * @author Admin
 */
public class ProjectTypeSettingController extends HttpServlet {

    private GroupService groupService = new GroupService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            ProjectTypeSetting pts = new ProjectTypeSetting();
            if (action == null) {
                String keyword = request.getParameter("keyword");
                Boolean statusFilter = request.getParameter("statusFilter") == null ? null : Boolean.valueOf(request.getParameter("statusFilter"));
                request.setAttribute("sl", groupService.getAllProjectTypeSettings(keyword, statusFilter));
                request.getRequestDispatcher("/WEB-INF/admin/project-type-setting-list.jsp").forward(request, response);
            } else if (action.endsWith("add")) {
                request.getRequestDispatcher("/WEB-INF/admin/project-type-setting-add.jsp").forward(request, response);
            } else if (action.endsWith("edit")) {
                showEditForm(request, response);
            } else if (action.endsWith("delete")) {
                deleteProjectTypeSetting(request, response);
            } else if (action.endsWith("changeStatus")) {
                System.out.println("++++++++++++++++++++++++");
                System.out.println("changeStatus");
                changeStatusProjectTypeSetting(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        String action = request.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "add":
                        insertProjectTypeSetting(request, response);
                        break;
                    case "edit":
                        updateProjectTypeSetting(request, response);
                        break;
                }
            } else {
                response.getWriter().print("Error!");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
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

    // Insert a new ProjectTypeSetting
    private void insertProjectTypeSetting(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        int priority = Integer.parseInt(request.getParameter("priority"));
        boolean status = request.getParameter("status") != null;
        String description = request.getParameter("description");

        ProjectTypeSetting newSetting = new ProjectTypeSetting(0, name, type, value, priority, status, description);
        groupService.createProjectTypeSetting(newSetting);
        response.sendRedirect("ProjectTypeSetting");
    }

    // Show form to edit an existing ProjectTypeSetting
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectTypeSetting existingSetting = groupService.getProjectTypeSettingById(id);
        request.setAttribute("pts", existingSetting);
        request.getRequestDispatcher("/WEB-INF/admin/project-type-setting-edit.jsp").forward(request, response);
    }

    // Update an existing ProjectTypeSetting
    private void updateProjectTypeSetting(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        int priority = Integer.parseInt(request.getParameter("priority"));
        boolean status = request.getParameter("status") != null;
        String description = request.getParameter("description");

        ProjectTypeSetting updatedSetting = new ProjectTypeSetting(id, name, type, value, priority, status, description);
        groupService.updateProjectTypeSetting(updatedSetting);
        response.sendRedirect("ProjectTypeSetting");
    }

    // Delete a ProjectTypeSetting
    private void deleteProjectTypeSetting(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        groupService.deleteProjectTypeSetting(id);
        response.sendRedirect("ProjectTypeSetting");
    }
    // In ProjectTypeSettingServlet.java

    private void changeStatusProjectTypeSetting(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean newStatus = Boolean.parseBoolean(request.getParameter("status"));
        groupService.changeStatusProjectTypeSettingById(id, newStatus);
        response.sendRedirect("ProjectTypeSetting");
    }

}
