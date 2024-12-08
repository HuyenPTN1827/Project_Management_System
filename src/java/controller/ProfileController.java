/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import service.UserService;

/**
 *
 * @author Admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "ProfileController", urlPatterns = {"/member-profile"})
public class ProfileController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
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
    //    BachHD
    //    28/09/2024 
    // hiển thị trang hồ sơ của người dùng
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);  // Kiểm tra xem session có tồn tại không
        if (session != null) {
            User user = (User) session.getAttribute("user");  // Lấy đối tượng user từ session

            if (user != null) {
                // Lấy thông tin người dùng với role từ UserService
                UserService userService = new UserService();
                User userWithRole = userService.getUserBySessionId(user.getId());

                if (userWithRole != null) {
                    // Nếu tìm thấy thông tin người dùng và role, thêm vào request
                    request.setAttribute("userProfile", userWithRole);
                } else {
                    // Nếu không tìm thấy thông tin người dùng, hiển thị lỗi
                    request.setAttribute("error", "User not found.");
                }

                // Chuyển tiếp request đến profile.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/profile.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu không có user trong session, chuyển hướng đến login
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            // Nếu không có session, chuyển hướng đến login
            response.sendRedirect(request.getContextPath() + "/login");
        }
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
    // updateProfile Member
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/member-profile":
                    updateProfile(request, response);  // Handle profile update
                    break;
                case "/change-avatar":
                    changeAvatar(request, response);  // Handle avatar change
                default:
                    // Handle default POST action if needed
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
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

    //HuyenPTNHE160769
    private void changeAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        Part filePart = request.getPart("avatar");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);  // Lấy phần mở rộng file

        // Tạo tên mới cho file để tránh trùng lặp
        String newFileName = "avatar_" + userId + "." + extension;
        String filePath = request.getServletContext().getRealPath("/img/avatars") + File.separator + newFileName;

        // Lưu file vào thư mục server
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }

        // Cập nhật avatar trong database
        User u = new User();
        u.setId(userId);
        u.setAvatar(newFileName);  // Lưu tên ảnh vào user

        userService.changeAvatar(u);
        response.sendRedirect("member-profile");
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UserService userService = new UserService();

        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                String fullname = request.getParameter("fullname");
                String email = request.getParameter("email");
                String mobile = request.getParameter("mobile");

                // Check if the profile was updated
                boolean isUpdated = false;

                // Check if the new values are different from the current ones
                if (!fullname.equals(user.getFull_name())
                        || !email.equals(user.getEmail())
                        || !mobile.equals(user.getMobile())) {

                    // Chỉ cập nhật các trường được phép
                    user.setFull_name(fullname);
                    user.setEmail(email);
                    user.setMobile(mobile);

                    // Gọi phương thức cập nhật từ UserService
                    isUpdated = userService.updateMember(user);
                }

                if (isUpdated) {
                    // Cập nhật lại session
                    session.setAttribute("user", user);
                    request.setAttribute("message", "Profile updated successfully!");
                } else {
                    // Nếu không có thay đổi nào
                    request.setAttribute("err", "No changes were made.");
                }

                // Lấy thông tin đầy đủ của user từ database để tránh mất dữ liệu
                User updatedUser = userService.getUserBySessionId(user.getId());
                request.setAttribute("userProfile", updatedUser);

                // Chuyển tiếp sang trang profile.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/profile.jsp");
                dispatcher.forward(request, response);

            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

}
