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
import model.Department;

/**
 *
 * @author kelma
 */
public class DeptDAO {
    private static final String SELECT_ALL_DEPTS_SQL = "SELECT * FROM pms.department;";

    public List<Department> selectAllDepartments() {
        List<Department> dept = new ArrayList<>();

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(SELECT_ALL_DEPTS_SQL);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
                d.setName(rs.getString("name"));
                d.setDetails(rs.getString("details"));
                d.setParent(rs.getString("parent"));
                d.setStatus(rs.getBoolean("status"));
                dept.add(d);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return dept;
    }
}
