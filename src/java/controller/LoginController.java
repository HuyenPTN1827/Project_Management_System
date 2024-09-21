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
//                RequestDispatcher dispatcher = request.getRequestDispatcher("member/todo-list.jsp");
//                dispatcher.forward(request, response);
                response.sendRedirect("todo-list");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("NOTIFICATION", "Login Failed!");
                session.setAttribute("user", usernameOrEmail);
                response.sendRedirect("login");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
