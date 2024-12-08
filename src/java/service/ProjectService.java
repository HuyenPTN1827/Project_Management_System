/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.ProjectDAO;
import java.util.List;
import model.Allocation;
import model.Department;
import model.Milestone;
import model.Project;
import model.ProjectType;
import model.User;

/**
 *
 * @author Admin
 */
//BachHD
public class ProjectService {

    private ProjectDAO projectDAO;

    public ProjectService() {
        projectDAO = new ProjectDAO();
    }

    // Lấy danh sách tất cả các dự án
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    // Tìm kiếm dự án theo trạng thái
    public List<Project> searchProjectsByStatus(int status) {
        return projectDAO.getProjectsByStatus(status);
    }

    public List<Project> searchProjectsByKeyword(String keyword) {
        return projectDAO.searchProjectsByKeyword(keyword); // Gọi phương thức từ ProjectDAO
    }

    // Lấy dự án theo ID
    public Project getProjectById(int id) {
        return projectDAO.getProjectById(id); // Gọi phương thức từ ProjectDAO
    }
    //update 

    public boolean updateProject(Project project) {
        return projectDAO.updateProject(project); // Gọi hàm cập nhật từ ProjectDAO
    }
    //edit

    public boolean insertProject(Project project, Allocation allocation, Milestone milestone) {
        return projectDAO.insertProject(project, allocation, milestone);
    }
// Lấy danh sách manager (người dùng có role_id = 4)

    public List<User> getAllManagers() {
        return projectDAO.getAllManagers(); // Gọi phương thức trong UserDAO để lấy danh sách Manager
    }

    public List<Project> getProjectsDropDown() {
        
        return projectDAO.getProjectsDropDown();
    }

    //HuyenPTNHE160769
    public List<Project> getProjectListByUserID(int userId) {
        return projectDAO.getProjectListByUserID(userId);
    }

    public List<Milestone> getMilestonesByProjectId(Integer projectId) {
        return projectDAO.getMilestonesByProjectId(projectId);
    }
    
     public List<ProjectType> getAllProjectType() {
        return projectDAO.getAllProjectTypes(); 
    }
      public List<Department> getAllDepartment() {
        return projectDAO.getAllDepartments(); 
    }
      
      public boolean isCodeExists(String code) {
    return projectDAO.isCodeExists(code);
}

}
