/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.ProjectTypeSettingDAO;
import java.util.List;
import model.ProjectTypeSetting;

/**
 *
 * @author kelma
 */
public class ProjectTypeSettingService {
    private final ProjectTypeSettingDAO ptSettingDAO;

    public ProjectTypeSettingService() {
        this.ptSettingDAO = new ProjectTypeSettingDAO();
    }

    // HuyenPTNHE160769
    // 17/10/2024
    // Get project roles list
    public List<ProjectTypeSetting> getProjectRoleList() {
        return ptSettingDAO.getProjectRolesList();
    }
}
