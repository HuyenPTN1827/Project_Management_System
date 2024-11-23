package service;

import context.TeamDAO;
import java.sql.SQLException;
import java.util.List;
import model.Team;

/**
 * Service class for managing teams.
 */
public class TeamService {

    private final TeamDAO teamDAO;

    public TeamService() {
        this.teamDAO = new TeamDAO();
    }

    // Get a team by ID
    public Team getTeamById(int id) {
        return teamDAO.getOne(id);
    }

    // Get a list of teams by name and status
    public List<Team> getTeamList(String name,String status) {
        return teamDAO.getList(name, status);
    }

    // Add a new team
    public int addNewTeam(Team team) throws SQLException {
        return teamDAO.addNew(team);
    }

    // Update an existing team by ID
    public boolean updateTeamById(Team team) throws SQLException {
        return teamDAO.updateById(team);
    }

    // Delete a team by ID
    public boolean deleteTeamById(int id) throws SQLException {
        return teamDAO.deleteById(id);
    }

    // Change the status of a team by ID
    public boolean changeTeamStatusById(int id, boolean status) throws SQLException {
        return teamDAO.changeStatusById(id, status);
    }
}
