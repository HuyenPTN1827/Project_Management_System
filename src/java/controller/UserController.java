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
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Department;
import model.Setting;
import model.User;
import service.DepartmentService;
import service.SettingService;
import service.UserService;

/**
 *
 * @author kelma
 */
public class UserController extends HttpServlet {

    private UserService userService;
    private DepartmentService deptService;
    private SettingService settingService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
        this.deptService = new DepartmentService();
        this.settingService = new SettingService();
    }

//    HuyenPTNHE160769 
//    26/09/2024 
//    User management controller by Admin
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-user" ->
                    showNewForm(request, response); // Show form insert user
                case "/insert-user" ->
                    insertUser(request, response); // Insert user
                case "/edit-user" ->
                    showEditForm(request, response); // Show form edit user
                case "/update-user" ->
                    updateUser(request, response); // Update user
                case "/change-status-user" ->
                    changeStatusUser(request, response); // Change status user
                default -> {
                    listUser(request, response); // List of users
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
//    29/09/2024 
//    Show list of users
    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String deptIdStr = request.getParameter("deptId");
        String roleIdStr = request.getParameter("roleId");
        String statusStr = request.getParameter("status");

        // Process the filter value, convert to number or null if not selected
        Integer deptId = deptIdStr != null && !deptIdStr.isEmpty() ? Integer.valueOf(deptIdStr) : null;
        Integer roleId = roleIdStr != null && !roleIdStr.isEmpty() ? Integer.valueOf(roleIdStr) : null;
        Integer status = statusStr != null && !statusStr.isEmpty() ? Integer.valueOf(statusStr) : null;

        // Send search results and list depts, roles to JSP page
        List<User> listUser = userService.getAllUsers(keyword, deptId, roleId, status);
        List<Department> dept = deptService.getAllDepartments(null, true);
        List<Setting> role = settingService.getUserRoleList();

        // Path to user list page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/user-list.jsp");
//        if (listUser.isEmpty()) {
//            request.setAttribute("message", "No results found!");
//        }
        request.setAttribute("listUser", listUser);
        request.setAttribute("dept", dept);
        request.setAttribute("role", role);
        request.setAttribute("keyword", keyword);
        request.setAttribute("deptId", deptId);
        request.setAttribute("roleId", roleId);
        request.setAttribute("status", status);
        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    27/09/2024 
//    Show form insert user
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call service to get all department for Department dropdown list
        List<Department> dept = deptService.getAllDepartments(null, true);
        // Call service to get all roles for Role dropdown list
        List<Setting> role = settingService.getUserRoleList();

        // Path to user information input form page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/user-detail.jsp");
        request.setAttribute("dept", dept);
        request.setAttribute("role", role);
        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    27/09/2024 
//    Insert user
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String notes = request.getParameter("notes");
        String dept = request.getParameter("dept");
        String role = request.getParameter("role");
        String password = generatePassword();

        // Process the filter value, convert to number or null if not selected
        Integer deptId = dept != null && !dept.isEmpty() ? Integer.valueOf(dept) : null;
        Integer roleId = role != null && !role.isEmpty() ? Integer.valueOf(role) : null;

        User u = new User();
        u.setFull_name(fullname);
        u.setUsername(username);
        u.setEmail(email);
        u.setMobile(mobile);
        u.setPassword(password);
        u.setNotes(notes);

        if (deptId != null) {
            Department d = new Department();
            d.setId(deptId);
            u.setDept(d);
        }
        if (roleId != null) {
            Setting s = new Setting();
            s.setId(roleId);
            u.setSetting(s);
        }

        // Validate user input
        List<String> errors = userService.validateUser(u);

        if (userService.checkEmailExist(email)) {
            errors.add("This email already exists.");
        }
        if (errors.isEmpty()) {
            // No validation errors, proceed to insert the user
            userService.insertUser(u, deptId, roleId);
            // Send email with account details
            sendEmail(email, fullname, username, password);
            // Redirect to user-management url
            response.sendRedirect("user-management");
        } else {
            // If there is a validation error, return to the form and display an error message
            request.setAttribute("errorMessages", errors);
            request.setAttribute("fullname", fullname);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("mobile", mobile);
            request.setAttribute("deptId", dept);
            request.setAttribute("roleId", role);
            showNewForm(request, response);
        }
    }

    //    HuyenPTNHE160769 
    //    27/09/2024 
    //    Show form edit user
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        // Call service to get an user information by id
        User user = userService.getUserById(id);

        // Call service to get all department for Department dropdown list
        List<Department> dept = deptService.getAllDepartments(null, true);
        // Call service to get all roles for Role dropdown list
        List<Setting> role = settingService.getUserRoleList();

        // Path to user information input form page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/user-detail.jsp");
        request.setAttribute("userDetail", user);
        request.setAttribute("dept", dept);
        request.setAttribute("role", role);
        dispatcher.forward(request, response);
    }

//    HuyenPTNHE160769 
//    27/09/2024 
//    Edit user
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String notes = request.getParameter("notes");
        int status = Integer.parseInt(request.getParameter("status"));
        String dept = request.getParameter("dept");
        String role = request.getParameter("role");

        // Process the filter value, convert to number or null if not selected
        Integer deptId = dept != null && !dept.isEmpty() ? Integer.valueOf(dept) : null;
        Integer roleId = role != null && !role.isEmpty() ? Integer.valueOf(role) : null;

        User u = new User();
        u.setId(id);
        u.setFull_name(fullname);
        u.setUsername(username);
        u.setEmail(email);
        u.setMobile(mobile);
        u.setPassword(password);
        u.setNotes(notes);
        u.setStatus(status);

        if (deptId != null) {
            Department d = new Department();
            d.setId(deptId);
            u.setDept(d);
        }
        if (roleId != null) {
            Setting s = new Setting();
            s.setId(roleId);
            u.setSetting(s);
        }

        // Validate user input
        List<String> errors = userService.validateUser(u);

        if (errors.isEmpty()) {
            // No validation errors, proceed to update the user
            userService.updateUser(u, deptId, roleId);
            // Redirect to user-management url
            response.sendRedirect("user-management");
        } else {
            // If there is a validation error, return to the form and display an error message
            request.setAttribute("errorMessages", errors);
            showEditForm(request, response);
        }
    }

//    HuyenPTNHE160769 
//    26/09/2024 
//    Change status user
    private void changeStatusUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));

        User u = new User();
        u.setId(id);
        if (status == 1) {
            u.setStatus(0);
        }
        if (status == 0) {
            u.setStatus(1);
        }
        // Change status of an user by id
        userService.changeStatusUser(u);
        // Redirect to user-management url
        response.sendRedirect("user-management");
    }

    private String generatePassword() {
        final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "0123456789";
        final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";
        final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARS;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure password includes at least one of each type
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Fill the rest of the password
        for (int i = 4; i < 8; i++) { // Password length = 8
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return shuffleString(password.toString());
    }

    private String shuffleString(String input) {
        SecureRandom random = new SecureRandom();
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }

    private void sendEmail(String email, String fullname, String username, String password) {
        // Tham số cấu hình email có thể được lấy từ file cấu hình
        String host = "smtp.gmail.com";
        final String user = "haduybachbn@gmail.com";
        final String passwordEmail = "iifq izgj dupx ivyy";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, passwordEmail);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("PMS account information");
//            message.setText("Your OTP code is: " + otp);
            message.setText("Dear " + fullname + ",\n\n"
                    + "Your account has been created successfully.\n"
                    + "Username: " + username + "\n"
                    + "Password: " + password + "\n\n"
                    + "Please log in and change your password.\n\n"
                    + "Best regards,\nSEP490-G88");
            Transport.send(message);
            System.out.println("OTP has been sent to email: " + email);
        } catch (MessagingException e) {
            System.out.println("Email sending failed. Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
