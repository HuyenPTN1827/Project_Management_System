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
import model.Project;

/**
 *
 * @author Admin
 */
public class ProjectDAO {
    //BachHD
   public List<Project> getAllProjects() {
    List<Project> projects = new ArrayList<>();

   String sql = "SELECT p.*, pt.code AS type_code, d.code AS department_code, s.name AS setting_name " +
             "FROM project p " +
             "LEFT JOIN project_type pt ON p.type_id = pt.id " +
             "LEFT JOIN department d ON p.department_id = d.id " +
             "LEFT JOIN setting s ON p.biz_term = s.id;";


    try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Project project = new Project();
            project.setId(rs.getInt("id"));
            project.setBizTerm(rs.getString("biz_term"));
            project.setCode(rs.getString("code"));
            project.setName(rs.getString("name"));
            project.setDetails(rs.getString("details"));
            project.setStartDate(rs.getDate("start_date"));
            project.setStatus(rs.getBoolean("status"));
            project.setTypeId(rs.getInt("type_id"));
            project.setDepartmentId(rs.getInt("department_id"));

            // Lấy mã loại dự án và mã bộ phận
            project.setTypeCode(rs.getString("type_code")); // Cần thêm setter cho typeCode trong Project
            project.setDepartmentCode(rs.getString("department_code")); // Cần thêm setter cho departmentCode trong Project
            project.setSettingName(rs.getString("setting_name"));
            projects.add(project);
        }
    } catch (SQLException e) {
        BaseDAO.printSQLException(e);
    }
    return projects;
}

}
