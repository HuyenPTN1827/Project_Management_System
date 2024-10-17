/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.DepartmentDAO;
import context.ProjectTypeDAO;
import java.sql.SQLException;
import java.util.List;
import model.Department;
import model.ProjectType;
import model.ProjectType_User;

/**
 *
 * @author kelma
 */
public class GroupService {

    private final DepartmentDAO deptDAO;
    private final ProjectTypeDAO projectTypeDAO;

    public GroupService() {
        this.deptDAO = new DepartmentDAO();
        this.projectTypeDAO = new ProjectTypeDAO();
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
    
//    HuyenPTNHE160769
//    17/10/2024      
//    Admin get all project type users
    public List<ProjectType_User> getAllProjectTypeUsers(String keyword, Integer roleId, Boolean status, int typeId) {
        return projectTypeDAO.selectAllProjectTypeUsers(keyword, roleId, status, typeId);
    }
}
