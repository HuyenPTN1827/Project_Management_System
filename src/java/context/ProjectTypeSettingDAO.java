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
import model.ProjectTypeSetting;

/**
 *
 * @author kelma
 */
public class ProjectTypeSettingDAO {
    // HuyenPTNHE160769
    // 29/09/2024
    // Get roles list
    public List<ProjectTypeSetting> getProjectRolesList() {
        List<ProjectTypeSetting> pjSetting = new ArrayList<>();

        String sql = "SELECT * FROM pms.project_type_setting WHERE type = 'Project Role' AND status = 1 ORDER BY priority ASC;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProjectTypeSetting pts = new ProjectTypeSetting();
                pts.setId(rs.getInt("id"));
                pts.setName(rs.getString("name"));
                pts.setValue(rs.getString("value"));
                pjSetting.add(pts);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return pjSetting;
    }
}
