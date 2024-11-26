package context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Team;

public class TeamDAO {

    // Get a single team by ID
    public Team getOne(int id) {
        Team team = null;
        String sql = "SELECT * FROM team WHERE id = ?;";
        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                team = mapToTeam(rs);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return team;
    }

    // Get a list of teams, filtered by name and status
    public List<Team> getList(String name, String status) {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM team WHERE 1=1";

        if (name != null && !name.trim().isEmpty()) {
            sql += " AND LOWER(name) LIKE ?";
        }
        if (status != null) {
            sql += " AND status = ?";
        }

        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            int index = 1;

            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(index++, "%" + name.trim().toLowerCase() + "%");
            }
            if (status != null) {
                stmt.setString(index++, status);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                teams.add(mapToTeam(rs));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return teams;
    }

    // Add a new team
    public int addNew(Team team) {
        int result = 0;
        String sql = "INSERT INTO team (name, topic, details, project_id, status) VALUES (?, ?, ?, ?, ?);";
        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, team.getName());
            stmt.setString(2, team.getTopic());
            stmt.setString(3, team.getDetails());
            stmt.setObject(4, team.getProjectId(), Types.INTEGER);
            stmt.setString(5, team.getStatus());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

    // Update a team by ID
    public boolean updateById(Team team) {
        boolean updated = false;
        String sql = "UPDATE team SET name = ?, topic = ?, details = ?, project_id = ?, status = ? WHERE id = ?;";
        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, team.getName());
            stmt.setString(2, team.getTopic());
            stmt.setString(3, team.getDetails());
            stmt.setObject(4, team.getProjectId(), Types.INTEGER);
            stmt.setString(5, team.getStatus());
            stmt.setInt(6, team.getId());

            updated = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return updated;
    }

    // Delete a team by ID
    public boolean deleteById(int id) {
        boolean deleted = false;
        String sql = "DELETE FROM team WHERE id = ?;";
        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            deleted = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return deleted;
    }

    // Change the status of a team by ID
    public boolean changeStatusById(int id, boolean status) {
        boolean updated = false;
        String sql = "UPDATE team SET status = ? WHERE id = ?;";
        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, id);

            updated = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return updated;
    }

    // Helper method to map a ResultSet to a Team object
    private Team mapToTeam(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setName(rs.getString("name"));
        team.setTopic(rs.getString("topic"));
        team.setDetails(rs.getString("details"));
        team.setProjectId(rs.getObject("project_id") != null ? rs.getInt("project_id") : null);
        team.setStatus(rs.getString("status"));
        team.setProject(new ProjectDAO().getProjectsName(rs.getInt("project_id")));
        return team;
    }
}
