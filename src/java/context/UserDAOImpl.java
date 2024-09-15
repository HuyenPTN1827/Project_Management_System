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
import model.User;

/**
 *
 * @author kelma
 */
public class UserDAOImpl implements UserDAO {

    private static final String INSERT_USER_SQL = "INSERT INTO pms.user (username, "
            + "password, fullname, email, mobile)\n"
            + "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_UNAME_PASS = "SELECT * FROM pms.user \n"
            + "WHERE (username = ? OR email = ?) "
            + "AND password = ?";
    private static final String UPDATE_USER_SQL = "UPDATE pms.user "
            + "SET username = ?, password = ?, fullname = ?, email = ?, mobile = ?, "
            + "role = ?, status = ?\n"
            + "WHERE userId = ?";
    private static final String DELETE_USER_SQL = "DELETE FROM pms.user WHERE userId = ?";
    private static final String SELECT_ALL_USERS_SQL = "SELECT userId, username, "
            + "email, mobile, role, status\n"
            + "FROM pms.user ORDER BY userId DESC;";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM pms.user WHERE userId = ?";
    private static final String INSERT_USER_BY_ADMIN_SQL = "INSERT INTO pms.user "
            + "(username, password, fullname, email, mobile, role)\n"
            + "VALUES (?, ?, ?, ?, ?, ?)";

    public int registerUser(User user) throws ClassNotFoundException {
        int result = 0;

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(INSERT_USER_SQL)) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getFullname());
            stm.setString(4, user.getEmail());
            stm.setString(5, user.getMobile());

            System.out.println(stm);

            result = stm.executeUpdate();

        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
        return result;
    }

    public boolean loginValidate(User user) throws ClassNotFoundException {
        boolean status = false;

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(SELECT_USER_BY_UNAME_PASS)) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());

            System.out.println(stm);

            ResultSet rs = stm.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
        return status;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> user = new ArrayList<>();

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(SELECT_ALL_USERS_SQL);) {

            System.out.println(stm);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setRole(rs.getString("role"));
                u.setStatus(rs.getBoolean("status"));

                user.add(u);
            }
        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
        return user;
    }

    @Override
    public User selectUserByID(int id) {
        User user = null;

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(SELECT_USER_BY_ID_SQL);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setNotes(rs.getString("notes"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
        return user;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(INSERT_USER_BY_ADMIN_SQL);) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getFullname());
            stm.setString(4, user.getEmail());
            stm.setString(5, user.getMobile());
            stm.setString(6, user.getRole());

            stm.executeUpdate();
        } catch (SQLException e) {
            DBContext.printSQLException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;

        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(UPDATE_USER_SQL);) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getFullname());
            stm.setString(4, user.getEmail());
            stm.setString(5, user.getMobile());
            stm.setString(6, user.getRole());
            stm.setBoolean(7, user.isStatus());
            stm.setInt(8, user.getUserId());

            rowUpdated = stm.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try ( Connection cnt = DBContext.getConnection();  PreparedStatement stm = cnt.prepareStatement(DELETE_USER_SQL);) {
            stm.setInt(1, id);
            rowDeleted = stm.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
