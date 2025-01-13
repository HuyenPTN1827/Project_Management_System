package service;

import context.ProjectConfigDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Milestone;
import java.util.List;
import model.Allocation;
import model.Department;
import model.Project;
import model.Setting;
import model.Team;
import model.TeamMember;
import model.User;

public class ProjectConfigService extends BaseServive {

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

    public List<Department> getAllDepartment() {
        return projectConfigDAO.getAllDepartments();
    }

    // Lấy danh sách manager (người dùng có role_id = 4)
    public List<User> getAllManagers() {
        return projectConfigDAO.getAllManagers(); // Gọi phương thức trong UserDAO để lấy danh sách Manager
    }

    // Phương thức để cập nhật thông tin dự án
    public boolean updateProject(Project project) {
        return projectConfigDAO.updateProject(project); // Gọi phương thức trong DAO để cập nhật dự án
    }

    // Hàm gọi getMilestoneParentByProjectId từ DAO
    public List<Milestone> getMilestoneParentByProjectId(int projectId) {
        return projectConfigDAO.getMilestoneParentByProjectId(projectId);
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

    // Add new allocation information
    public int insertAllocation(Allocation allocation) throws SQLException {
        return projectConfigDAO.insertAllocation(allocation);
    }

    // Get an allocation information by id
    public Allocation getAllocationById(int id) {
        return projectConfigDAO.selectAllocationByID(id);
    }

    // Update an allocation information
    public boolean updateAllocation(Allocation allocation) throws SQLException {
        return projectConfigDAO.updateAllocation(allocation);
    }

    // Change status of an allocation
    public boolean changeStatusAllocation(Allocation allocation) throws SQLException {
        return projectConfigDAO.changeStatusAllocation(allocation);
    }

    //Start date cannot be before today, End date cannot be before start date
    public List<String> validateAllocationDate(Allocation allocation) {
        List<String> errors = new ArrayList<>();

        if (!validateStartDates(allocation.getStartDate())) {
            errors.add("Start Date cannot be earlier than today.");
        }

        if (!validateEndDates(allocation.getStartDate(), allocation.getEndDate())) {
            errors.add("End Date cannot be earlier than Start Date.");
        }

        return errors;
    }

    public boolean isCodeExists(String code, int id) {
        return projectConfigDAO.isCodeExists(code, id);
    }

    public List<Setting> getAllBizTerms() {
        return projectConfigDAO.getAllBizTerms();
    }

}
