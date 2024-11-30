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
@WebServlet(name = "ProfileController", urlPatterns = {"/member-profilecontroller"})
public class ProfileController extends HttpServlet {

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
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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
    //    BachHD
    //    28/09/2024 
    // hiển thị trang hồ sơ của người dùng
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy đối tượng HttpSession từ request
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session != null) {
            // Lấy người dùng từ session
            User user = (User) session.getAttribute("user");

            if (user != null) {
                // Set thông tin người dùng vào request để truyền tới profile.jsp
                request.setAttribute("memberProfile", user);

                // Chuyển tiếp sang trang profile.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/profile.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu không tìm thấy người dùng trong session, chuyển hướng về trang đăng nhập
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            // Nếu không có session, chuyển hướng về trang đăng nhập
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

                    // Cập nhật thông tin người dùng
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

                // Chuyển tiếp sang trang profile.jsp
                request.setAttribute("memberProfile", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/profile.jsp");
                dispatcher.forward(request, response);

            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
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
