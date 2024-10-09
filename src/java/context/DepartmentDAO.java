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
import model.Department;

/**
 *
 * @author kelma
 */
public class DepartmentDAO {

    // HuyenPTNHE160769
    // 29/09/2024
    // Get depts list
    public List<Department> getDepartmentList() {
        List<Department> dept = new ArrayList<>();
        String sql = "SELECT * FROM pms.department WHERE status = 1;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
                d.setName(rs.getString("name"));
                d.setDetails(rs.getString("details"));
                d.setParent(rs.getInt("parent"));
                d.setStatus(rs.getBoolean("status"));
                dept.add(d);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return dept;
    }

//    HuyenPTNHE160769
//    04/10/2024        
//    Admin select all depts order by id descending
//        LIMIT = pageSize
//        OFFSET = (pageIndex - 1) * pageSize;
    public List<Department> selectAllDepartments(String keyword, Boolean status) {
        List<Department> dept = new ArrayList<>();

        String sql = "SELECT * FROM pms.department WHERE 1=1";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(code) LIKE ? OR LOWER(name) LIKE ?)";
        }
        if (status != null) {
            sql += " AND status = ?";
        }

        sql += " ORDER BY id DESC LIMIT 10 OFFSET 0;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";

                stm.setString(index++, "%" + keywordPattern + "%");
                stm.setString(index++, "%" + keywordPattern + "%");
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
                d.setName(rs.getString("name"));
                d.setDetails(rs.getString("details"));
                d.setParent(rs.getInt("parent"));
                d.setStatus(rs.getBoolean("status"));

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

        String sql = "SELECT * FROM pms.department WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                d = new Department();
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
                d.setName(rs.getString("name"));
                d.setDetails(rs.getString("details"));
                d.setParent(rs.getInt("parent"));
                d.setStatus(rs.getBoolean("status"));
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
        String sql = "INSERT INTO pms.department (code, name, details, parent) "
                + "VALUES (?, ?, ?, ?);";

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

        String sql = "UPDATE pms.department SET code = ?, name = ?, details = ?, "
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

        String sql = "UPDATE pms.department SET status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setBoolean(1, dept.isStatus());
            stm.setInt(2, dept.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }
}
