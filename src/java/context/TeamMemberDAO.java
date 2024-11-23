package context;

import model.TeamMember;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamMemberDAO {

    // Fetch a single team member by ID
    public TeamMember getOne(int id) throws SQLException {
        String query = "SELECT * FROM team_member WHERE id = ?";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToTeamMember(rs);
            }
        }
        return null;
    }

    // Fetch a list of team members by Team ID
    public List<TeamMember> getListByTeamId(int teamId) throws SQLException {
        String query = "SELECT * FROM team_member WHERE team_id = ?";
        List<TeamMember> list = new ArrayList<>();
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToTeamMember(rs));
            }
        }
        return list;
    }

    // Insert a new team member
    public void create(TeamMember teamMember) throws SQLException {
        String query = "INSERT INTO team_member (team_id, user_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setTeamMemberParameters(ps, teamMember);
            ps.executeUpdate();
        }
    }

    // Update an existing team member
    public void update(TeamMember teamMember) throws SQLException {
        String query = "UPDATE team_member SET team_id = ?, user_id = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setTeamMemberParameters(ps, teamMember);
            ps.setInt(6, teamMember.getId());
            ps.executeUpdate();
        }
    }

    // Change the status of a team member
    public void changeStatus(int id, boolean newStatus) throws SQLException {
        String query = "UPDATE team_member SET status = ? WHERE id = ?";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setBoolean(1, newStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private TeamMember mapResultSetToTeamMember(ResultSet rs) throws SQLException {
        return new TeamMember(
                rs.getInt("id"),
                rs.getObject("team_id", Integer.class),
                rs.getObject("user_id", Integer.class),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getBoolean("status")
        );
    }

    private void setTeamMemberParameters(PreparedStatement ps, TeamMember teamMember) throws SQLException {
        ps.setObject(1, teamMember.getTeamId(), Types.INTEGER);
        ps.setObject(2, teamMember.getUserId(), Types.INTEGER);
        ps.setDate(3, teamMember.getStartDate());
        ps.setDate(4, teamMember.getEndDate());
        ps.setBoolean(5, teamMember.getStatus());
    }
}
