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
import model.Setting;
import service.SettingService;

/**
 *
 * @author kelma
 */
public class SettingController extends HttpServlet {

    private SettingService settingService;

    @Override
    public void init() throws ServletException {
        this.settingService = new SettingService();
    }

//    HuyenPTNHE160769 
//    13/10/2024 
//    Setting management controller by Admin
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

//        try {
            switch (action) {
                case "/add-setting" ->
                    showNewForm(request, response); // Show form insert setting
                case "/insert-setting" ->
                    insertSetting(request, response); // Insert setting
                case "/edit-setting" ->
                    showEditForm(request, response); // Show form edit setting
                case "/update-setting" ->
                    updateSetting(request, response); // Update setting
                case "/change-status-setting" ->
                    changeStatusSetting(request, response); // Change status setting
                default -> {
                    listSetting(request, response); // List of settings
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
//    Show list of settings
    private void listSetting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String type = request.getParameter("type");
        String statusStr = request.getParameter("status");

        // Process the filter value, convert to number or null if not selected
        Boolean status = statusStr != null && !statusStr.isEmpty() ? Boolean.valueOf(statusStr) : null;

        // Send search results and list depts, roles to JSP page
        List<Setting> listSetting = settingService.getAllSettings(keyword, type, status);

        // Path to user list page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/setting-list.jsp");
//        if (listSetting.isEmpty()) {
//            request.setAttribute("message", "No results found!");
//        }
        request.setAttribute("listSetting", listSetting);
        request.setAttribute("keyword", keyword);
        request.setAttribute("type", type);
        request.setAttribute("status", status);
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void insertSetting(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void updateSetting(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void changeStatusSetting(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
