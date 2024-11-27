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

    public ProjectTypeService() {
        this.projectTypeDAO = new ProjectTypeDAO();
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
    public List<ProjectTypeSetting> getAllProjectTypeSettings(String keyword, Boolean status, String type, int typeId) throws SQLException {
        return projectTypeDAO.getAllProjectTypeSettings(keyword, status, type, typeId);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Get project type setting by id
    public ProjectTypeSetting getProjectTypeSettingById(int id) throws SQLException {
        return projectTypeDAO.getProjectTypeSettingById(id);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Create new project type setting
    public void createProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        projectTypeDAO.createProjectTypeSetting(setting);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Update an existing project type setting
    public void updateProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        projectTypeDAO.updateProjectTypeSetting(setting);
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Change status of project type setting by id
    public void changeStatusProjectTypeSettingById(int id, boolean newStatus) throws SQLException {
        projectTypeDAO.changeStatusById(id, newStatus);
    }

    // HuyenPTNHE160769
    // 17/10/2024
    // Get project roles list
    public List<ProjectTypeSetting> getProjectRoleList(int projectId) {
        return projectTypeDAO.getProjectRolesList(projectId);
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
    public List<ProjectPhase> getAllProjectPhase(int typeId, String keyword, Boolean status) {
        return projectTypeDAO.selectAllProjectPhase(typeId, keyword, status);
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
    public boolean changeStatusProjectTypeCriteria(ProjectTypeCriteria ptc) throws SQLException {
        return projectTypeDAO.changeStatusProjectTypeCriteria(ptc);
    }
    
    // HuyenPTNHE160769
    // 30/10/2024
    // Admin get a project type criteria information by id
    public ProjectTypeCriteria getProjectTypeCriteriaById(int id) {
        return projectTypeDAO.selectProjectTypeCriteriaByID(id);
    }
    
    // HuyenPTNHE160769
    // 30/10/2024
    // Admin add new project type criteria information
    public int insertProjectTypeCriteria(ProjectTypeCriteria ptc) throws SQLException {
        return projectTypeDAO.insertProjectTypeCriteria(ptc);
    }
    
    // HuyenPTNHE160769
    // 30/10/2024
    // Admin update a project type criteria information
    public boolean updateProjectTypeCriteria(ProjectTypeCriteria ptc) throws SQLException {
        return projectTypeDAO.updateProjectTypeCriteria(ptc);
    }
    
    //chiennkhe161554
    
    // 30/10/2024
    // Admin get a project phase information by id
    public ProjectPhase getProjectPhaseById(int id) {
        return projectTypeDAO.selectProjectPhaseByID(id);
    }

    // 30/10/2024
    // Admin add new project phase information
    public int insertProjectPhase(ProjectPhase phase) throws SQLException {
        return projectTypeDAO.insertProjectPhase(phase);
    }

    // 30/10/2024
    // Admin update a project phase information
    public boolean updateProjectPhase(ProjectPhase phase) throws SQLException {
        return projectTypeDAO.updateProjectPhase(phase);
    }

    // 30/10/2024
    // Admin change status of a project phase
    public boolean changeStatusProjectPhase(ProjectPhase phase) throws SQLException {
        return projectTypeDAO.changeStatusProjectPhase(phase);
    }
        
    // HuyenPTNHE160769
    // 11/11/2024
    // Get type list
    public List<ProjectTypeSetting> getTypeList(int id) {
        return projectTypeDAO.getTypeList(id);
    }
}
