/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.SettingDAO;
import java.sql.SQLException;
import java.util.List;
import model.Setting;

/**
 *
 * @author kelma
 */
public class SettingService {

    private final SettingDAO settingDAO;

    public SettingService() {
        this.settingDAO = new SettingDAO();
    }

    // HuyenPTNHE160769
    // 29/09/2024
    // Get user roles list
    public List<Setting> getUserRoleList() {
        return settingDAO.getUserRolesList();
    }

    // HuyenPTNHE160769
    // 22/11/2024
    // Get issue types list
    public List<Setting> getIssueTypeList() {
        return settingDAO.getIssueTypeList();
    }

    // HuyenPTNHE160769
    // 03/10/2024
    // Admin get all settings
    public List<Setting> getAllSettings(String keyword, String type, Boolean status) {
        return settingDAO.selectAllSettings(keyword, type, status);
    }

    // HuyenPTNHE160769
    // 03/10/2024
    // Admin get a setting information by id
    public Setting getSettingById(int id) {
        return settingDAO.selectSettingByID(id);
    }

    // HuyenPTNHE160769
    // 03/10/2024
    // Admin add new setting information
    public int insertSetting(Setting setting) throws SQLException {
        return settingDAO.insertSetting(setting);
    }

    // HuyenPTNHE160769
    // 03/10/2024
    // Admin update a setting information
    public boolean updateSetting(Setting setting) throws SQLException {
        return settingDAO.updateSetting(setting);
    }

    // HuyenPTNHE160769
    // 03/10/2024
    // Admin change status of an setting
    public boolean changeStatusSetting(Setting setting) throws SQLException {
        return settingDAO.changeStatusSetting(setting);
    }
    
    //BachHD
    //16/10
    public List<Setting> getPriorityUserRolesList(){
        return settingDAO.getPriorityUserRolesList();
    }
    
    // HuyenPTNHE160769
    // 11/11/2024
    // Get type list
    public List<Setting> getTypeList() {
        return settingDAO.getTypeList();
    }
}
