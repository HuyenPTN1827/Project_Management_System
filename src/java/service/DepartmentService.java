/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.DepartmentDAO;
import java.sql.SQLException;
import java.util.List;
import model.Department;
import model.Department_User;

/**
 *
 * @author kelma
 */
public class DepartmentService {

    private final DepartmentDAO deptDAO;

    public DepartmentService() {
        this.deptDAO = new DepartmentDAO();
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
        
//    HuyenPTNHE160769
//    31/10/2024      
//    Admin get all dept users
    public List<Department_User> getAllDepartmentUsers(String keyword, Integer roleId, Boolean status, int deptId) {
        return deptDAO.selectAllDepartmentUsers(keyword, roleId, status, deptId);
    }

    // HuyenPTNHE160769
    // 31/10/2024
    // Admin add new dept user information
    public int insertDepartmentUser(Department_User deptUser) throws SQLException {
        return deptDAO.insertDepartmentUser(deptUser);
    }

    // HuyenPTNHE160769
    // 31/10/2024
    // Admin get a dept user information by id
    public Department_User getDepartmentUserById(int id) {
        return deptDAO.selectDepartmentUserByID(id);
    }

    // HuyenPTNHE160769
    // 31/10/2024
    // Admin update a dept user information
    public boolean updateDepartmentUser(Department_User deptUser) throws SQLException {
        return deptDAO.updateDepartmentUser(deptUser);
    }

    // HuyenPTNHE160769
    // 1/10/2024
    // Admin change status of a dept user
    public boolean changeStatusDepartmentUser(Department_User deptUser) throws SQLException {
        return deptDAO.changeStatusDepartmentUser(deptUser);
    }

}
