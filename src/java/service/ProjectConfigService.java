package service;

import context.ProjectConfigDAO;
import model.Milestone;
import java.util.List;
import model.Team;
import model.TeamMember;

public class ProjectConfigService {

    private ProjectConfigDAO projectConfigDAO;

    public ProjectConfigService() {
        projectConfigDAO = new ProjectConfigDAO();
    }

    public List<Milestone> getMilestonesByProjectId(int projectId) {
        return projectConfigDAO.getMilestonesByProjectId(projectId);
    }

    public List<Milestone> filterAndsearch(String status, String searchKeyword) {
        return projectConfigDAO.filterAndsearch(status, searchKeyword);
    }

    // Phương thức để lấy một milestone theo ID
    public Milestone getMilestoneById(int milestoneId) {
        return projectConfigDAO.getMilestoneById(milestoneId);
    }

    // Phương thức để cập nhật một milestone
    public void updateMilestone(Milestone milestone) {
        projectConfigDAO.updateMilestone(milestone);
    }

    // Phương thức để lấy danh sách đội nhóm theo projectId
    public List<Team> getTeamsByProjectId(int projectId) {
        return projectConfigDAO.getTeamsByProjectId(projectId); // Gọi phương thức trong DAO
    }
    // Thêm vào ProjectConfigService

public List<Team> searchTeamsByName(String searchKeyword) {
    // Giả sử bạn có một phương thức truy vấn trong DAO để lấy danh sách nhóm
    return projectConfigDAO.searchTeamsByName(searchKeyword); // Gọi phương thức tìm kiếm theo keyword và projectId
}

// Phương thức để lấy đội theo ID
    public Team getTeamById(int teamId) {
        return projectConfigDAO.getTeamById(teamId);
    }
    public void updateTeam(Team team) {
        projectConfigDAO.updateTeam(team);
    }

     // Phương thức để lấy danh sách member theo projectId
    public List<TeamMember> getMembersByProjectId(int projectId) {
        return projectConfigDAO.getMembersByProjectId(projectId);
    }


}
