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
public class GroupService {

    private final DepartmentDAO deptDAO;
    private final ProjectTypeDAO projectTypeDAO;
    private final ProjecTypeSettingDAO projecTypeSettingDAO;

    public GroupService() {
        this.deptDAO = new DepartmentDAO();
        this.projectTypeDAO = new ProjectTypeDAO();
        this.projecTypeSettingDAO = new ProjecTypeSettingDAO();
    }

    // HuyenPTNHE160769
    // 29/09/2024
    // Get depts list
    public List<Department> getDepartmentList() {
        return deptDAO.getDepartmentList();
    }

    // HuyenPTNHE160769
    // 04/10/2024
    // Admin get all depts
    public List<Department> getAllDepartments(String keyword, Boolean status) {
        return deptDAO.selectAllDepartments(keyword, status);
    }

    // HuyenPTNHE160769
    // 04/10/2024
    // Admin get a dept information by id
    public Department getDepartmentById(int id) {
        return deptDAO.selectDepartmentByID(id);
    }

    // HuyenPTNHE160769
    // 04/10/2024
    // Admin add new dept information
    public int insertDepartment(Department dept, Integer parent) throws SQLException {
        return deptDAO.insertDepartment(dept, parent);
    }

    // HuyenPTNHE160769
    // 04/10/2024
    // Admin update a dept information
    public boolean updateDepartment(Department dept, Integer parent) throws SQLException {
        return deptDAO.updateDepartment(dept, parent);
    }

    // HuyenPTNHE160769
    // 04/10/2024
    // Admin change status of a dept
    public boolean changeStatusDepartment(Department dept) throws SQLException {
        return deptDAO.changeStatusDepartment(dept);
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

    // Get all project type settings
    public List<ProjecTypeSetting> getAllProjectTypeSettings(String keyword, Boolean statusFilter) throws SQLException {
        return projecTypeSettingDAO.getAllProjectTypeSettings(keyword,statusFilter);
    }

    // Get project type setting by id
    public ProjecTypeSetting getProjectTypeSettingById(int id) throws SQLException {
        return projecTypeSettingDAO.getProjectTypeSettingById(id);
    }

    // Create new project type setting
    public void createProjectTypeSetting(ProjecTypeSetting setting) throws SQLException {
        projecTypeSettingDAO.createProjectTypeSetting(setting);
    }

    // Update an existing project type setting
    public void updateProjectTypeSetting(ProjecTypeSetting setting) throws SQLException {
        projecTypeSettingDAO.updateProjectTypeSetting(setting);
    }

    // Delete project type setting by id
    public void deleteProjectTypeSetting(int id) throws SQLException {
        projecTypeSettingDAO.deleteProjectTypeSetting(id);
    }

    // Change status of project type setting by id
    public void changeStatusProjectTypeSettingById(int id, boolean newStatus) throws SQLException {
        projecTypeSettingDAO.changeStatusById(id, newStatus);
    }

}
