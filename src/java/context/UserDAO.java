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
import model.Department;
import model.Role;

/**
 *
 * @author kelma
 */
public class UserDAO {

//    Queries của Member
    private static final String REGISTER_USER_SQL = "INSERT INTO pms.user (full_name, "
            + "email, mobile, password) VALUES (?, ?, ?, ?);";
    private static final String LOGIN_USER_SQL = "SELECT * FROM pms.user "
            + "WHERE email = ? AND password = ?;";

//    Queries của Admin
    private static final String SELECT_ALL_USERS_SQL = """
                                                       SELECT u.id, u.full_name, u.email, u.mobile, u.department_id, 
                                                       d.code, u.role_id, r.role_name, u.status
                                                       FROM pms.user u
                                                       INNER JOIN pms.department d ON u.department_id = d.id
                                                       INNER JOIN pms.role r ON u.role_id = r.id
                                                       ORDER BY u.id DESC;""";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM pms.user WHERE userId = ?";
    private static final String INSERT_USER_SQL = "INSERT INTO pms.user (full_name, "
            + "email, mobile, password, status, role_id, department_id)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_USER_SQL = "UPDATE pms.user SET status = ?, "
            + "role_id = ?, department_id = ? WHERE id = ?;";
    private static final String DELETE_USER_SQL = "DELETE FROM pms.user WHERE id = ?;";

//    Member
    public int registerUser(User user) throws ClassNotFoundException {
        int result = 0;

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(REGISTER_USER_SQL)) {
            stm.setString(1, user.getFull_name());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getMobile());
            stm.setString(4, user.getPassword());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    public boolean loginValidate(User user) throws ClassNotFoundException {
//        boolean status = false;
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(LOGIN_USER_SQL)) {
//            stm.setString(1, user.getEmail());
//            stm.setString(2, user.getPassword());
//
//            ResultSet rs = stm.executeQuery();
//            status = rs.next();
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return status;
//    }
    public User loginValidate(User user) throws ClassNotFoundException {
        User foundUser = null;
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(LOGIN_USER_SQL)) {
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                // Giả sử bạn đã có một phương thức để lấy thông tin người dùng từ ID
                int userId = rs.getInt("id"); // Lấy ID người dùng
                foundUser = selectUserByID(userId); // Lấy thông tin người dùng
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return foundUser; // Trả về người dùng đã tìm thấy hoặc null
    }

//    Admin
    public List<User> selectAllUsers() {
        List<User> user = new ArrayList<>();

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(SELECT_ALL_USERS_SQL);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFull_name(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setStatus(rs.getInt("status"));

                Department d = new Department();
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
//                d.setUsers(u);
                u.getDepts().add(d);

                Role r = new Role();
                r.setId(rs.getInt("id"));
                r.setRole_name(rs.getString("role_name"));
//                r.setUsers(u);
                u.getRoles().add(r);

                user.add(u);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return user;
    }

//    public User selectUserByID(int id) {
//        User user = null;
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(SELECT_USER_BY_ID_SQL);) {
//            stm.setInt(1, id);
//
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                user = new User();
//                user.setUserId(rs.getInt("userId"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setFullname(rs.getString("fullname"));
//                user.setEmail(rs.getString("email"));
//                user.setMobile(rs.getString("mobile"));
//                user.setNotes(rs.getString("notes"));
//                user.setRole(rs.getString("role"));
//                user.setStatus(rs.getBoolean("status"));
//            }
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return user;
//    }
//
//    public int insertUser(User user) throws SQLException {
//        int result = 0;
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(INSERT_USER_BY_ADMIN_SQL);) {
//            stm.setString(1, user.getUsername());
//            stm.setString(2, user.getPassword());
//            stm.setString(3, user.getFullname());
//            stm.setString(4, user.getEmail());
//            stm.setString(5, user.getMobile());
//            stm.setString(6, user.getRole());
//
//            result = stm.executeUpdate();
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return result;
//    }
//
//    public boolean updateUser(User user) throws SQLException {
//        boolean rowUpdated;
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(UPDATE_USER_SQL);) {
//            stm.setString(1, user.getUsername());
//            stm.setString(2, user.getPassword());
//            stm.setString(3, user.getFullname());
//            stm.setString(4, user.getEmail());
//            stm.setString(5, user.getMobile());
//            stm.setString(6, user.getRole());
//            stm.setBoolean(7, user.isStatus());
//            stm.setInt(8, user.getUserId());
//
//            rowUpdated = stm.executeUpdate() > 0;
//        }
//
//        return rowUpdated;
//    }
//
//    public boolean deleteUser(int id) throws SQLException {
//        boolean rowDeleted;
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(DELETE_USER_SQL);) {
//            stm.setInt(1, id);
//            rowDeleted = stm.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//
//    public User selectUserByUsername(String username) {
//        User user = null;
//        String query = "SELECT * FROM pms.user WHERE username = ? OR email = ?";
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(query)) {
//            stm.setString(1, username);
//            stm.setString(2, username); // Có thể kiểm tra theo cả username và email
//
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                user = new User();
//                user.setUserId(rs.getInt("userId"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setFullname(rs.getString("fullname"));
//                user.setEmail(rs.getString("email"));
//                user.setMobile(rs.getString("mobile"));
//                user.setRole(rs.getString("role")); // Lấy vai trò
//                user.setStatus(rs.getBoolean("status"));
//            }
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return user;
//    }
    public User selectUserByEmail(String email) {
        User user = null;
        String query = """
                       SELECT u.id, u.full_name, u.email, u.mobile, u.password, u.notes, 
                       u.status, u.department_id, d.code, u.role_id, r.role_name, u.status
                       FROM pms.user u
                       INNER JOIN pms.department d ON u.department_id = d.id
                       INNER JOIN pms.role r ON u.role_id = r.id
                       WHERE u.email = ?;""";
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(query)) {
            stm.setString(1, email);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));

                Department dept = new Department();
                dept.setId(rs.getInt("id"));
                dept.setCode(rs.getString("code"));
                user.getDepts().add(dept);

                // Lấy vai trò
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole_name(rs.getString("role_name"));
                user.getRoles().add(role);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return user;
    }

    public User selectUserByID(int id) {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?"; // Thay đổi tên bảng nếu cần

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setNotes(rs.getString("notes"));
                user.setStatus(rs.getInt("status"));

                // Lấy vai trò của người dùng (nếu có)
                int roleId = rs.getInt("role_id"); // Thay đổi tên trường nếu cần
                Role role = selectRoleByID(roleId); // Phương thức để lấy thông tin vai trò
                user.setRole(role);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return user;
    }

// Thêm phương thức này để lấy vai trò nếu cần
    private Role selectRoleByID(int roleId) {

        Role role = null;
        String sql = "SELECT * FROM role WHERE id = ?";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, roleId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole_name(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return role;
    }

//BachHD
//28/9
//updateMember
// Phương thức cập nhật thông tin người dùng vào cơ sở dữ liệu
    public boolean updateMember(User user) {
        String query = "UPDATE user SET full_name = ?, email = ?, mobile = ? WHERE id = ?";

        try (Connection conn = BaseDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobile());
            ps.setInt(4, user.getId());
            System.out.println("User ID: " + user.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;  // Nếu có ít nhất 1 dòng bị ảnh hưởng thì trả về true

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Trả về false nếu có lỗi
    }

//BachHD
//28/9
//updatepassword
    public boolean updatePassword(int userId, String newPassword) {
        String sql = "UPDATE pms.user SET password = ? WHERE id = ?"; // Đã sửa tên bảng
        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0; // Trả về true nếu có bản ghi được cập nhật
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
            return false;
        }
    }

}