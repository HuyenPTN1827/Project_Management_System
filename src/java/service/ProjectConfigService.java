package service;

import context.ProjectConfigDAO;
import java.sql.SQLException;
import model.Milestone;
import java.util.List;
import model.Allocation;
import model.Project;
import model.Team;
import model.TeamMember;
import model.User;

public class ProjectConfigService {

    private ProjectConfigDAO projectConfigDAO;

    public ProjectConfigService() {
        projectConfigDAO = new ProjectConfigDAO();
    }

    public List<Milestone> getMilestonesByProjectId(int projectId) {
        return projectConfigDAO.getMilestonesByProjectId(projectId);
    }

    public List<Milestone> filterAndsearch(String status, String searchKeyword, String projectId) {
        return projectConfigDAO.filterAndsearch(status, searchKeyword, projectId);
    }

    // Phương thức để lấy một milestone theo ID
    public Milestone getMilestoneById(int milestoneId) {
        return projectConfigDAO.getMilestoneById(milestoneId);
    }

//lấy danh sách project
    public List<Project> getAllProjects() {
        return projectConfigDAO.getAllProjects(); // Gọi phương thức trong DAO để lấy danh sách tất cả project
    }
    // Lấy dự án theo ID

    public Project getProjectById(int id) {
        return projectConfigDAO.getProjectById(id); // Gọi phương thức từ ProjectDAO
    }

    // Lấy danh sách manager (người dùng có role_id = 4)
    public List<User> getAllManagers() {
        return projectConfigDAO.getAllManagers(); // Gọi phương thức trong UserDAO để lấy danh sách Manager
    }

    // Phương thức để cập nhật thông tin dự án
    public boolean updateProject(Project project) {
        return projectConfigDAO.updateProject(project); // Gọi phương thức trong DAO để cập nhật dự án
    }

    // Hàm gọi getAllMilestonesParent từ DAO
    public List<Milestone> getAllMilestonesParent() {
        return projectConfigDAO.getAllMilestonesParent();
    }

    // Phương thức addMilestone
    public boolean addMilestone(Milestone milestone) {
        // Kiểm tra các thông tin quan trọng của milestone trước khi thêm
        if (milestone == null || milestone.getName() == null || milestone.getPriority() <= 0) {
            return false; // Trả về false nếu dữ liệu không hợp lệ
        }

        // Thực hiện thêm milestone vào cơ sở dữ liệu thông qua DAO
        return projectConfigDAO.insertMilestone(milestone);
    }

    public boolean updateMilestone(Milestone milestone) {
        // Kiểm tra tính hợp lệ của milestone trước khi cập nhật
        if (milestone == null || milestone.getId() <= 0 || milestone.getName() == null || milestone.getPriority() <= 0) {
            return false; // Trả về false nếu dữ liệu không hợp lệ
        }

        // Thực hiện cập nhật milestone vào cơ sở dữ liệu thông qua DAO
        return projectConfigDAO.updateMilestone(milestone);
    }

    
    //HuyenPTNHE160769
    //Get all allocations
    public List<Allocation> getAllAllocations(int projectId, String keyword, Integer deptId, Integer roleId, Boolean status) {
        return projectConfigDAO.selectAllAllocations(projectId, keyword, deptId, roleId, status);
    }
//
//    // HuyenPTNHE160769
//    // 31/10/2024
//    // Admin add new dept user information
//    public int insertDepartmentUser(Allocation deptUser) throws SQLException {
//        return deptDAO.insertDepartmentUser(deptUser);
//    }
//
//    // HuyenPTNHE160769
//    // 31/10/2024
//    // Admin get a dept user information by id
//    public Allocation getDepartmentUserById(int id) {
//        return deptDAO.selectDepartmentUserByID(id);
//    }
//
//    // HuyenPTNHE160769
//    // 31/10/2024
//    // Admin update a dept user information
//    public boolean updateDepartmentUser(Allocation deptUser) throws SQLException {
//        return deptDAO.updateDepartmentUser(deptUser);
//    }
//
//    // HuyenPTNHE160769
//    // 1/10/2024
//    // Admin change status of a dept user
//    public boolean changeStatusDepartmentUser(Department_User deptUser) throws SQLException {
//        return deptDAO.changeStatusDepartmentUser(deptUser);
//    }

}
