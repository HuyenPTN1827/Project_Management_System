/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.TodoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Todo;

/**
 *
 * @author kelma
 */
public class TodoService {
    private TodoDAO todoDAO;

    public TodoService() {
        this.todoDAO = new TodoDAO();
    }
    
    // Lấy tất cả người dùng
    public List<Todo> getAllTodos() {
        return todoDAO.selectAllTodos();
    }

    // Lấy thông tin người dùng qua ID
    public Todo getTodoById(Long id) {
        return todoDAO.selectTodoByID(id);
    }
    
    // Admin thêm thông tin người dùng mới
    public int insertTodo(Todo todo) throws SQLException {
        return todoDAO.insertTodo(todo);
    }

    // Cập nhật thông tin người dùng
    public boolean updateTodo(Todo todo) throws SQLException {
        return todoDAO.updateTodo(todo);
    }

    // Xóa người dùng
    public boolean deleteTodo(Long id) throws SQLException {
        return todoDAO.deleteTodo(id);
    }
}
