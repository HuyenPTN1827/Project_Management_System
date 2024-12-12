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
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
            case "/verify" ->
                verifyOtp (request, response);
            case "/resend-otp" ->
                resendOtp(request, response);
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
//    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String fullname = request.getParameter("fullname");
//        String username = request.getParameter("username");
//        String email = request.getParameter("email");
////        String mobile = request.getParameter("mobile");
//        String password = request.getParameter("password");
//        String confirmPassword = request.getParameter("confirmPassword");
//
//        User user = new User();
//        user.setFull_name(fullname);
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
////        user.setMobile(mobile);
//
//// Validate user information
//        List<String> validationErrors = userService.validateUserRegister(user, confirmPassword);
//
//        // Kiểm tra giá trị người dùng nhập có lỗi nào không
//        if (!validationErrors.isEmpty()) {
//
//            request.setAttribute("fullname", fullname);
//            request.setAttribute("username", username);
//            request.setAttribute("email", email);
////            request.setAttribute("mobile", mobile);
//            request.setAttribute("password", password);
//            request.setAttribute("confirmpassword", confirmPassword);
//            request.setAttribute("NOTIFICATION", String.join(", ", validationErrors));
//
//          
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
//            dispatcher.forward(request, response);
//            return; // Dừng xử lý nếu có lỗi
//        }
//        try {
//            int result = userService.registerUser(user);
//
//            if (result == 1) {
//                // Đăng ký thành công
//                request.setAttribute("SUCCESS", "User Registered Successfully!");
//            } else if (result == -1) {
//                // Email đã tồn tại
//                request.setAttribute("NOTIFICATION", "Email or Username already exists. Please use a different one.");
//            } else {
//                // Đăng ký thất bại vì lý do khác
//                request.setAttribute("NOTIFICATION", "Registration failed. Please try again.");
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        request.setAttribute("fullname", fullname);
//        request.setAttribute("username", username);
//        request.setAttribute("email", email);
////        request.setAttribute("mobile", mobile);
//        request.setAttribute("password", password);
//        request.setAttribute("confirmpassword", confirmPassword);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/verify.jsp");
//        dispatcher.forward(request, response);
//    }
    
    
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String fullname = request.getParameter("fullname");
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");

    User user = new User();
    user.setFull_name(fullname);
    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);

    // Validate user information
    List<String> validationErrors = userService.validateUserRegister(user, confirmPassword);

    if (!validationErrors.isEmpty()) {
        // Return validation errors to the registration form
        request.setAttribute("fullname", fullname);
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("confirmpassword", confirmPassword);
        request.setAttribute("NOTIFICATION", String.join(", ", validationErrors));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
        dispatcher.forward(request, response);
        return;
    }

    try {
        int result = userService.registerUser(user);

        if (result == 1) {
            // Registration successful - generate and send OTP
            String otp = generateOtp();
            sendOtpToEmail(email, otp);

            // Save OTP and user ID to session
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("userEmail", email);

            // Forward to OTP verification page
            request.setAttribute("SUCCESS", "User Registered Successfully! please enter OTP");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/verify.jsp");
            dispatcher.forward(request, response);
        } else if (result == -1) {
            // Email or username already exists
            request.setAttribute("NOTIFICATION", "Email or Username already exists. Please use a different one.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
            dispatcher.forward(request, response);
        } else {
            // Registration failed
            request.setAttribute("NOTIFICATION", "Registration failed. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
            dispatcher.forward(request, response);
        }
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

protected void verifyOtp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String enteredOtp = request.getParameter("otp");
    HttpSession session = request.getSession();
    String sessionOtp = (String) session.getAttribute("otp");
    String userEmail = (String) session.getAttribute("userEmail"); // Lấy userId từ session
   

    if (enteredOtp.equals(sessionOtp)) {
        // Nếu OTP đúng, cập nhật trạng thái user thành 1 (active)
        userService.updateUserStatus(userEmail, 1);

        // Xóa OTP khỏi session sau khi xác thực thành công
        session.removeAttribute("otp");
        session.removeAttribute("userEmail");

        // Thông báo thành công
        request.setAttribute("enteredotp", enteredOtp);
        request.setAttribute("SUCCESS", "Account has been verified successfully. You can return to the login screen to log in.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/verify.jsp");
        dispatcher.forward(request, response);
    } else {
        // Nếu OTP không đúng, hiển thị thông báo lỗi
        request.setAttribute("enteredotp", enteredOtp);
        request.setAttribute("NOTIFICATION", "OTP is incorrect. Please try again.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/verify.jsp");
        dispatcher.forward(request, response);
    }
}


// Helper methods for OTP
private String generateOtp() {
    return String.format("%06d", new Random().nextInt(999999));
}

private void sendOtpToEmail(String email, String otp) {
        // Tham số cấu hình email có thể được lấy từ file cấu hình
        String host = "smtp.gmail.com";
        final String user = "haduybachbn@gmail.com";
        final String password = "iifq izgj dupx ivyy";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);
            Transport.send(message);
            System.out.println("OTP has been sent to email: " + email);
        } catch (MessagingException e) {
            System.out.println("Email sending failed. Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

protected void resendOtp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy email từ tham số gửi qua form
    String email = request.getParameter("email");

    if (email != null && !email.isEmpty()) {
        // Tạo OTP mới
        String otp = generateOtp();

        // Lưu OTP vào session
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);  // Lưu OTP vào session

        try {
            // Gửi OTP tới email
            sendOtpToEmail(email, otp);  // Nếu gửi thành công, không cần trả về giá trị boolean

            // Thông báo thành công
            request.setAttribute("SUCCESS", "A new OTP has been sent to your email.");
        } catch (Exception e) {
            // Thông báo lỗi gửi email
            request.setAttribute("NOTIFICATION", "Failed to send OTP. Please try again later.");
            e.printStackTrace(); // Bạn có thể ghi lại lỗi vào log nếu cần
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/verify.jsp");
        dispatcher.forward(request, response);
    } else {
        // Nếu không có email hợp lệ
        request.setAttribute("NOTIFICATION", "Unable to resend OTP. Please check your input and try again.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/guest/register.jsp");
        dispatcher.forward(request, response);
    }
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
//  
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
            session.setMaxInactiveInterval(1 * 60);

            // Kiểm tra trạng thái người dùng
            int userStatus = foundUser.getStatus();
            if (userStatus == 0) {
                // Tài khoản bị hủy
                request.setAttribute("NOTIFICATION", "Your account has been canceled.");
                request.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(request, response);
                return;
            } else if (userStatus == 2) {
                // Tài khoản chưa được xác thực
                request.setAttribute("NOTIFICATION", "Your account is not verified. Please check your email and enter OTP.");
                request.getRequestDispatcher("/WEB-INF/guest/verify.jsp").forward(request, response);
                
                return;
            } else if (userStatus == 1) {
                // Tài khoản hợp lệ
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

                    // Chuyển hướng đến trang dashboard cho người dùng hợp lệ
                    System.out.println("Redirecting to member-dashboard");
                    response.sendRedirect(request.getContextPath() + "/member-dashboard");
                } else {
                    System.out.println("User role setting is null!");
                    request.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
                }
            }
        } else {
            // Nếu không tìm thấy người dùng
            HttpSession session = request.getSession();
            request.setAttribute("user", email);
            session.setAttribute("NOTIFICATION", "Incorrect account or password, please log in again.");
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
