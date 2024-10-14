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
import model.ProjectType;

/**
 *
 * @author kelma
 */
public class ProjectTypeDAO {

//    HuyenPTNHE160769
//    05/10/2024        
//    Admin select all project types order by id descending
//        LIMIT = pageSize
//        OFFSET = (pageIndex - 1) * pageSize;
    public List<ProjectType> selectAllProjectTypes(String keyword, Boolean status) {
        List<ProjectType> projectType = new ArrayList<>();

        String sql = "SELECT * FROM pms.project_type WHERE 1=1";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(code) LIKE ? OR LOWER(name) LIKE ?)";
        }
        if (status != null) {
            sql += " AND status = ?";
        }

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
                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("id"));
                pt.setCode(rs.getString("code"));
                pt.setName(rs.getString("name"));
                pt.setDetails(rs.getString("details"));
                pt.setStatus(rs.getBoolean("status"));

                projectType.add(pt);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return projectType;
    }

//    HuyenPTNHE160769
//    05/10/2024      
//    Admin select project type by id
    public ProjectType selectProjectTypeByID(int id) {
        ProjectType pt = null;

        String sql = "SELECT * FROM pms.project_type WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                pt = new ProjectType();
                pt.setId(rs.getInt("id"));
                pt.setCode(rs.getString("code"));
                pt.setName(rs.getString("name"));
                pt.setDetails(rs.getString("details"));
                pt.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return pt;
    }

//    HuyenPTNHE160769
//    05/10/2024       
//    Admin add new project type
    public int insertProjectType(ProjectType projectType) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO pms.project_type (code, name, details) VALUES (?, ?, ?);";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, projectType.getCode());
            stm.setString(2, projectType.getName());
            stm.setString(3, projectType.getDetails());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    05/10/2024         
//    Admin update a project type
    public boolean updateProjectType(ProjectType projectType) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.project_type SET code = ?, name = ?, details = ?, status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, projectType.getCode());
            stm.setString(2, projectType.getName());
            stm.setString(3, projectType.getDetails());
            stm.setBoolean(4, projectType.isStatus());
            stm.setInt(5, projectType.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    05/10/2024      
//    Admin change status of a project type
    public boolean changeStatusProjectType(ProjectType projectType) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.project_type SET status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setBoolean(1, projectType.isStatus());
            stm.setInt(2, projectType.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }
}
