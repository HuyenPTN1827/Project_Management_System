/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.SQLException;
import java.util.List;
import model.Todo;

/**
 *
 * @author kelma
 */
public interface TodoDAO {

    List<Todo> selectAllTodos();

    Todo selectTodoByID(Long id);

    void insertTodo(Todo todo) throws SQLException;

    boolean updateTodo(Todo todo) throws SQLException;

    boolean deleteTodo(Long id) throws SQLException;
}
