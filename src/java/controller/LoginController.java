/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import service.UserService;

/**
 *
 * @author kelma
 */
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("member/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(usernameOrEmail);
        user.setEmail(usernameOrEmail);
        user.setPassword(password);

        try {
            if (userService.loginValidate(user)) {
                String role = userService.getUserRole(user);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userRole", role); // Lưu vai trò vào sessio
                session.setMaxInactiveInterval(1 * 60); // Thiết lập thời gian hết hạn là 1 phút

                // Chuyển hướng người dùng tùy theo vai trò
                if ("admin".equalsIgnoreCase(role)) {
                    response.sendRedirect(request.getContextPath() + "/user-management"); // Đường dẫn đến user-management
                } else if ("member".equalsIgnoreCase(role)) {
                    response.sendRedirect(request.getContextPath() + "/todo-list"); // Đường dẫn đến todo-list
                } else {
                    response.sendRedirect(request.getContextPath() + "/member/unauthorized.jsp"); // Trang lỗi truy cập
                }
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("NOTIFICATION", "Login Failed!");
                response.sendRedirect(request.getContextPath() + "/login"); // Đường dẫn đến trang đăng nhập
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
