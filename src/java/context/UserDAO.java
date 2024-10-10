/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Department;
import model.Role;
import model.Setting;

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
    String query = """
                   SELECT id, full_name, email, mobile, password, role_id
                   FROM pms.user
                   WHERE email = ? AND password = ?;
                   """;

    try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(query)) {
        stm.setString(1, user.getEmail());
        stm.setString(2, user.getPassword());

        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            foundUser = new User();
            foundUser.setId(rs.getInt("id"));
            foundUser.setFull_name(rs.getString("full_name"));
            foundUser.setEmail(rs.getString("email"));
            foundUser.setMobile(rs.getString("mobile"));
            foundUser.setPassword(rs.getString("password"));
            foundUser.setRole_id(rs.getInt("role_id")); // Set role_id vào user
        }
    } catch (SQLException e) {
        BaseDAO.printSQLException(e);
    }
    return foundUser; // Trả về người dùng đã tìm thấy hoặc null
}


   public User selectUserByEmail(String email) {
    User user = null;
    String query = """
                   SELECT u.id, u.full_name, u.email, u.mobile, u.password, u.notes, 
                   u.status, u.role_id
                   FROM pms.user u
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

            
//            user.setRole(rs.getInt("role_id")); 
        }
    } catch (SQLException e) {
        BaseDAO.printSQLException(e);
    }
    return user;
}


//    public User selectUserByID(int id) {
//        User user = null;
//        String sql = "SELECT * FROM user WHERE id = ?"; // Thay đổi tên bảng nếu cần
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//
//            if (rs.next()) {
//                user = new User();
//                user.setId(rs.getInt("id"));
//                user.setFull_name(rs.getString("full_name"));
//                user.setEmail(rs.getString("email"));
//                user.setMobile(rs.getString("mobile"));
//                user.setPassword(rs.getString("password"));
//                user.setNotes(rs.getString("notes"));
//                user.setStatus(rs.getInt("status"));
//
//                // Lấy vai trò của người dùng (nếu có)
//                int roleId = rs.getInt("role_id"); // Thay đổi tên trường nếu cần
//                Role role = selectRoleByID(roleId); // Phương thức để lấy thông tin vai trò
//                user.setRole(role);
//            }
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//
//        return user;
//    }
// Thêm phương thức này để lấy vai trò nếu cần
//    private Role selectRoleByID(int roleId) {
//
//        Role role = null;
//        String sql = "SELECT * FROM role WHERE id = ?";
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
//            stm.setInt(1, roleId);
//            ResultSet rs = stm.executeQuery();
//
//            if (rs.next()) {
//                role = new Role();
//                role.setId(rs.getInt("id"));
//                role.setRole_name(rs.getString("role_name"));
//                role.setDescription(rs.getString("description"));
//            }
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//
//        return role;
//    }

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
            BaseDAO.printSQLException(e);
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

//    HuyenPTNHE160769
//    25/09/2024        
//    Admin select all users order by user id descending
//        LIMIT = pageSize
//        OFFSET = (pageIndex - 1) * pageSize;
    public List<User> selectAllUsers(String keyword, Integer deptId, Integer roleId, Integer status) {
        List<User> user = new ArrayList<>();

        String sql = """
                     SELECT u.id, u.full_name, u.email, u.mobile, d.id, d.code, s.id, s.name, u.status
                     FROM pms.user u
                     LEFT JOIN pms.dept_user du ON u.id = du.user_id AND du.end_date IS NULL
                     LEFT JOIN pms.department d ON du.dept_id = d.id
                     LEFT JOIN pms.setting s ON u.role_id = s.id                  
                     WHERE 1=1""";
        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(u.full_name) LIKE ? OR LOWER(u.email) LIKE ? OR u.mobile LIKE ?)";
        }
        if (deptId != null) {
            sql += " AND d.id = ?";
        }
        if (roleId != null) {
            sql += " AND s.id = ?";
        }
        if (status != null) {
            sql += " AND u.status = ?";
        }

        sql += " ORDER BY u.id DESC LIMIT 10 OFFSET 0;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";

                stm.setString(index++, "%" + keywordPattern + "%");
                stm.setString(index++, "%" + keywordPattern + "%");
                stm.setString(index++, "%" + keywordPattern + "%");
            }
            if (deptId != null) {
                stm.setInt(index++, deptId);
            }
            if (roleId != null) {
                stm.setInt(index++, roleId);
            }
            if (status != null) {
                stm.setInt(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("u.id"));
                u.setFull_name(rs.getString("u.full_name"));
                u.setEmail(rs.getString("u.email"));
                u.setMobile(rs.getString("u.mobile"));
                u.setStatus(rs.getInt("u.status"));

                Department d = new Department();
                d.setId(rs.getInt("d.id"));
                d.setCode(rs.getString("d.code"));
                u.getDepts().add(d);

                Setting r = new Setting();
                r.setId(rs.getInt("s.id"));
                r.setName(rs.getString("s.name"));
                u.getSettings().add(r);

                user.add(u);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return user;
    }

//    HuyenPTNHE160769
//    25/09/2024        
//    Admin select user by user id
    public User selectUserByID(int id) {
        User u = null;

        String sql = """
                     SELECT u.id, u.full_name, u.email, u.mobile, u.password, d.id, d.name, s.id, s.name, u.notes, u.status
                     FROM pms.user u
                     LEFT JOIN pms.dept_user du ON u.id = du.user_id AND du.end_date IS NULL
                     LEFT JOIN pms.department d ON du.dept_id = d.id
                     LEFT JOIN pms.setting s ON u.role_id = s.id   
                     WHERE u.id = ?;""";
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("u.id"));
                u.setFull_name(rs.getString("u.full_name"));
                u.setEmail(rs.getString("u.email"));
                u.setMobile(rs.getString("u.mobile"));
                u.setPassword(rs.getString("u.password"));
                u.setNotes(rs.getString("u.notes"));
                u.setStatus(rs.getInt("u.status"));

                Department d = new Department();
                d.setId(rs.getInt("d.id"));
                d.setCode(rs.getString("d.name"));
                u.setDept(d);

                Setting s = new Setting();
                s.setId(rs.getInt("s.id"));
                s.setName(rs.getString("s.name"));
                u.setSetting(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return u;
    }

//    HuyenPTNHE160769
//    25/09/2024        
//    Admin add new user
    public int insertUser(User user, Integer deptId, Integer roleId) throws SQLException {
        int userId = 0;
        int result = 0;
        String userSql = """
                         INSERT INTO pms.user (full_name, email, mobile, password, notes, role_id)
                         VALUES (?, ?, ?, ?, ?, ?);""";
        String deptUserSql = """
                            INSERT INTO pms.dept_user (user_id, dept_id, status)
                            VALUES (?, ?, 0);""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement userStm = cnt.prepareStatement(userSql); PreparedStatement deptUserStm = cnt.prepareStatement(deptUserSql);) {
            userStm.setString(1, user.getFull_name());
            userStm.setString(2, user.getEmail());
            userStm.setString(3, user.getMobile());
            userStm.setString(4, user.getPassword());
            userStm.setString(5, user.getNotes());
            // Set role_id (allow null)
            if (roleId != null) {
                userStm.setInt(6, roleId);
            } else {
                userStm.setNull(6, Types.INTEGER);
            }

            result = userStm.executeUpdate();

            ResultSet rs = userStm.executeQuery("SELECT DISTINCT LAST_INSERT_ID() FROM pms.user;");
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            // If deptId is provided, insert into dept_user table
            if (deptId != null) {
                deptUserStm.setInt(1, userId);
                deptUserStm.setInt(2, deptId);

                result += deptUserStm.executeUpdate();
            }

        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    25/09/2024        
//    Admin update an user
    public boolean updateUser(User user, Integer deptId, Integer roleId) throws SQLException {
        boolean rowUpdated = false;

        try (Connection cnt = BaseDAO.getConnection()) {
            switch (user.getStatus()) {
                // If status = 3, dept_user status is not changed
                case 3 -> {
                    String updateUserSql = "UPDATE pms.user SET full_name = ?, email = ?, "
                            + "mobile = ?, notes = ?, role_id = ? "
                            + "WHERE id = ? AND status = 3;";
                    try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                        updateUserStm.setString(1, user.getFull_name());
                        updateUserStm.setString(2, user.getEmail());
                        updateUserStm.setString(3, user.getMobile());
                        updateUserStm.setString(4, user.getNotes());
                        // Set role_id (allow null)
                        if (roleId != null) {
                            updateUserStm.setInt(5, roleId);
                        } else {
                            updateUserStm.setNull(5, Types.INTEGER);
                        }
                        updateUserStm.setInt(6, user.getId());

                        rowUpdated = updateUserStm.executeUpdate() > 0;
                    }
                    
                    if (deptId != null) {
                        // Update current record in dept_user
                        String updateDeptUserSql = "UPDATE pms.dept_user SET dept_id = ? WHERE user_id = ? AND end_date IS NULL";
                        try (PreparedStatement updateDeptUserStm = cnt.prepareStatement(updateDeptUserSql)) {
                            updateDeptUserStm.setInt(1, user.getDept().getId());
                            updateDeptUserStm.setInt(2, user.getId());
                            updateDeptUserStm.executeUpdate();

                            updateDeptUserStm.executeUpdate();
                        }

                        // Insert new record in dept_user if record of this user not exist
                        String insertDeptUserSql = """
                                               INSERT INTO pms.dept_user (user_id, dept_id) 
                                               SELECT ?, ?
                                               WHERE NOT EXISTS (
                                                   SELECT 1 FROM pms.dept_user 
                                                   WHERE user_id = ? AND end_date IS NULL
                                               );""";
                        try (PreparedStatement insertDeptUserStm = cnt.prepareStatement(insertDeptUserSql)) {
                            insertDeptUserStm.setInt(1, user.getId());
                            insertDeptUserStm.setInt(2, user.getDept().getId());
                            insertDeptUserStm.setInt(3, user.getId());

                            insertDeptUserStm.executeUpdate();
                        }
                    }
                }

                // If status = 1 or changes to 1, and dept_id is changed
                case 1 -> {
                    // Update user
                    String updateUserSql = "UPDATE pms.user SET full_name = ?, email = ?, "
                            + "mobile = ?, notes = ?, status = ?, role_id = ? WHERE id = ?;";
                    try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                        updateUserStm.setString(1, user.getFull_name());
                        updateUserStm.setString(2, user.getEmail());
                        updateUserStm.setString(3, user.getMobile());
                        updateUserStm.setString(4, user.getNotes());
                        updateUserStm.setInt(5, user.getStatus());
                        // Set role_id (allow null)
                        if (roleId != null) {
                            updateUserStm.setInt(6, roleId);
                        } else {
                            updateUserStm.setNull(6, Types.INTEGER);
                        }
                        updateUserStm.setInt(7, user.getId());

                        rowUpdated = updateUserStm.executeUpdate() > 0;
                    }

                    if (deptId != null) {
                        // Close current record in dept_user
                        String closeDeptUserSql = "UPDATE pms.dept_user SET end_date = CURDATE(), "
                                + "status = 0 WHERE user_id = ? AND end_date IS NULL";
                        try (PreparedStatement closeDeptUserStm = cnt.prepareStatement(closeDeptUserSql)) {
                            closeDeptUserStm.setInt(1, user.getId());
                            closeDeptUserStm.executeUpdate();

                            closeDeptUserStm.executeUpdate();
                        }

                        // Insert new record in dept_user
                        String insertDeptUserSql = "INSERT INTO pms.dept_user (user_id, dept_id, start_date, status) VALUES (?, ?, CURDATE(), 1)";
                        try (PreparedStatement insertDeptUserStm = cnt.prepareStatement(insertDeptUserSql)) {
                            insertDeptUserStm.setInt(1, user.getId());
                            insertDeptUserStm.setInt(2, user.getDept().getId());

                            insertDeptUserStm.executeUpdate();
                        }
                    }
                }

                // If status = 0 or changes to 0
                case 0 -> {
                    // Update user
                    String updateUserSql = "UPDATE pms.user SET full_name = ?, email = ?, "
                            + "mobile = ?, notes = ?, status = ? WHERE id = ?;";
                    try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                        updateUserStm.setString(1, user.getFull_name());
                        updateUserStm.setString(2, user.getEmail());
                        updateUserStm.setString(3, user.getMobile());
                        updateUserStm.setString(4, user.getNotes());
                        updateUserStm.setInt(5, user.getStatus());
                        updateUserStm.setInt(6, user.getId());

                        rowUpdated = updateUserStm.executeUpdate() > 0;
                    }

                    // Close current record in dept_user
                    String closeDeptUserSql = "UPDATE pms.dept_user SET status = 0, "
                            + "end_date = CURRENT_DATE WHERE user_id = ? AND end_date IS NULL";
                    try (PreparedStatement closeDeptUserStm = cnt.prepareStatement(closeDeptUserSql)) {
                        closeDeptUserStm.setInt(1, user.getId());
                        closeDeptUserStm.executeUpdate();
                    }
                }
                default -> {
                }
            }

        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return rowUpdated;
    }

//    HuyenPTNHE160769
//    25/09/2024        
//    Admin change status of an user
    public boolean changeStatusUser(User user) throws SQLException {
        boolean rowUpdated = false;
        try (Connection cnt = BaseDAO.getConnection()) {
            // Update user
            String updateUserSql = "UPDATE pms.user SET status = ? WHERE id = ?";
            try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                updateUserStm.setInt(1, user.getStatus());
                updateUserStm.setInt(2, user.getId());

                rowUpdated = updateUserStm.executeUpdate() > 0;
            }

            // If status changes to 0, Close current record in dept_user
            if (user.getStatus() == 0) {
                String closeDeptUserSql = "UPDATE pms.dept_user SET status = 0, end_date = CURDATE() "
                        + "WHERE user_id = ? AND end_date IS NULL";
                try (PreparedStatement closeDeptUserStm = cnt.prepareStatement(closeDeptUserSql)) {
                    closeDeptUserStm.setInt(1, user.getId());
                    closeDeptUserStm.executeUpdate();
                }
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

}
