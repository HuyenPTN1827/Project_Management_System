package controller;

import model.TeamMember;
import service.TeamMemberService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class TeamMemberController extends HttpServlet {

    private TeamMemberService teamMemberService;

    @Override
    public void init() throws ServletException {
        teamMemberService = new TeamMemberService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("getOne".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                TeamMember teamMember = teamMemberService.getOne(id);
                request.setAttribute("teamMember", teamMember);
            } else if ("getListByTeamId".equals(action)) {
                int teamId = Integer.parseInt(request.getParameter("teamId"));
                List<TeamMember> teamMembers = teamMemberService.getListByTeamId(teamId);
                request.setAttribute("teamMembers", teamMembers);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/teamMember.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action)) {
                // Create a new TeamMember
                TeamMember teamMember = new TeamMember();
                teamMember.setTeamId(Integer.parseInt(request.getParameter("teamId")));
                teamMember.setUserId(Integer.parseInt(request.getParameter("userId")));
                teamMember.setStartDate(Date.valueOf(request.getParameter("startDate")));
                teamMember.setEndDate(Date.valueOf(request.getParameter("endDate")));
                teamMember.setStatus(Boolean.parseBoolean(request.getParameter("status")));

                teamMemberService.create(teamMember);
                response.sendRedirect("teamMember?action=getListByTeamId&teamId=" + teamMember.getTeamId());

            } else if ("update".equals(action)) {
                // Update an existing TeamMember
                TeamMember teamMember = new TeamMember();
                teamMember.setId(Integer.parseInt(request.getParameter("id")));
                teamMember.setTeamId(Integer.parseInt(request.getParameter("teamId")));
                teamMember.setUserId(Integer.parseInt(request.getParameter("userId")));
                teamMember.setStartDate(Date.valueOf(request.getParameter("startDate")));
                teamMember.setEndDate(Date.valueOf(request.getParameter("endDate")));
                teamMember.setStatus(Boolean.parseBoolean(request.getParameter("status")));

                teamMemberService.update(teamMember);
                response.sendRedirect("teamMember?action=getListByTeamId&teamId=" + teamMember.getTeamId());

            } else if ("changeStatus".equals(action)) {
                // Change the status of a TeamMember
                int id = Integer.parseInt(request.getParameter("id"));
                boolean newStatus = Boolean.parseBoolean(request.getParameter("newStatus"));

                teamMemberService.changeStatus(id, newStatus);
                response.sendRedirect("teamMember?action=getOne&id=" + id);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new ServletException(e);
        }
    }

}
