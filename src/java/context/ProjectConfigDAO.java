/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Milestone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Team;
import model.TeamMember;

/**
 *
 * @author Admin
 */
public class ProjectConfigDAO {

    public List<Milestone> getMilestonesByProjectId(int projectId) {
        List<Milestone> milestones = new ArrayList<>();

        String sql = "SELECT m.id, m.code, m.name, m.details, m.priority, m.deadline, m.status, m.project_id, m.phase_id "
                + "FROM milestone m "
                + "WHERE m.project_id = ?"; // Điều kiện lọc theo projectId

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            stm.setInt(1, projectId); // Thiết lập giá trị cho tham số projectId
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Milestone milestone = new Milestone();
                milestone.setId(rs.getInt("id"));
                milestone.setCode(rs.getString("code"));
                milestone.setName(rs.getString("name"));
                milestone.setDetails(rs.getString("details"));
                milestone.setPriority(rs.getString("priority"));
                milestone.setDeadline(rs.getDate("deadline")); // Kiểm tra xem deadline có đúng không
                milestone.setStatus(rs.getString("status"));
                milestone.setProjectId(rs.getInt("project_id")); // Đảm bảo tên cột chính xác
                milestone.setPhaseId(rs.getInt("phase_id")); // Đảm bảo tên cột chính xác

                milestones.add(milestone);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Xử lý ngoại lệ
        }
        return milestones; // Trả về danh sách milestone
    }

    public List<Milestone> filterAndsearch(String status, String searchKeyword) {
        List<Milestone> milestones = new ArrayList<>();
        String sql = "SELECT m.id, m.code, m.name, m.details, m.priority, m.deadline, m.status, m.project_id, m.phase_id "
                + "FROM milestone m WHERE 1=1"; // Bắt đầu với điều kiện luôn đúng

        // Thêm điều kiện trạng thái nếu không rỗng
        if (status != null && !status.isEmpty()) {
            sql += " AND m.status = ?";
        }

        // Thêm điều kiện tìm kiếm theo tên nếu không rỗng
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql += " AND LOWER(m.name) LIKE ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            int parameterIndex = 1;

            // Thiết lập trạng thái nếu có
            if (status != null && !status.isEmpty()) {
                stm.setString(parameterIndex++, status);
            }

            // Thiết lập từ khóa tìm kiếm nếu có
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                stm.setString(parameterIndex, "%" + searchKeyword.toLowerCase() + "%");
            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Milestone milestone = new Milestone();
                milestone.setId(rs.getInt("id"));
                milestone.setCode(rs.getString("code"));
                milestone.setName(rs.getString("name"));
                milestone.setDetails(rs.getString("details"));
                milestone.setPriority(rs.getString("priority"));
                milestone.setDeadline(rs.getDate("deadline"));
                milestone.setStatus(rs.getString("status"));
                milestone.setProjectId(rs.getInt("project_id"));
                milestone.setPhaseId(rs.getInt("phase_id"));
                milestones.add(milestone);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return milestones;
    }

    // Phương thức để lấy một milestone theo ID
    public Milestone getMilestoneById(int milestoneId) {
        Milestone milestone = null;
        String sql = "SELECT m.id, m.code, m.name, m.details, m.priority, m.deadline, m.status, m.project_id, m.phase_id "
                + "FROM milestone m WHERE m.id = ?"; // Điều kiện lọc theo milestoneId

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            stm.setInt(1, milestoneId); // Thiết lập giá trị cho tham số milestoneId
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                milestone = new Milestone();
                milestone.setId(rs.getInt("id"));
                milestone.setCode(rs.getString("code"));
                milestone.setName(rs.getString("name"));
                milestone.setDetails(rs.getString("details"));
                milestone.setPriority(rs.getString("priority"));
                milestone.setDeadline(rs.getDate("deadline"));
                milestone.setStatus(rs.getString("status"));
                milestone.setProjectId(rs.getInt("project_id"));
                milestone.setPhaseId(rs.getInt("phase_id"));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return milestone; // Trả về milestone tìm được
    }

    // Phương thức để cập nhật một milestone
    public void updateMilestone(Milestone milestone) {
        String sql = "UPDATE milestone SET code = ?, name = ?, details = ?, priority = ?, deadline = ?, status = ? "
                + "WHERE id = ?"; // Cập nhật thông tin milestone

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            stm.setString(1, milestone.getCode());
            stm.setString(2, milestone.getName());
            stm.setString(3, milestone.getDetails());
            stm.setString(4, milestone.getPriority());
            stm.setDate(5, new java.sql.Date(milestone.getDeadline().getTime()));
            stm.setString(6, milestone.getStatus());
            stm.setInt(7, milestone.getId());

            stm.executeUpdate(); // Thực thi câu lệnh cập nhật
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
    }

    // Phương thức để lấy danh sách đội nhóm theo projectId
    public List<Team> getTeamsByProjectId(int projectId) {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT id, name, topic, details, project_id, status FROM team WHERE project_id = ?"; // Thêm điều kiện WHERE

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            stm.setInt(1, projectId); // Thiết lập projectId vào PreparedStatement

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                team.setTopic(rs.getString("topic"));
                team.setDetails(rs.getString("details"));
                team.setProjectId(rs.getInt("project_id"));
                team.setStatus(rs.getString("status"));

                teams.add(team);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return teams; // Trả về danh sách đội nhóm theo projectId
    }
    
 public List<Team> searchTeamsByName(String keyword) {
    List<Team> teams = new ArrayList<>();
    String sql = "SELECT * FROM team WHERE name LIKE ?"; // Chỉ tìm kiếm theo tên
    
    try (Connection conn = BaseDAO.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + keyword + "%"); // Gán giá trị cho tên
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // Tạo đối tượng Team từ ResultSet và thêm vào danh sách
            Team team = new Team();
            team.setId(rs.getInt("id"));
            team.setName(rs.getString("name"));
            team.setTopic(rs.getString("topic"));
            team.setDetails(rs.getString("details"));
            team.setProjectId(rs.getInt("project_id")); // Thiết lập projectId
            team.setStatus(rs.getString("status")); // Thiết lập status
            teams.add(team);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return teams;
}

// Phương thức để lấy một đội nhóm theo ID
public Team getTeamById(int teamId) {
    Team team = null;
    String sql = "SELECT id, name, topic, details, project_id, status FROM team WHERE id = ?"; // Điều kiện lọc theo teamId

    try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
        stm.setInt(1, teamId); // Thiết lập giá trị cho tham số teamId
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            team = new Team();
            team.setId(rs.getInt("id"));
            team.setName(rs.getString("name"));
            team.setTopic(rs.getString("topic"));
            team.setDetails(rs.getString("details"));
            team.setProjectId(rs.getInt("project_id"));
            team.setStatus(rs.getString("status"));
        }
    } catch (SQLException e) {
        BaseDAO.printSQLException(e);
    }
    return team; // Trả về đội nhóm tìm được
}


    public void updateTeam(Team team) {
        String sql = "UPDATE team SET name = ?, topic = ?, details = ?, project_id = ?, status = ? WHERE id = ?";

        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, team.getName());
            stm.setString(2, team.getTopic()); // Giả sử bạn có trường topic trong Team
            stm.setString(3, team.getDetails()); // Giả sử bạn có trường details trong Team
            stm.setInt(4, team.getProjectId());
            stm.setString(5, team.getStatus()); // Giả sử bạn có trường status trong Team
            stm.setInt(6, team.getId()); // ID của đội nhóm để cập nhật

            // Thực thi câu lệnh cập nhật
            stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
    }




    // Phương thức để lấy danh sách thành viên theo projectId
    public List<TeamMember> getMembersByProjectId(int projectId) {
        List<TeamMember> members = new ArrayList<>();
        String sql = "SELECT m.id, m.team_id, m.user_id, m.start_date, m.end_date, m.status "
                   + "FROM team_member m "
                   + "JOIN team t ON m.team_id = t.id "
                   + "WHERE t.project_id = ?"; // Điều kiện lọc theo projectId

        try (Connection cnt = BaseDAO.getConnection(); 
             PreparedStatement stm = cnt.prepareStatement(sql)) {

            stm.setInt(1, projectId); // Thiết lập giá trị cho tham số projectId
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                TeamMember member = new TeamMember();
                member.setId(rs.getInt("id"));
                member.setTeamId(rs.getInt("team_id"));
                member.setUserId(rs.getInt("user_id"));
                member.setStartDate(rs.getDate("start_date"));
                member.setEndDate(rs.getDate("end_date"));
                member.setStatus(rs.getInt("status"));

                members.add(member);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Xử lý ngoại lệ
        }
        return members; // Trả về danh sách member theo projectId
    }




 public static void main(String[] args) {
    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO(); // Tạo đối tượng ProjectConfigDAO
    String keyword = "Dev"; // Từ khóa tìm kiếm, có thể thay đổi tùy ý
    int projectId = 1; // ID của dự án mà bạn muốn tìm kiếm nhóm

    // Gọi phương thức tìm kiếm
    List<Team> teams = projectConfigDAO.searchTeamsByName(keyword);

    // In kết quả ra console
    if (teams.isEmpty()) {
        System.out.println("Không tìm thấy nhóm nào cho dự án ID: " + projectId + " với từ khóa: " + keyword);
    } else {
        System.out.println("Danh sách nhóm tìm thấy cho dự án ID: " + projectId + " với từ khóa: " + keyword);
        for (Team team : teams) {
            System.out.println("ID: " + team.getId() + ", Tên nhóm: " + team.getName());
        }
    }
}


}
