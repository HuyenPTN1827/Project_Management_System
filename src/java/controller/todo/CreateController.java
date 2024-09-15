/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.todo;

import context.TodoDAO;
import context.TodoDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Todo;

/**
 *
 * @author kelma
 */
public class CreateController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TodoDAO todoDao;

    public void init() {
        todoDao = new TodoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("member/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
            boolean status = Boolean.valueOf(request.getParameter("status"));
            
            Todo t = new Todo();
            t.setTitle(title);
            t.setDescription(description);
            t.setTargetDate(targetDate);
            t.setStatus(status);
            
            todoDao.insertTodo(t);
            response.sendRedirect("todo-list");
        } catch (SQLException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
