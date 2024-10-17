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
}
