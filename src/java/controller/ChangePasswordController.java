/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import service.UserService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/changepassword"})
public class ChangePasswordController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

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
            out.println("<title>Servlet ChangePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/change-password.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //    BachHD
    //    28/09/2024        
    //     xử lý yêu cầu đổi mật khẩu của người dùng
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Lấy đối tượng user từ session
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    // Kiểm tra nếu user là null
    if (user == null) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
        return;
    }

    String oldPassword = request.getParameter("oldPassword");
    String newPassword = request.getParameter("newPassword");

    // Gọi UserService để validate mật khẩu mới
    UserService userService = new UserService();
    
    // Kiểm tra tính hợp lệ của mật khẩu mới
    if (!userService.validatePassword(newPassword)) {
        request.setAttribute("ERROR", "The new password does not meet the security requirements.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/change-password.jsp");
        dispatcher.forward(request, response);
        return;
    }

    // Gọi UserService để thay đổi mật khẩu
    boolean success = userService.changePassword(user, oldPassword, newPassword);

    if (success) {
        // Đổi mật khẩu thành công
        request.setAttribute("NOTIFICATION", "You have changed your password successfully.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/change-password.jsp");
        dispatcher.forward(request, response);
    } else {
        // Đổi mật khẩu không thành công (mật khẩu cũ không khớp)
       
        request.setAttribute("ERROR", "Old password is incorrect.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/change-password.jsp");
        dispatcher.forward(request, response);
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
