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
import model.Setting;

/**
 *
 * @author kelma
 */
public class SettingDAO {

    // HuyenPTNHE160769
    // 29/09/2024
    // Get user roles list
    public List<Setting> getUserRolesList() {
        List<Setting> setting = new ArrayList<>();

        String sql = "SELECT * FROM setting WHERE type = 'User Role' ORDER BY priority DESC;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setValue(rs.getString("value"));
                setting.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return setting;
    }
    
    // Get biz term list
    public List<Setting> getBizTermsList() {
        List<Setting> setting = new ArrayList<>();

        String sql = "SELECT * FROM setting WHERE type = 'Business Term' AND status = 1 ORDER BY id ASC;";
        
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setValue(rs.getString("value"));
                setting.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return setting;
    }

    // HuyenPTNHE160769
    // 22/10/2024
    // Get issue types list
    public List<Setting> getIssueTypeList() {
        List<Setting> setting = new ArrayList<>();

        String sql = "SELECT * FROM setting WHERE type = 'Issue Type' AND status = 1 ORDER BY priority ASC;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setValue(rs.getString("value"));
                setting.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return setting;
    }

//    HuyenPTNHE160769
//    03/10/2024        
//    Admin select all settings order by id descending
//        LIMIT = pageSize
//        OFFSET = (pageIndex - 1) * pageSize;
    public List<Setting> selectAllSettings(String keyword, String type, Boolean status) {
        List<Setting> setting = new ArrayList<>();

        String sql = "SELECT * FROM setting WHERE 1=1";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(name) LIKE ? OR LOWER(value) LIKE ?)";
        }
        if ("parent".equals(type)) {
            sql += " AND (type IS NULL OR type = '')";
        } else if (type != null && !type.isEmpty()) {
            sql += " AND type = ?";
        }
        if (status != null) {
            sql += " AND status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";

                stm.setString(index++, keywordPattern);
                stm.setString(index++, keywordPattern);
            }
            // Set type only if it is not "parent" and not empty
            if (type != null && !type.isEmpty() && !"parent".equals(type)) {
                stm.setString(index++, type);
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setType(rs.getString("type"));
                s.setValue(rs.getString("value"));
                s.setPriority(rs.getInt("priority"));
                s.setStatus(rs.getBoolean("status"));
                s.setDescription(rs.getString("description"));

                setting.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return setting;
    }

//    HuyenPTNHE160769
//    03/10/2024        
//    Admin select setting by id
    public Setting selectSettingByID(int id) {
        Setting s = null;

        String sql = "SELECT * FROM setting WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s = new Setting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setType(rs.getString("type"));
                s.setValue(rs.getString("value"));
                s.setPriority(rs.getInt("priority"));
                s.setStatus(rs.getBoolean("status"));
                s.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return s;
    }

//    HuyenPTNHE160769
//    03/10/2024        
//    Admin add new setting
    public int insertSetting(Setting setting) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO setting (name, type, value, priority, status, description) "
                + "VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, setting.getName());
            stm.setString(2, setting.getType());
            stm.setString(3, setting.getValue());
            stm.setInt(4, setting.getPriority());
            stm.setBoolean(5, setting.isStatus());
            stm.setString(6, setting.getDescription());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    03/10/2024        
//    Admin update a setting
    public boolean updateSetting(Setting setting) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE setting SET name = ?, type = ?, value = ?, "
                + "priority = ?, status = ?, description = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, setting.getName());
            stm.setString(2, setting.getType());
            stm.setString(3, setting.getValue());
            stm.setInt(4, setting.getPriority());
            stm.setBoolean(5, setting.isStatus());
            stm.setString(6, setting.getDescription());
            stm.setInt(7, setting.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    03/10/2024      
//    Admin change status of a setting
    public boolean changeStatusSetting(Setting setting) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE setting SET status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setBoolean(1, setting.isStatus());
            stm.setInt(2, setting.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

    public List<Setting> getPriorityUserRolesList() {
        List<Setting> settings = new ArrayList<>();

        String sql = "SELECT id, name, value, priority FROM setting WHERE type = 'User Role' AND status = 1 ORDER BY priority DESC;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setValue(rs.getString("value"));
                s.setPriority(rs.getInt("priority")); // Thêm dòng này để gán giá trị cho priority
                settings.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return settings;
    }

    // HuyenPTNHE160769
    // 11/11/2024
    // Get type list
    public List<Setting> getTypeList() {
        List<Setting> setting = new ArrayList<>();

        String sql = "SELECT * FROM setting WHERE (type IS NULL OR type = '');";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setValue(rs.getString("value"));
                setting.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return setting;
    }

}
