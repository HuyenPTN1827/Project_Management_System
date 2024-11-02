/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.ProjectDAO;
import java.util.List;
import model.Project;

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

    public List<Project> getProjectsByCode(String projectCode) {
        return projectDAO.getProjectsByCode(projectCode); // Gọi phương thức từ ProjectDAO
    }
    // Tìm kiếm dự án theo trạng thái

    public List<Project> searchProjectsByStatus(boolean status) {
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

    public boolean insertProject(Project project) {
        return projectDAO.insertProject(project);
    }
    
   

}
