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
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import service.UserService;

/**
 *
 * @author kelma
 */
public class AuthenticationController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/register-form" ->
                showRegisterForm(request, response);
            case "/register" ->
                register(request, response);
            case "/login-form" ->
                showLoginForm(request, response);
            case "/login" ->
                login(request, response);
            case "/logout" ->
                logout(request, response);
            default -> {
            }
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

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        User user = new User();
        user.setFull_name(fullname);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);

        try {
            int result = userService.registerUser(user);
            if (result == 1) {
                request.setAttribute("NOTIFICATION", "User Register Successfully!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
        dispatcher.forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/login.jsp");
        dispatcher.forward(request, response);
    }

//    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//
//        try {
//            if (userService.loginValidate(user)) {
//                String role = userService.getUserRole(user);
//
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                session.setAttribute("userRole", role); // Lưu vai trò vào sessio
//                session.setMaxInactiveInterval(1 * 60); // Thiết lập thời gian hết hạn là 1 phút
//
//                // Chuyển hướng người dùng tùy theo vai trò
//                if ("admin".equalsIgnoreCase(role)) {
//                    response.sendRedirect(request.getContextPath() + "/user-management"); // Đường dẫn đến user-management
//                } else if ("member".equalsIgnoreCase(role)) {
//                    response.sendRedirect(request.getContextPath() + "/todo-list"); // Đường dẫn đến todo-list
//                } else {
//                    response.sendRedirect(request.getContextPath() + "/member/unauthorized.jsp"); // Trang lỗi truy cập
//                }
//            } else {
//                HttpSession session = request.getSession();
//                session.setAttribute("NOTIFICATION", "Login Failed!");
//                response.sendRedirect(request.getContextPath() + "/login"); // Đường dẫn đến trang đăng nhập
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    User user = new User();
    user.setEmail(email);
    user.setPassword(password);

    try {
        User foundUser = userService.loginValidate(user);
        if (foundUser != null) {
            int roleId = foundUser.getRole_id(); // Lấy role_id
            HttpSession session = request.getSession();
            session.setAttribute("user", foundUser);
            session.setMaxInactiveInterval(1 * 60);

            // Phân quyền dựa vào role_id
            if (roleId == 1) { // Giả sử role_id = 1 là admin
                response.sendRedirect(request.getContextPath() + "/user-management");
            } else if (roleId == 7) { // Giả sử role_id = 7 là member
                response.sendRedirect(request.getContextPath() + "/member-dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/member/unauthorized.jsp");
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("NOTIFICATION", "Login Failed!");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}


    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Lấy session hiện tại
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không có session

        if (session != null) {
            // Hủy session để đăng xuất người dùng
            session.invalidate();
        }

        // Chuyển hướng về trang đăng nhập sau khi đăng xuất
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/login.jsp");
        dispatcher.forward(request, response);
    }

}
