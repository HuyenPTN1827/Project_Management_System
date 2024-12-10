/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

/**
 *
 * @author Admin
 */
//5/10
//BachHD
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/resetpassword"})
public class ResetPasswordController extends HttpServlet {

    UserService userService = new UserService();

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredOtp = request.getParameter("otp");
        String newPassword = request.getParameter("newPassword");

        String sessionOtp = (String) request.getSession().getAttribute("otp");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        
        UserService userService = new UserService();
        if(!userService.validatePassword(newPassword)){
            request.setAttribute("ERROR", "The new password does not meet the security requirements.");
            request.getRequestDispatcher("/WEB-INF/member/resetpassword.jsp").forward(request, response);
        return;
        }

        if (enteredOtp != null && enteredOtp.equals(sessionOtp) && userId != null) {
            // Cập nhật mật khẩu
            boolean isUpdated = userService.updatePassword(userId, newPassword);
            if (isUpdated) {
                // Đã cập nhật thành công
                request.setAttribute("SUCCESS", "Password update successful.");
                request.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(request, response);
            } else {
                // Xử lý lỗi
                request.setAttribute("errorMessage", "Password update failed.");
                request.getRequestDispatcher("/WEB-INF/member/resetpassword.jsp").forward(request, response);
            }
        } else {
            // Xử lý nếu OTP không hợp lệ
            request.setAttribute("errorMessage", "Invalid OTP.");
            request.getRequestDispatcher("/WEB-INF/member/resetpassword.jsp").forward(request, response);
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

}
