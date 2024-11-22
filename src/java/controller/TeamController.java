package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Team;
import service.TeamService;

/**
 * Controller for managing teams.
 */
public class TeamController extends HttpServlet {

    private final TeamService teamService = new TeamService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                listTeams(request, response);
            } else if (action.equals("detail")) {
                showTeamDetail(request, response);
            } else if (action.equals("add")) {
                showAddForm(request, response);
            } else if (action.equals("edit")) {
                showEditForm(request, response);
            } else if (action.equals("delete")) {
                deleteTeam(request, response);
            } else if (action.equals("changeStatus")) {
                changeStatusTeam(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listTeams(HttpServletRequest request, HttpServletResponse response)
            throws Exception, ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String status = request.getParameter("status");

        List<Team> teams = teamService.getTeamList(keyword, status);
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/WEB-INF/admin/team-list.jsp").forward(request, response);
    }

    private void showTeamDetail(HttpServletRequest request, HttpServletResponse response)
            throws Exception, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Team team = teamService.getTeamById(id);
        request.setAttribute("team", team);
        request.getRequestDispatcher("/WEB-INF/admin/team-detail.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/admin/team-add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Team existingTeam = teamService.getTeamById(id);
        request.setAttribute("team", existingTeam);
        request.getRequestDispatcher("/WEB-INF/admin/team-edit.jsp").forward(request, response);
    }

    private void deleteTeam(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        teamService.deleteTeamById(id);
        response.sendRedirect("team?action=list");
    }

    private void changeStatusTeam(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean newStatus = Boolean.parseBoolean(request.getParameter("status"));
        teamService.changeTeamStatusById(id, newStatus);
        response.sendRedirect("team?action=list");
    }

    private void addTeam(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        String name = request.getParameter("name");
        String topic = request.getParameter("topic");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String pid = request.getParameter("pid");

        Team newTeam = new Team(0, name, topic, description, Integer.valueOf(pid), status);
        teamService.addNewTeam(newTeam);
        response.sendRedirect("team?action=list");
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response)
            throws Exception, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String topic = request.getParameter("topic");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String pid = request.getParameter("pid");

        Team updatedTeam = new Team(id, name, topic, description, Integer.valueOf(pid), status);
        teamService.updateTeamById(updatedTeam);
        response.sendRedirect("team?action=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "add":
                        addTeam(request, response);
                        break;
                    case "edit":
                        updateTeam(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Team Controller Servlet";
    }
}
