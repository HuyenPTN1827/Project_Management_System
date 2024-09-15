/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import context.UserDAO;
import context.UserDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author kelma
 */
public class UpdateController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.selectUserByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/user-form.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String role = request.getParameter("role");
            boolean status = Boolean.valueOf(request.getParameter("status"));
            
            User u = new User();
            u.setUserId(id);
            u.setUsername(username);
            u.setPassword(password);
            u.setPassword(password);
            u.setFullname(fullname);
            u.setEmail(email);
            u.setMobile(mobile);
            u.setRole(role);
            u.setStatus(status);
            
            userDao.updateUser(u);
            response.sendRedirect("user-list");
        } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
