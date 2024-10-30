/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.*;
import java.sql.SQLException;
import java.util.List;
import model.*;

/**
 *
 * @author kelma
 */
public class ProjectTypeService {

    private final ProjectTypeDAO projectTypeDAO;
    private final ProjectTypeSettingDAO projecTypeSettingDAO;

    public ProjectTypeService() {
        this.projectTypeDAO = new ProjectTypeDAO();
        this.projecTypeSettingDAO = new ProjectTypeSettingDAO();
    }

    // HuyenPTNHE160769
    // 05/10/2024
    // Admin get all project types
    public List<ProjectType> getAllProjectTypes(String keyword, Boolean status) {
        return projectTypeDAO.selectAllProjectTypes(keyword, status);
    }

    // HuyenPTNHE160769
    // 05/10/2024
    // Admin get a project type information by id
    public ProjectType getProjectTypeById(int id) {
        return projectTypeDAO.selectProjectTypeByID(id);
    }

    // HuyenPTNHE160769
    // 05/10/2024
    // Admin add new project type information
    public int insertProjectType(ProjectType projectType) throws SQLException {
        return projectTypeDAO.insertProjectType(projectType);
    }

    // HuyenPTNHE160769
    // 05/10/2024
    // Admin update a project type information
    public boolean updateProjectType(ProjectType projectType) throws SQLException {
        return projectTypeDAO.updateProjectType(projectType);
    }

    // HuyenPTNHE160769
    // 05/10/2024
    // Admin change status of a project type
    public boolean changeStatusProjectType(ProjectType projectType) throws SQLException {
        return projectTypeDAO.changeStatusProjectType(projectType);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Get all project type settings
    public List<ProjectTypeSetting> getAllProjectTypeSettings(String keyword, Boolean statusFilter) throws SQLException {
        return projecTypeSettingDAO.getAllProjectTypeSettings(keyword, statusFilter);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Get project type setting by id
    public ProjectTypeSetting getProjectTypeSettingById(int id) throws SQLException {
        return projecTypeSettingDAO.getProjectTypeSettingById(id);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Create new project type setting
    public void createProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        projecTypeSettingDAO.createProjectTypeSetting(setting);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Update an existing project type setting
    public void updateProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        projecTypeSettingDAO.updateProjectTypeSetting(setting);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Delete project type setting by id
    public void deleteProjectTypeSetting(int id) throws SQLException {
        projecTypeSettingDAO.deleteProjectTypeSetting(id);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Change status of project type setting by id
    public void changeStatusProjectTypeSettingById(int id, boolean newStatus) throws SQLException {
        projecTypeSettingDAO.changeStatusById(id, newStatus);
    }

    // HuyenPTNHE160769
    // 17/10/2024
    // Get project roles list
    public List<ProjectTypeSetting> getProjectRoleList(int typeId) {
        return projectTypeDAO.getProjectRolesList(typeId);
    }
    
//    HuyenPTNHE160769
//    17/10/2024      
//    Admin get all project type users
    public List<ProjectType_User> getAllProjectTypeUsers(String keyword, Integer roleId, Boolean status, int typeId) {
        return projectTypeDAO.selectAllProjectTypeUsers(keyword, roleId, status, typeId);
    }

    // HuyenPTNHE160769
    // 22/10/2024
    // Admin add new project type user information
    public int insertProjectTypeUser(ProjectType_User ptUser) throws SQLException {
        return projectTypeDAO.insertProjectTypeUser(ptUser);
    }

    // HuyenPTNHE160769
    // 22/10/2024
    // Admin get a project type user information by id
    public ProjectType_User getProjectTypeUserById(int id) {
        return projectTypeDAO.selectProjectTypeUserByID(id);
    }

    // HuyenPTNHE160769
    // 22/10/2024
    // Admin update a project type user information
    public boolean updateProjectTypeUser(ProjectType_User ptUser) throws SQLException {
        return projectTypeDAO.updateProjectTypeUser(ptUser);
    }

    // HuyenPTNHE160769
    // 18/10/2024
    // Admin change status of a project type user
    public boolean changeStatusProjectTypeUser(ProjectType_User ptUser) throws SQLException {
        return projectTypeDAO.changeStatusProjectTypeUser(ptUser);
    }

    // HuyenPTNHE160769
    // 29/10/2024
    // Get project phases list
    public List<ProjectPhase> getPhaseList(int typeId) {
        return projectTypeDAO.getPhaseList(typeId);
    }
    
//    HuyenPTNHE160769
//    29/10/2024      
//    Admin get all project type criteria
    public List<ProjectTypeCriteria> getAllProjectTypeCriteria(String keyword, Integer phaseId, Boolean status, int typeId) {
        return projectTypeDAO.selectAllProjectTypeCriteria(keyword, phaseId, status, typeId);
    }
    
    // HuyenPTNHE160769
    // 29/10/2024
    // Admin change status of a project type criteria
    public boolean changeStatusProjectTypeCriteria(ProjectTypeCriteria ptCriteria) throws SQLException {
        return projectTypeDAO.changeStatusProjectTypeCriteria(ptCriteria);
    }
    
    // HuyenPTNHE160769
    // 30/10/2024
    // Admin get a project type criteria information by id
    public ProjectTypeCriteria getProjectTypeCriteriaById(int id) {
        return projectTypeDAO.selectProjectTypeCriteriaByID(id);
    }
}
