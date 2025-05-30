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
import model.Allocation;
import model.User;
import model.Department;
import model.Setting;

/**
 *
 * @author kelma
 */
public class UserDAO {

    //BachHD
    public int registerUser(User user) throws ClassNotFoundException {
        int result = 0;

        // Kiểm tra xem email hoặc username đã tồn tại chưa
        if (checkEmailExist(user.getEmail()) || checkUsernameExist(user.getUsername())) {
            return -1; // Trả về -1 nếu email hoặc username đã tồn tại
        }

        // Thực hiện đăng ký nếu email và username chưa tồn tại
        String REGISTER_USER_SQL = "INSERT INTO user (full_name, username, email, password, role_id) VALUES (?, ?, ?, ?, 5)";
        try (Connection connection = BaseDAO.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_USER_SQL)) {

            preparedStatement.setString(1, user.getFull_name());
            preparedStatement.setString(2, user.getUsername()); // Thiết lập username
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return result; // Trả về 1 nếu đăng ký thành công
    }

    public User loginValidate(User user) throws ClassNotFoundException {
        User foundUser = null;
        String query = """
               SELECT u.id, u.full_name, u.username, u.email, u.mobile, u.password, u.role_id, u.status, s.name, u.avatar
               FROM user u 
               JOIN setting s ON u.role_id = s.id
               WHERE u.email = ? AND u.password = ?;
               """;

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(query)) {
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                foundUser = new User();
                foundUser.setId(rs.getInt("id"));
                foundUser.setFull_name(rs.getString("full_name"));
                foundUser.setUsername(rs.getString("username"));
                foundUser.setEmail(rs.getString("email"));
                foundUser.setMobile(rs.getString("mobile"));
                foundUser.setPassword(rs.getString("password"));
                foundUser.setRole_id(rs.getInt("role_id")); // Set role_id vào user
                foundUser.setRole_name(rs.getString("name")); // Set role_name vào user
                foundUser.setStatus(rs.getInt("status")); // Set status vào user
                foundUser.setAvatar(rs.getString("avatar"));
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
                   FROM user u
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

//BachHD
//28/9
//updateMember
// Phương thức cập nhật thông tin người dùng vào cơ sở dữ liệu
    public boolean updateMember(User user) {
        String query = "UPDATE user SET full_name = ?, email = ?, mobile = ?, username = ? WHERE id = ?";

        try (Connection conn = BaseDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobile());
            ps.setString(4, user.getUsername()); // Thiết lập giá trị cho username
            ps.setInt(5, user.getId());
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
        String sql = "UPDATE user SET password = ? WHERE id = ?"; // Đã sửa tên bảng
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
//    Admin select all users 
    public List<User> selectAllUsers(String keyword, Integer deptId, Integer roleId, Integer status) {
        List<User> user = new ArrayList<>();

        String sql = """
                     SELECT u.id, u.full_name, u.username, u.email, u.mobile, 
                     d.id, d.code, s.id, s.name, u.status
                     FROM user u
                     LEFT JOIN dept_user du ON u.id = du.user_id AND du.end_date IS NULL
                     LEFT JOIN department d ON du.dept_id = d.id
                     LEFT JOIN setting s ON u.role_id = s.id                  
                     WHERE 1=1""";
        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(u.full_name) LIKE ? "
                    + "OR LOWER(u.username) LIKE ? "
                    + "OR LOWER(u.email) LIKE ? "
                    + "OR u.mobile LIKE ?)";
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
        sql += " ORDER BY u.id DESC;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";

                stm.setString(index++, keywordPattern);
                stm.setString(index++, keywordPattern);
                stm.setString(index++, keywordPattern);
                stm.setString(index++, keywordPattern);
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
                u.setUsername(rs.getString("u.username"));
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
                     SELECT u.id, u.full_name, u.username, u.email, u.mobile, 
                     u.password, d.id, d.name, s.id, s.name, u.notes, u.status
                     FROM user u
                     LEFT JOIN dept_user du ON u.id = du.user_id AND du.end_date IS NULL
                     LEFT JOIN department d ON du.dept_id = d.id
                     LEFT JOIN setting s ON u.role_id = s.id   
                     WHERE u.id = ?;""";
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("u.id"));
                u.setFull_name(rs.getString("u.full_name"));
                u.setUsername(rs.getString("u.username"));
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
                         INSERT INTO user (full_name, username, email, mobile, password, notes, role_id)
                         VALUES (?, ?, ?, ?, ?, ?, ?);""";
        String deptUserSql = """
                            INSERT INTO dept_user (user_id, dept_id, role_id, status)
                            VALUES (?, ?, ?, 0);""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement userStm = cnt.prepareStatement(userSql); PreparedStatement deptUserStm = cnt.prepareStatement(deptUserSql);) {
            userStm.setString(1, user.getFull_name());
            userStm.setString(2, user.getUsername());
            userStm.setString(3, user.getEmail());
            userStm.setString(4, user.getMobile());
            userStm.setString(5, user.getPassword());
            userStm.setString(6, user.getNotes());
            // Set role_id (allow null)
            if (roleId != null) {
                userStm.setInt(7, roleId);
            } else {
                userStm.setNull(7, Types.INTEGER);
            }

            result = userStm.executeUpdate();

            ResultSet rs = userStm.executeQuery("SELECT DISTINCT LAST_INSERT_ID() FROM user;");
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            // If deptId is provided, insert into dept_user table
            if (deptId != null) {
                deptUserStm.setInt(1, userId);
                deptUserStm.setInt(2, deptId);
                // Set role_id (allow null)
                if (roleId != null) {
                    userStm.setInt(3, roleId);
                } else {
                    userStm.setNull(3, Types.INTEGER);
                }

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
                    String updateUserSql = "UPDATE user SET full_name = ?, username = ?, "
                            + "email = ?, mobile = ?, notes = ?, role_id = ? "
                            + "WHERE id = ? AND status = 3;";
                    try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                        updateUserStm.setString(1, user.getFull_name());
                        updateUserStm.setString(2, user.getUsername());
                        updateUserStm.setString(3, user.getEmail());
                        updateUserStm.setString(4, user.getMobile());
                        updateUserStm.setString(5, user.getNotes());
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
                        // Update current record in dept_user
                        String updateDeptUserSql = "UPDATE dept_user SET dept_id = ?, role_id = ? WHERE user_id = ? AND end_date IS NULL";
                        try (PreparedStatement updateDeptUserStm = cnt.prepareStatement(updateDeptUserSql)) {
                            updateDeptUserStm.setInt(1, user.getDept().getId());
                            // Set role_id (allow null)
                            if (roleId != null) {
                                updateDeptUserStm.setInt(2, roleId);
                            } else {
                                updateDeptUserStm.setNull(2, Types.INTEGER);
                            }
                            updateDeptUserStm.setInt(3, user.getId());
                            updateDeptUserStm.executeUpdate();

                            updateDeptUserStm.executeUpdate();
                        }

                        // Insert new record in dept_user if record of this user not exist
                        String insertDeptUserSql = """
                                               INSERT INTO dept_user (user_id, dept_id, role_id) 
                                               SELECT ?, ?, ?
                                               WHERE NOT EXISTS (
                                                   SELECT 1 FROM dept_user 
                                                   WHERE user_id = ? AND end_date IS NULL
                                               );""";
                        try (PreparedStatement insertDeptUserStm = cnt.prepareStatement(insertDeptUserSql)) {
                            insertDeptUserStm.setInt(1, user.getId());
                            insertDeptUserStm.setInt(2, user.getDept().getId());
                            // Set role_id (allow null)
                            if (roleId != null) {
                                insertDeptUserStm.setInt(3, roleId);
                            } else {
                                insertDeptUserStm.setNull(3, Types.INTEGER);
                            }
                            insertDeptUserStm.setInt(4, user.getId());

                            insertDeptUserStm.executeUpdate();
                        }
                    }
                }

                // If status = 1 or changes to 1, and dept_id is changed
                case 1 -> {
                    // Update user
                    String updateUserSql = "UPDATE user SET full_name = ?, username = ?, email = ?, "
                            + "mobile = ?, notes = ?, status = ?, role_id = ? WHERE id = ?;";
                    try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                        updateUserStm.setString(1, user.getFull_name());
                        updateUserStm.setString(2, user.getUsername());
                        updateUserStm.setString(3, user.getEmail());
                        updateUserStm.setString(4, user.getMobile());
                        updateUserStm.setString(5, user.getNotes());
                        updateUserStm.setInt(6, user.getStatus());
                        // Set role_id (allow null)
                        if (roleId != null) {
                            updateUserStm.setInt(7, roleId);
                        } else {
                            updateUserStm.setNull(7, Types.INTEGER);
                        }
                        updateUserStm.setInt(8, user.getId());

                        rowUpdated = updateUserStm.executeUpdate() > 0;
                    }

                    if (deptId != null) {
                        // Query to get the current dept_id from dept_user
                        String selectDeptIdSql = "SELECT dept_id FROM dept_user WHERE user_id = ? AND end_date IS NULL";
                        Integer currentDeptId = null;

                        try (PreparedStatement selectDeptIdStm = cnt.prepareStatement(selectDeptIdSql)) {
                            selectDeptIdStm.setInt(1, user.getId());
                            try (ResultSet rs = selectDeptIdStm.executeQuery()) {
                                if (rs.next()) {
                                    currentDeptId = rs.getInt("dept_id");
                                }
                            }
                        }

                        // Only update if deptId is different from currentDeptId
                        if (!deptId.equals(currentDeptId)) {
                            // Close current record in dept_user
                            String closeDeptUserSql = "UPDATE dept_user SET end_date = CURDATE(), "
                                    + "status = 0 WHERE user_id = ? AND end_date IS NULL";
                            try (PreparedStatement closeDeptUserStm = cnt.prepareStatement(closeDeptUserSql)) {
                                closeDeptUserStm.setInt(1, user.getId());

                                closeDeptUserStm.executeUpdate();
                            }

                            // Insert new record in dept_user
                            String insertDeptUserSql = "INSERT INTO dept_user (user_id, dept_id, start_date, status, role_id) VALUES (?, ?, CURDATE(), 1, ?)";
                            try (PreparedStatement insertDeptUserStm = cnt.prepareStatement(insertDeptUserSql)) {
                                insertDeptUserStm.setInt(1, user.getId());
                                insertDeptUserStm.setInt(2, user.getDept().getId());
                                // Set role_id (allow null)
                                if (roleId != null) {
                                    insertDeptUserStm.setInt(3, roleId);
                                } else {
                                    insertDeptUserStm.setNull(3, Types.INTEGER);
                                }

                                insertDeptUserStm.executeUpdate();
                            }
                        }
                    }
                }

                // If status = 0 or changes to 0
                case 0 -> {
                    // Update user
                    String updateUserSql = "UPDATE user SET full_name = ?, username = ?, email = ?, "
                            + "mobile = ?, notes = ?, status = ?, role_id = ? WHERE id = ?;";
                    try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                        updateUserStm.setString(1, user.getFull_name());
                        updateUserStm.setString(2, user.getUsername());
                        updateUserStm.setString(3, user.getEmail());
                        updateUserStm.setString(4, user.getMobile());
                        updateUserStm.setString(5, user.getNotes());
                        updateUserStm.setInt(6, user.getStatus());
                        // Set role_id (allow null)
                        if (roleId != null) {
                            updateUserStm.setInt(7, roleId);
                        } else {
                            updateUserStm.setNull(7, Types.INTEGER);
                        }
                        updateUserStm.setInt(8, user.getId());

                        rowUpdated = updateUserStm.executeUpdate() > 0;
                    }

                    // Close current record in dept_user
                    String closeDeptUserSql = "UPDATE dept_user SET status = 0, "
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
            String updateUserSql = "UPDATE user SET status = ? WHERE id = ?";
            try (PreparedStatement updateUserStm = cnt.prepareStatement(updateUserSql)) {
                updateUserStm.setInt(1, user.getStatus());
                updateUserStm.setInt(2, user.getId());

                rowUpdated = updateUserStm.executeUpdate() > 0;
            }

            // If status changes to 0, Close current record in dept_user
            if (user.getStatus() == 0) {
                String closeDeptUserSql = "UPDATE dept_user SET status = 0, end_date = CURDATE() "
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

    //BachHD
    // Phương thức kiểm tra email đã tồn tại
    public boolean checkEmailExist(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (Connection con = BaseDAO.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu email đã tồn tại
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return false;
    }

    //BachHD
    // Phương thức kiểm tra username đã tồn tại
    public boolean checkUsernameExist(String username) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (Connection con = BaseDAO.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu username đã tồn tại
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return false;
    }

    public User getUserBySessionId(int userId) {
        String query = "SELECT u.id, u.username, u.password, u.full_name, u.email, u.role_id, u.mobile, u.avatar, "
                + "s.name AS role_name, d.name AS department_name "
                + "FROM user u "
                + "JOIN setting s ON u.role_id = s.id "
                + "LEFT JOIN dept_user du ON u.id = du.user_id "
                + "LEFT JOIN department d ON du.dept_id = d.id "
                + "WHERE u.id = ?";
        try (Connection con = BaseDAO.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Khởi tạo đối tượng User từ kết quả truy vấn
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFull_name(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setMobile(rs.getString("mobile"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRole_id(rs.getInt("role_id"));
                    user.setRole_name(rs.getString("role_name")); // Lấy tên vai trò
                    user.setDepartment(rs.getString("department_name")); // Lấy tên phòng ban
                    return user;
                }
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return null; // Trả về null nếu không tìm thấy hoặc xảy ra lỗi
    }

    //HuyenPTNHE160769
    // Change avatar
    public boolean changeAvatar(User user) throws SQLException {
        boolean rowUpdated = false;
        String sql = "UPDATE user SET avatar = ? WHERE id = ?";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setString(1, user.getAvatar());
            stm.setInt(2, user.getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

    //    Admin select all users 
    public List<User> selectAllDeptManagers() {
        List<User> user = new ArrayList<>();

        String sql = """
                     SELECT id, full_name, username, email
                     FROM user               
                     WHERE role_id = 3 AND status = 1
                     ORDER BY id DESC;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFull_name(rs.getString("full_name"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));

                user.add(u);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return user;
    }

    public void updateUserStatus(String email, int status) {
        String sql = "UPDATE user SET status = ? WHERE email = ?";
        try (Connection connection = BaseDAO.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, status); // Trạng thái (0, 1, hoặc 2)
            statement.setString(2, email); // Email người dùng

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User status updated successfully for email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> selectAllMembers(Integer deptId) {
        List<User> user = new ArrayList<>();

        String sql = """
                     SELECT u.id, u.full_name, u.username, u.email, u.mobile, 
                     d.id, d.code, s.id, s.name, u.status, COUNT(DISTINCT a.project_id) AS total_projects
                     FROM user u
                     LEFT JOIN dept_user du ON u.id = du.user_id AND du.end_date IS NULL
                     LEFT JOIN department d ON du.dept_id = d.id
                     LEFT JOIN setting s ON u.role_id = s.id   
                     LEFT JOIN allocation a ON u.id = a.user_id
                     WHERE u.status = 1 AND u.role_id = 5""";
        // Add search conditions if any
        if (deptId != null) {
            sql += " AND d.id = ?";
        }
        sql += " GROUP BY u.id, u.full_name, u.username, u.email, u.mobile, d.id, d.code, s.id, s.name, u.status;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            if (deptId != null) {
                stm.setInt(1, deptId);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("u.id"));
                u.setFull_name(rs.getString("u.full_name"));
                u.setUsername(rs.getString("u.username"));
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

                Allocation a = new Allocation();
                a.setTotal_projects(rs.getInt("total_projects"));
                u.setAllocation(a);

                user.add(u);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return user;
    }
}
