/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Todo;
import model.User;

/**
 *
 * @author kelma
 */
public class TodoDAO {

    private static final String SELECT_ALL_TODOS_SQL = "SELECT t.id, t.title, "
            + "t.description, t.targetDate, t.status, t.userId, u.username\n"
            + "FROM pms.todo t INNER JOIN pms.user u ON t.userId = u.userId\n"
            + "ORDER BY t.id DESC";
//    private static final String SELECT_TODO_BY_ID_SQL = "SELECT t.id, t.title, "
//            + "t.description, t.targetDate, t.status, t.userId, u.username\n"
//            + "FROM pms.todo t INNER JOIN pms.user u ON t.userId = u.userId"
//            + "WHERE t.id = ?";
    private static final String SELECT_TODO_BY_ID_SQL = "SELECT id, title, "
            + "description, targetDate, status\n" 
            + "FROM pms.todo WHERE id = ?";
    private static final String INSERT_TODO_SQL = "INSERT INTO pms.todo (title, "
            + "description, targetDate, status, userId)\n"
            + "VALUES (?, ?, ?, ?, ?) ";
    private static final String UPDATE_TODO_SQL = "UPDATE pms.todo SET title = ?, "
            + "description = ?, targetDate = ?, status = ?, userId = ?\n"
            + "WHERE id = ?";
    private static final String DELETE_TODO_BY_ID_SQL = "DELETE FROM pms.todo WHERE id = ?";

    public List<Todo> selectAllTodos() {
        List<Todo> todo = new ArrayList<>();

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(SELECT_ALL_TODOS_SQL);) {

            System.out.println(stm);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Todo t = new Todo();
                t.setId(rs.getLong("id"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setTargetDate(rs.getDate("targetDate").toLocalDate());
                t.setStatus(rs.getBoolean("status"));

                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setUsername(rs.getString("username"));
                u.setTodo(t);

                t.getUser().add(u);

                todo.add(t);
            }
        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
        return todo;
    }

    public Todo selectTodoByID(Long id) {
        Todo todo = null;

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(SELECT_TODO_BY_ID_SQL);) {
            stm.setLong(1, id);

            System.out.println(stm);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                todo = new Todo();
                todo.setId(rs.getLong("id"));
                todo.setTitle(rs.getString("title"));
                todo.setDescription(rs.getString("description"));
                todo.setTargetDate(rs.getDate("targetDate").toLocalDate());
                todo.setStatus(rs.getBoolean("status"));

//                User u = new User();
//                u.setUserId(rs.getInt("userId"));
//                u.setUsername(rs.getString("username"));
//                u.setTodo(todo);
//
//                todo.getUser().add(u);
            }
        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
        return todo;
    }

    public int insertTodo(Todo todo) throws SQLException {
        int result = 0;
        
        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(INSERT_TODO_SQL);) {
            stm.setString(1, todo.getTitle());
            stm.setString(2, todo.getDescription());
            stm.setDate(3, DBContext.getSQLDate(todo.getTargetDate()));
            stm.setBoolean(4, todo.isStatus());
            stm.setInt(5, 1);

            result = stm.executeUpdate();
        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
        return result;
    }

    public boolean updateTodo(Todo todo) throws SQLException {
        boolean rowUpdated;

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(UPDATE_TODO_SQL);) {
            stm.setString(1, todo.getTitle());
            stm.setString(2, todo.getDescription());
            stm.setDate(3, DBContext.getSQLDate(todo.getTargetDate()));
            stm.setBoolean(4, todo.isStatus());
            stm.setInt(5, 1);
            stm.setLong(6, todo.getId());

            rowUpdated = stm.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteTodo(Long id) throws SQLException {
        boolean rowDeleted;

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(DELETE_TODO_BY_ID_SQL);) {
            stm.setLong(1, id);
            rowDeleted = stm.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
