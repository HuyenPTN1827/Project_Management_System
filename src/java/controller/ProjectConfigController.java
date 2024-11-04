package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import model.Milestone;
import model.Team;
import model.TeamMember;
import service.ProjectConfigService;

@WebServlet()
public class ProjectConfigController extends HttpServlet {

    private ProjectConfigService projectConfigService;

    @Override
    public void init() throws ServletException {
        // Initialize the ProjectConfigService
        projectConfigService = new ProjectConfigService();
    }

    /**
     * Processes requests for both HTTP GET and POST methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/projectconfig":
                listMilestonesAndTeamsAndMembers(request, response);
                break;
            case "/filterandsearchmilestone":
                filterAndsearchMilestone(request, response);
                break;
            case "/update":
                updateMilestone(request, response);
                break;
            case "/edit":
                editMilestone(request, response);
                break;
            case "/delete":
//                deleteMilestone(request, response);
                break;
            case "/searchteam":
                searchTeam(request, response);
                break;
            case "/editteam":
                editTeam(request, response);
                break; 
            case "/updateteam": 
                updateTeam(request, response);
            case "/addteam": 
                addTeam(request, response);
            case "/deleteteam": 
                deleteTeam(request, response);    
            break;
            default:
                listMilestones(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP GET method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void listMilestones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectIdParam = request.getParameter("id");

        if (projectIdParam != null) {
            try {
                int projectId = Integer.parseInt(projectIdParam);
                List<Milestone> milestoneList = projectConfigService.getMilestonesByProjectId(projectId);
                request.setAttribute("milestoneList", milestoneList);
            } catch (NumberFormatException e) {
                // Handle the exception if the project ID is invalid
                request.setAttribute("milestoneList", List.of());
                request.setAttribute("errorMessage", "Invalid project ID.");
            }
        } else {
            request.setAttribute("milestoneList", List.of());
        }

        request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
    }

    /**
     * Filters milestones based on the status and search keyword.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void filterAndsearchMilestone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String statusFilterParam = request.getParameter("statusFilter");
        String searchKeyword = request.getParameter("searchKeyword");

        // Lấy danh sách milestones đã lọc
        List<Milestone> milestoneList = projectConfigService.filterAndsearch(statusFilterParam, searchKeyword);
        request.setAttribute("milestoneList", milestoneList);

        // Giữ lại giá trị filter cho UI sau khi tải lại
//        request.setAttribute("statusFilter", statusFilterParam);
//        request.setAttribute("searchKeyword", searchKeyword);
        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
    }

    protected void editMilestone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String milestoneIdParam = request.getParameter("id");

        if (milestoneIdParam != null) {
            try {
                int milestoneId = Integer.parseInt(milestoneIdParam);
                Milestone milestone = projectConfigService.getMilestoneById(milestoneId);

                if (milestone != null) {
                    request.setAttribute("milestone", milestone);
                    request.getRequestDispatcher("/WEB-INF/member/project-config-edit.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Milestone not found.");
                    request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid milestone ID.");
                request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Milestone ID is required.");
            request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
        }
    }

    protected void updateMilestone(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String milestoneIdParam = request.getParameter("id");
    String code = request.getParameter("code");
    String name = request.getParameter("name");
    String details = request.getParameter("details");
    String priority = request.getParameter("priority");
    String status = request.getParameter("status");
    String projectIdParam = request.getParameter("projectId"); // Lấy projectId từ request
    String deadlineString = request.getParameter("deadline"); // Lấy deadline từ request

    if (milestoneIdParam != null && projectIdParam != null) { // Kiểm tra projectId
        try {
            int milestoneId = Integer.parseInt(milestoneIdParam);
            int projectId = Integer.parseInt(projectIdParam); // Ép kiểu projectId
            Milestone milestone = projectConfigService.getMilestoneById(milestoneId);

            if (milestone != null) {
                milestone.setCode(code);
                milestone.setName(name);
                milestone.setDetails(details);
                milestone.setPriority(priority);
                milestone.setStatus(status);

                // Chuyển đổi deadline từ String sang LocalDate hoặc Date
                if (deadlineString != null && !deadlineString.isEmpty()) {
                    LocalDate deadline = LocalDate.parse(deadlineString);
                    milestone.setDeadline(java.sql.Date.valueOf(deadline)); // Nếu deadline là kiểu Date
                }

                projectConfigService.updateMilestone(milestone); // Cập nhật milestone
                request.setAttribute("successMessage", "Milestone updated successfully.");

                // Điều hướng lại với projectId
                response.sendRedirect(request.getContextPath() + "/projectconfig?id=" + projectId);
                return;
            } else {
                request.setAttribute("errorMessage", "Milestone not found.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid milestone or project ID.");
        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Invalid date format for deadline.");
        }
    } else {
        request.setAttribute("errorMessage", "Milestone ID and Project ID are required.");
    }

    request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
}


  protected void listMilestonesAndTeamsAndMembers(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String projectIdParam = request.getParameter("id");
    System.out.println("Received project ID: " + projectIdParam); // Kiểm tra projectIdParam

    // Danh sách milestones, teams và members
    List<Milestone> milestoneList = new ArrayList<>();
    List<Team> teamList = new ArrayList<>();
    List<TeamMember> memberList = new ArrayList<>();

    if (projectIdParam != null) {
        try {
            int projectId = Integer.parseInt(projectIdParam);
            milestoneList = projectConfigService.getMilestonesByProjectId(projectId);
            teamList = projectConfigService.getTeamsByProjectId(projectId);
            memberList = projectConfigService.getMembersByProjectId(projectId); 
            
            // In ra các danh sách đã lấy được để kiểm tra
            System.out.println("Milestones: " + milestoneList.size());
            System.out.println("Teams: " + teamList.size());
            System.out.println("Members: " + memberList.size());

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid project ID.");
            System.out.println("Error parsing project ID: " + e.getMessage());
        }
    }

    // Lưu cả ba danh sách vào request
    request.setAttribute("milestoneList", milestoneList);
    request.setAttribute("teamList", teamList);
    request.setAttribute("memberList", memberList); 

    // Chuyển tiếp đến JSP
    request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
}



private void searchTeam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchKeyword = request.getParameter("searchTeam");
        
        List<Team> teamList = projectConfigService.searchTeamsByName(searchKeyword); // Giả sử bạn đã cài đặt phương thức searchTeams trong ProjectConfigService

        request.setAttribute("teamList", teamList); // Lưu danh sách nhóm vào request
        request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response); // Chuyển tiếp đến JSP
    }

protected void editTeam(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String teamIdParam = request.getParameter("id");

    if (teamIdParam != null) {
        try {
            int teamId = Integer.parseInt(teamIdParam);
            Team team = projectConfigService.getTeamById(teamId); // Giả sử bạn đã cài đặt phương thức getTeamById trong ProjectConfigService

            if (team != null) {
                request.setAttribute("team", team);
                request.getRequestDispatcher("/WEB-INF/member/project-config-team-edit.jsp").forward(request, response); // Chuyển đến trang JSP để chỉnh sửa thông tin đội
            } else {
                request.setAttribute("errorMessage", "Team not found.");
                request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid team ID.");
            request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
        }
    } else {
        request.setAttribute("errorMessage", "Team ID is required.");
        request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
    }
}

protected void updateTeam(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String teamIdParam = request.getParameter("id");
    String projectIdParam = request.getParameter("projectId"); 

    String name = request.getParameter("name");
    String topic = request.getParameter("topic");
    String details = request.getParameter("details");
    String statusParam = request.getParameter("status"); // Vẫn giữ nguyên là String

    // Kiểm tra xem tham số teamId và projectId có tồn tại không
    if (teamIdParam != null && projectIdParam != null) {
        try {
            int teamId = Integer.parseInt(teamIdParam);
            int projectId = Integer.parseInt(projectIdParam);
            Team team = projectConfigService.getTeamById(teamId);

            if (team != null) {
                // Thay vì chuyển đổi statusParam sang số nguyên, giữ nguyên dưới dạng String
                // Cập nhật thông tin đội
                team.setName(name);
                team.setTopic(topic);
                team.setDetails(details);
                team.setStatus(statusParam); // Cập nhật giá trị status
                team.setProjectId(projectId); // Cập nhật projectId cho đội nếu cần

                // Gọi phương thức cập nhật trong service
                projectConfigService.updateTeam(team);

                // Đặt thông báo thành công
                request.setAttribute("successMessage", "Team updated successfully.");

                // Chuyển hướng đến trang cấu hình dự án với ID dự án tương ứng
                response.sendRedirect(request.getContextPath() + "/projectconfig?id=" + projectId);
                return;
            } else {
                // Nếu không tìm thấy đội
                request.setAttribute("errorMessage", "Team not found.");
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi ID đội hoặc projectId không hợp lệ
            request.setAttribute("errorMessage", "Invalid team ID or project ID.");
        }
    } else {
        // Nếu không có ID đội hoặc projectId
        request.setAttribute("errorMessage", "Team ID and Project ID are required.");
    }

    // Quay lại trang cấu hình nếu có lỗi
    request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
}

    private void addTeam(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Lấy dữ liệu từ form
    String name = request.getParameter("name");
    String topic = request.getParameter("topic");
    String details = request.getParameter("details");
    String projectIdStr = request.getParameter("projectId"); // Lấy giá trị projectId như chuỗi
    String status = request.getParameter("status");

    int projectId = 0; // Khởi tạo projectId với giá trị mặc định
    if (projectIdStr != null && !projectIdStr.isEmpty()) {
        try {
            projectId = Integer.parseInt(projectIdStr); // Chuyển đổi chuỗi thành int
        } catch (NumberFormatException e) {
            // Xử lý trường hợp projectId không phải là số hợp lệ
            request.setAttribute("errorMessage", "Invalid project ID. Please enter a valid number.");
            request.getRequestDispatcher("WEB-INF/member/project-config-team-add.jsp").forward(request, response);
            return; // Dừng lại nếu có lỗi
        }
    } else {
        // Xử lý trường hợp projectId là null hoặc rỗng
        request.setAttribute("errorMessage", "Project ID cannot be null or empty.");
        request.getRequestDispatcher("WEB-INF/member/project-config-team-add.jsp").forward(request, response);
        return; // Dừng lại nếu có lỗi
    }

    // Tạo đối tượng Team từ dữ liệu đã lấy
    Team team = new Team();
    team.setName(name);
    team.setTopic(topic);
    team.setDetails(details);
    team.setProjectId(projectId);
    team.setStatus(status);

    // Gọi TeamService để thêm đội mới
    boolean success = projectConfigService.addTeam(team);

    if (success) {
        // Thêm thành công, chuyển hướng về trang cấu hình dự án
        request.setAttribute("successMessage", "Team added successfully!");
        response.sendRedirect(request.getContextPath() + "/projectconfig");
    } else {
        // Thêm thất bại, hiển thị lại trang thêm với thông báo lỗi
        request.setAttribute("errorMessage", "Failed to add the team. Please try again.");
        request.getRequestDispatcher("WEB-INF/member/project-config-team-add.jsp").forward(request, response);
    }
}

private void deleteTeam(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    String teamIdStr = request.getParameter("id");
    if (teamIdStr != null) {
        try {
            int teamId = Integer.parseInt(teamIdStr);
            boolean success = projectConfigService.deleteTeam(teamId);
            if (success) {
                request.setAttribute("successMessage", "Team deleted successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to delete the team. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid team ID. Please try again.");
        }
    } else {
        request.setAttribute("errorMessage", "Team ID cannot be null.");
    }
    // Redirect back to the teams list
    response.sendRedirect("projectconfig");
}





    /**
     * Handles the HTTP POST method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Project Configuration Controller";
    }
}
