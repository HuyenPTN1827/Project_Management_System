/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.SettingDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Setting;
import model.User;
import service.BaseServive;
import service.SettingService;
import service.UserService;

/**
 *
 * @author kelma
 */
public class AuthenticationController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;
    private SettingService settingService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
        this.settingService = new SettingService();

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

//    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String fullname = request.getParameter("fullname");
//        String email = request.getParameter("email");
//        String mobile = request.getParameter("mobile");
//        String password = request.getParameter("password");
//
//        User user = new User();
//        user.setFull_name(fullname);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setMobile(mobile);
//
//        try {
//            int result = userService.registerUser(user);
//            if (result == 1) {
//                request.setAttribute("NOTIFICATION", "User Register Successfully!");
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
//        dispatcher.forward(request, response);
//    }
    //BachHD
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
// Validate user information
        List<String> validationErrors = userService.validateUser(user);

        // Kiểm tra giá trị người dùng nhập có lỗi nào không
        if (!validationErrors.isEmpty()) {
            request.setAttribute("NOTIFICATION", String.join(", ", validationErrors));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
            dispatcher.forward(request, response);
            return; // Dừng xử lý nếu có lỗi
        }
        try {
            int result = userService.registerUser(user);

            if (result == 1) {
                // Đăng ký thành công
                request.setAttribute("SUCCESS", "User Registered Successfully!");
            } else if (result == -1) {
                // Email đã tồn tại
                request.setAttribute("NOTIFICATION", "Email already exists. Please use a different email.");
            } else {
                // Đăng ký thất bại vì lý do khác
                request.setAttribute("NOTIFICATION", "Registration failed. Please try again.");
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
//   private void login(HttpServletRequest request, HttpServletResponse response) 
//        throws ServletException, IOException {
//    String email = request.getParameter("email");
//    String password = request.getParameter("password");
//
//    User user = new User();
//    user.setEmail(email);
//    user.setPassword(password);
//
//    try {
//        User foundUser = userService.loginValidate(user);
//        if (foundUser != null) {
//            int roleId = foundUser.getRole_id();
//            HttpSession session = request.getSession();
//            session.setAttribute("user", foundUser);
//            session.setMaxInactiveInterval(1 * 60);
//
//            // Phân quyền dựa vào role_id
//            if (roleId == 1) { // Admin
//                response.sendRedirect(request.getContextPath() + "/user-management");
//            } else if (roleId == 2) { // Member
//                response.sendRedirect(request.getContextPath() + "/member-dashboard");
//            } else {
//                response.sendRedirect(request.getContextPath() + "/member/unauthorized.jsp");
//            }
//        } else {
//            HttpSession session = request.getSession();
//            session.setAttribute("NOTIFICATION", "Login Failed!");
//            request.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(request, response);
//
//        }
//    } catch (ClassNotFoundException e) {
//        e.printStackTrace();
//        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal error occurred.");
//    }
//}
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        SettingDAO settingDAO = new SettingDAO();
        List<Setting> userRoles = settingService.getPriorityUserRolesList();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        try {
            User foundUser = userService.loginValidate(user);
            if (foundUser != null) {
                System.out.println("User found: " + foundUser.getEmail());
                int roleId = foundUser.getRole_id();
                HttpSession session = request.getSession();
                session.setAttribute("user", foundUser);
                session.setMaxInactiveInterval(30 * 60);

                Setting userRoleSetting = null;
                for (Setting role : userRoles) {
                    System.out.println("Checking role: " + role.getValue() + " with ID: " + roleId);
                    if (role.getValue().equals(String.valueOf(roleId))) {
                        userRoleSetting = role;
                        break;
                    }
                }
                if (userRoleSetting != null) {
                    session.setAttribute("userRoleSetting", userRoleSetting);
                    System.out.println("User Role: " + userRoleSetting.getName());
                    System.out.println("Role Priority: " + userRoleSetting.getPriority());
                    System.out.println("User found: " + foundUser.getEmail() + ", Role ID: " + foundUser.getRole_id());

                    // Phân quyền dựa vào tên role
                    if (userRoleSetting.getPriority() == 1) {
                        System.out.println("Redirecting to user-management");
                        response.sendRedirect(request.getContextPath() + "/user-management");
                    } else if (userRoleSetting.getPriority() >= 2) {
                        System.out.println("Redirecting to member-dashboard");
                        response.sendRedirect(request.getContextPath() + "/member-dashboard");
                    } else {
                        System.out.println("Unauthorized access!");
                        response.sendRedirect(request.getContextPath() + "/member/unauthorized.jsp");
                    }
                } else {
                    System.out.println("User role setting is null!");
                    response.sendRedirect(request.getContextPath() + "/member/unauthorized.jsp");
                }
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("NOTIFICATION", "Login Failed!");
                System.out.println("Login Failed!");
                request.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal error occurred.");
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
