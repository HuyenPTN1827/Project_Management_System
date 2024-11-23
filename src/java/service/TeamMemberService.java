package service;

import context.TeamMemberDAO;
import model.TeamMember;

import java.sql.SQLException;
import java.util.List;

public class TeamMemberService {
    private TeamMemberDAO teamMemberDAO;

    public TeamMemberService() {
        this.teamMemberDAO = new TeamMemberDAO();
    }

    public TeamMember getOne(int id) throws SQLException {
        return teamMemberDAO.getOne(id);
    }

    public List<TeamMember> getListByTeamId(int teamId) throws SQLException {
        return teamMemberDAO.getListByTeamId(teamId);
    }

    public void create(TeamMember teamMember) throws SQLException {
        teamMemberDAO.create(teamMember);
    }

    public void update(TeamMember teamMember) throws SQLException {
        teamMemberDAO.update(teamMember);
    }

    public void changeStatus(int id, boolean newStatus) throws SQLException {
        teamMemberDAO.changeStatus(id, newStatus);
    }
}
