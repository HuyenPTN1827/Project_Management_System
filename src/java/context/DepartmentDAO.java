/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import context.BaseDAO.MyDateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Department;
import model.Department_User;
import model.Setting;
import model.User;

/**
 *
 * @author kelma
 */
public class DepartmentDAO {

//    HuyenPTNHE160769    
//    Select all sctive depts 
    public List<Department> selectAllActiveDepartments() {
        List<Department> dept = new ArrayList<>();

        String sql = """
                     SELECT d1.id, d1.code, d1.name, d1.details, d1.parent, 
                     d1.status, d2.code, d2.name 
                     FROM department d1
                     LEFT JOIN department d2 ON d1.parent = d2.id
                     WHERE d1.status = 1""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("d1.id"));
                d.setCode(rs.getString("d1.code"));
                d.setName(rs.getString("d1.name"));
                d.setDetails(rs.getString("d1.details"));
                d.setParentId(rs.getInt("d1.parent"));
                d.setParentName(rs.getString("d2.name"));
                d.setParentCode(rs.getString("d2.code"));
                d.setStatus(rs.getBoolean("d1.status"));

                dept.add(d);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return dept;
    }
    
    public List<Department> selectAllDepartments(String keyword, Boolean status) {
        List<Department> dept = new ArrayList<>();

        String sql = """
                     SELECT d1.id, d1.code, d1.name, d1.details, d1.parent, 
                     d1.status, d2.code, d2.name 
                     FROM department d1
                     LEFT JOIN department d2 ON d1.parent = d2.id
                     WHERE 1=1""";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(d1.code) LIKE ? OR LOWER(d1.name) LIKE ?)";
        }
        if (status != null) {
            sql += " AND d1.status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";

                stm.setString(index++, keywordPattern);
                stm.setString(index++, keywordPattern);
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("d1.id"));
                d.setCode(rs.getString("d1.code"));
                d.setName(rs.getString("d1.name"));
                d.setDetails(rs.getString("d1.details"));
                d.setParentId(rs.getInt("d1.parent"));
                d.setParentName(rs.getString("d2.name"));
                d.setParentCode(rs.getString("d2.code"));
                d.setStatus(rs.getBoolean("d1.status"));

                dept.add(d);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return dept;
    }

//    HuyenPTNHE160769
//    04/10/2024       
//    Admin select dept by id
    public Department selectDepartmentByID(int id) {
        Department d = null;

        String sql = """
                     SELECT d1.id, d1.code, d1.name, d1.details, d1.parent, d1.status, d2.code, d2.name 
                     FROM department d1
                     LEFT JOIN department d2 ON d1.parent = d2.id
                     WHERE d1.id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                d = new Department();
                d.setId(rs.getInt("d1.id"));
                d.setCode(rs.getString("d1.code"));
                d.setName(rs.getString("d1.name"));
                d.setDetails(rs.getString("d1.details"));
                d.setParentId(rs.getInt("d1.parent"));
                d.setParentName(rs.getString("d2.name"));
                d.setParentCode(rs.getString("d2.code"));
                d.setStatus(rs.getBoolean("d1.status"));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return d;
    }

//    HuyenPTNHE160769
//    04/10/2024       
//    Admin add new dept
    public int insertDepartment(Department dept, Integer parent) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO department (code, name, details, status, parent) "
                + "VALUES (?, ?, ?, ?, ?);";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, dept.getCode());
            stm.setString(2, dept.getName());
            stm.setString(3, dept.getDetails());
            stm.setBoolean(4, dept.isStatus());
            // Set parent (allow null)
            if (parent != null) {
                stm.setInt(5, parent);
            } else {
                stm.setNull(5, Types.INTEGER);
            }

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    04/10/2024         
//    Admin update a dept
    public boolean updateDepartment(Department dept, Integer parent) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE department SET code = ?, name = ?, details = ?, "
                + "parent = ?, status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, dept.getCode());
            stm.setString(2, dept.getName());
            stm.setString(3, dept.getDetails());
            // Set parent (allow null)
            if (parent != null) {
                stm.setInt(4, parent);
            } else {
                stm.setNull(4, Types.INTEGER);
            }
            stm.setBoolean(5, dept.isStatus());
            stm.setInt(6, dept.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    04/10/2024      
//    Admin change status of a dept
    public boolean changeStatusDepartment(Department dept) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE department SET status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setBoolean(1, dept.isStatus());
            stm.setInt(2, dept.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    31/10/2024      
//    Admin select all dept users
    public List<Department_User> selectAllDepartmentUsers(String keyword, Integer roleId, Boolean status, int deptId) {
        List<Department_User> deptUsers = new ArrayList<>();

        String sql = """
                     SELECT du.id, du.user_id, u.full_name, du.dept_id, 
                     du.role_id, s.name, du.start_date, du.end_date, du.status
                     FROM dept_user du
                     INNER JOIN user u ON du.user_id = u.id
                     INNER JOIN department d ON du.dept_id = d.id 
                     INNER JOIN setting s ON du.role_id = s.id AND s.name = 'Department Manager'
                     WHERE du.dept_id = ?""";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND LOWER(u.full_name) LIKE ?";
        }
        if (roleId != null) {
            sql += " AND du.role_id = ?";
        }
        if (status != null) {
            sql += " AND du.status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, deptId);

            int index = 2;
            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";
                stm.setString(index++, keywordPattern);
            }
            if (roleId != null) {
                stm.setInt(index++, roleId);
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department_User du = new Department_User();
                du.setId(rs.getInt("du.id"));

                // Handle potential null values for dates
                Date startDate = rs.getDate("du.start_date");
                if (startDate != null) {
                    du.setStart_date(MyDateUtil.getUtilDate((java.sql.Date) startDate));
                }

                Date endDate = rs.getDate("du.end_date");
                if (endDate != null) {
                    du.setEnd_date(MyDateUtil.getUtilDate((java.sql.Date) endDate));
                }

                du.setStatus(rs.getBoolean("du.status"));

                User u = new User();
                u.setId(rs.getInt("du.user_id"));
                u.setFull_name(rs.getString("u.full_name"));
                du.setUser(u);

                Department d = new Department();
                d.setId(rs.getInt("du.dept_id"));
                du.setDept(d);

                Setting s = new Setting();
                s.setId(rs.getInt("du.role_id"));
                s.setName(rs.getString("s.name"));
                du.setSetting(s);

                deptUsers.add(du);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return deptUsers;
    }

//    HuyenPTNHE160769
//    31/10/2024      
//    Admin change status of a dept users
    public boolean changeStatusDepartmentUser(Department_User deptUser) throws SQLException {
        boolean rowUpdated = false;

        String activateSql = "UPDATE dept_user SET status = ?, end_date = NULL WHERE id = ? AND dept_id = ?;";
        String deactivateSql = "UPDATE dept_user SET status = ?, end_date = CURDATE() WHERE id = ? AND dept_id = ?;";

        try (Connection cnt = BaseDAO.getConnection()) {
            PreparedStatement stm;
            if (!deptUser.isStatus()) { // Check if status is false
                stm = cnt.prepareStatement(deactivateSql);
                stm.setBoolean(1, deptUser.isStatus());  // Change to inactive
            } else {  // Check if status is true
                stm = cnt.prepareStatement(activateSql);
                stm.setBoolean(1, deptUser.isStatus()); // Change to active
            }
            stm.setInt(2, deptUser.getId());
            stm.setInt(3, deptUser.getDept().getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    31/10/2024       
//    Admin add new dept user
    public int insertDepartmentUser(Department_User ptUser) throws SQLException {
        int result = 0;
        String sql = """
                     INSERT INTO dept_user (user_id, dept_id, start_date, status, role_id)
                     VALUES (?, ?, CURDATE(), 1, ?);""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, ptUser.getUser().getId());
            stm.setInt(2, ptUser.getDept().getId());
            stm.setInt(3, ptUser.getSetting().getId());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    31/10/2024      
//    Admin select dept user by id
    public Department_User selectDepartmentUserByID(int id) {
        Department_User du = null;

        String sql = """
                     SELECT du.id, du.user_id, u.full_name, u.username, u.email, u.mobile, du.dept_id, d.code, 
                     du.role_id, s.name, du.start_date, du.end_date, du.status
                     FROM dept_user du
                     INNER JOIN user u ON du.user_id = u.id
                     INNER JOIN department d ON du.dept_id = d.id
                     INNER JOIN setting s ON du.role_id = s.id
                     WHERE du.id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                du = new Department_User();
                du.setId(rs.getInt("du.id"));

                // Handle potential null values for dates
                Date startDate = rs.getDate("du.start_date");
                if (startDate != null) {
                    du.setStart_date(MyDateUtil.getUtilDate((java.sql.Date) startDate));
                }

                Date endDate = rs.getDate("du.end_date");
                if (endDate != null) {
                    du.setEnd_date(MyDateUtil.getUtilDate((java.sql.Date) endDate));
                }

                du.setStatus(rs.getBoolean("du.status"));

                User u = new User();
                u.setId(rs.getInt("du.user_id"));
                u.setFull_name(rs.getString("u.full_name"));
                u.setUsername(rs.getString("u.username"));
                u.setEmail(rs.getString("u.email"));
                u.setMobile(rs.getString("u.mobile"));
                du.setUser(u);

                Department d = new Department();
                d.setId(rs.getInt("du.dept_id"));
                d.setCode(rs.getString("d.code"));
                du.setDept(d);

                Setting s = new Setting();
                s.setId(rs.getInt("du.role_id"));
                s.setName(rs.getString("s.name"));
                du.setSetting(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return du;
    }

//    HuyenPTNHE160769
//    31/10/2024         
//    Admin update a dept user
    public boolean updateDepartmentUser(Department_User deptUser) throws SQLException {
        boolean rowUpdated = false;

        String activateSql = "UPDATE dept_user SET status = ?, role_id = ?, end_date = NULL WHERE id = ? AND dept_id = ?;";
        String deactivateSql = "UPDATE dept_user SET status = ?, role_id = ?, end_date = CURDATE() WHERE id = ? AND dept_id = ?;";

        try (Connection cnt = BaseDAO.getConnection()) {
            PreparedStatement stm;
            if (!deptUser.isStatus()) { // Check if status is false
                stm = cnt.prepareStatement(deactivateSql);
                stm.setBoolean(1, deptUser.isStatus());  // Change to inactive
            } else {  // Check if status is true
                stm = cnt.prepareStatement(activateSql);
                stm.setBoolean(1, deptUser.isStatus()); // Change to active
            }
            stm.setInt(2, deptUser.getSetting().getId());
            stm.setInt(3, deptUser.getId());
            stm.setInt(4, deptUser.getDept().getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }
}
