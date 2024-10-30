/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.ProjectTypeSetting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Department;
import model.Setting;

public class ProjectTypeSettingDAO {
    // TrươngHBHE151011
    // 17/10/2024
    // Get list of ProjectTypeSetting with search by name or value and filter by status
    public List<ProjectTypeSetting> getAllProjectTypeSettings(String keyword, Boolean statusFilter) throws SQLException {
        List<ProjectTypeSetting> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM project_type_setting WHERE 1=1");

        // Add search condition if keyword is provided
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (name LIKE ? OR value LIKE ?)");
        }

        // Add status filter if provided
        if (statusFilter != null) {
            sql.append(" AND status = ?");
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql.toString());) {
            int paramIndex = 1;

            // Set parameters for keyword search
            if (keyword != null && !keyword.trim().isEmpty()) {
                String searchPattern = "%" + keyword.trim() + "%";
                stmt.setString(paramIndex++, searchPattern);
                stmt.setString(paramIndex++, searchPattern);
            }

            // Set parameter for status filter
            if (statusFilter != null) {
                stmt.setBoolean(paramIndex++, statusFilter);
            }
            System.out.println(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProjectTypeSetting setting = new ProjectTypeSetting(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("value"),
                        rs.getInt("priority"),
                        rs.getBoolean("status"),
                        rs.getString("description")
                );
                list.add(setting);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // TrươngHBHE151011
    // 17/10/2024
    // Get ProjectTypeSetting by ID
    public ProjectTypeSetting getProjectTypeSettingById(int id) throws SQLException {
        String sql = "SELECT * FROM project_type_setting WHERE id = ?";
        System.out.println(sql);

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProjectTypeSetting(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("value"),
                        rs.getInt("priority"),
                        rs.getBoolean("status"),
                        rs.getString("description")
                );
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // TrươngHBHE151011
    // 17/10/2024
    // Create new ProjectTypeSetting
    public void createProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        String sql = "INSERT INTO project_type_setting (name, type, value, priority, status, description) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println(sql);

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setString(1, setting.getName());
            stmt.setString(2, setting.getType());
            stmt.setString(3, setting.getValue());
            stmt.setInt(4, setting.getPriority());
            stmt.setBoolean(5, setting.isStatus());
            stmt.setString(6, setting.getDescription());
            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TrươngHBHE151011
    // 17/10/2024
    // Update existing ProjectTypeSetting
    public void updateProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        String sql = "UPDATE project_type_setting SET name = ?, type = ?, value = ?, priority = ?, status = ?, description = ? WHERE id = ?";
        System.out.println(sql);

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setString(1, setting.getName());
            stmt.setString(2, setting.getType());
            stmt.setString(3, setting.getValue());
            stmt.setInt(4, setting.getPriority());
            stmt.setBoolean(5, setting.isStatus());
            stmt.setString(6, setting.getDescription());
            stmt.setInt(7, setting.getId());
            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TrươngHBHE151011
    // 17/10/2024
    // Delete ProjectTypeSetting by ID
    public void deleteProjectTypeSetting(int id) throws SQLException {
        String sql = "DELETE FROM project_type_setting WHERE id = ?";
        System.out.println(sql);

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TrươngHBHE151011
    // 17/10/2024
    // Change the status of ProjectTypeSetting by ID
    public void changeStatusById(int id, boolean newStatus) throws SQLException {
        String sql = "UPDATE project_type_setting SET status = ? WHERE id = ?";
        System.out.println(sql);
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setBoolean(1, newStatus);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
