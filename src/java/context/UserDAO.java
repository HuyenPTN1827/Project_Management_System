/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.SQLException;
import java.util.List;
import model.User;

/**
 *
 * @author kelma
 */
public interface UserDAO {

    List<User> selectAllUsers();

    User selectUserByID(int id);

    void insertUser(User user) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUser(int id) throws SQLException;
}
