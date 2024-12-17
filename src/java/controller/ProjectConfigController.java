package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import model.Milestone;
import model.Team;
import model.TeamMember;
import model.User;
import model.Project;
import service.ProjectConfigService;
import service.ProjectService;
import java.util.Collections;
import java.util.Comparator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Allocation;
import model.Department;
import model.ProjectTypeSetting;
import model.Setting;
import service.DepartmentService;
import service.ProjectTypeService;
import service.TeamService;
import service.UserService;

@WebServlet()
public class ProjectConfigController extends HttpServlet {

    private ProjectConfigService projectConfigService;
    private DepartmentService deptService;
    private ProjectTypeService pjTypeService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        // Initialize the ProjectConfigService
        projectConfigService = new ProjectConfigService();
        deptService = new DepartmentService();
        pjTypeService = new ProjectTypeService();
        userService = new UserService();
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
        try {
            String action = request.getServletPath();

            switch (action) {
                case "/projectconfig" ->
                    handleProjectConfig(request, response);
                case "/updateproject" ->
                    updateProject(request, response);
                case "/filterandsearchmilestone" ->
                    filterAndsearchMilestone(request, response);
                case "/getmilestone" ->
                    getMilestoneById(request, response);
                case "/updatemilestone" ->
                    updateMilestone(request, response);

                case "/add-milestone" ->
                    showNewFormMilestone(request, response); // Show form insert milestone
                case "/insert-milestone" ->
                    insertMilestone(request, response); // Insert milestone
                case "/edit-milestone" ->
                    showEditFormMilestone(request, response); // Show form edit milestone
                case "/update-milestone" ->
                    updateMilestone(request, response); // Update milestone

                // Allocation Management
                case "/add-allocation" ->
                    showNewFormAllocation(request, response); // Show form insert allocation
                case "/insert-allocation" ->
                    insertAllocation(request, response); // Insert allocation
                case "/edit-allocation" ->
                    showEditFormAllocation(request, response); // Show form edit allocation
                case "/update-allocation" ->
                    updateAllocation(request, response); // Update allocation
                case "/change-status-allocation" ->
                    changeStatusAllocation(request, response); // Change status allocation

                default ->
                    handleProjectConfig(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectConfigController.class.getName()).log(Level.SEVERE, null, ex);
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

    // Hàm xử lý logic trang cấu hình dự án
    private void handleProjectConfig(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Start handleProjectConfig");
            String keyword = request.getParameter("keyword");
            String status = request.getParameter("status");
            TeamService teamService = new TeamService();
            List<Team> teams = teamService.getTeamList(keyword, status);
            request.setAttribute("teams", teams);

            System.out.println("+================================");
            System.out.println(teams);
            // Lấy danh sách dự án
            System.out.println("Calling listProjects");
            listProjects(request, response);

            // Lấy thông tin chỉnh sửa dự án (nếu có)
            String projectIdParam = request.getParameter("id");
            if (projectIdParam != null && !projectIdParam.isEmpty()) {
                System.out.println("Calling editProject with id: " + projectIdParam);
                editProject(request, response);
            }

            // Lấy danh sách milestones, teams và members
            System.out.println("Calling listMilestonesAndTeamsAndMembers");

            String activeTab = request.getParameter("activeTab");
            // Nếu không có activeTab, mặc định là "detail"
            if (activeTab == null || activeTab.isEmpty()) {
                activeTab = "detail"; // Tab mặc định là "detail"
            }
            request.setAttribute("activeTab", activeTab);

            listMilestones(request, response);
            listAllocation(projectIdParam, request, response);

            // Forward đến trang cấu hình dự án
            System.out.println("Forwarding to /WEB-INF/member/project-config.jsp");
            request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error in handleProjectConfig: " + e.getMessage());
            handleError(request, response, "Error loading project configuration: " + e.getMessage());
        }
    }

// Xử lý danh sách dự án
    private void listProjects(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Start listProjects");
        List<Project> projectList = projectConfigService.getAllProjects();
        if (projectList != null) {
            System.out.println("Retrieved project list with size: " + projectList.size());
        } else {
            System.out.println("Project list is null");
        }
        request.setAttribute("projectList", projectList);
    }

// Xử lý danh sách milestones, teams và members
    protected void listMilestones(HttpServletRequest request, HttpServletResponse response) {

        String projectIdParam = request.getParameter("id");

        if (projectIdParam == null || projectIdParam.isEmpty()) {
            System.out.println("Project ID is missing");
            request.setAttribute("errorMessage", "Project ID is required to list milestones.");
            return;
        }

        try {
            int projectId = Integer.parseInt(projectIdParam);
            System.out.println("Getting milestones for project ID: " + projectId);

            // Lấy danh sách milestones cho project
            List<Milestone> milestoneList = projectConfigService.getMilestonesByProjectId(projectId);
            List<Milestone> parentMilestones = projectConfigService.getMilestoneParentByProjectId(projectId);
            System.out.println("Retrieved milestone list size: " + (milestoneList != null ? milestoneList.size() : 0));

            // Sắp xếp danh sách milestone sao cho milestone có parentMilestone = null lên đầu
            Collections.sort(milestoneList, new Comparator<Milestone>() {
                @Override
                public int compare(Milestone m1, Milestone m2) {
                    // Nếu parentMilestone là null thì ưu tiên sắp xếp lên đầu
                    if (m1.getParentMilestone() == null && m2.getParentMilestone() != null) {
                        return -1;
                    } else if (m1.getParentMilestone() != null && m2.getParentMilestone() == null) {
                        return 1;
                    }
                    // Nếu cả hai đều có parentMilestone, có thể so sánh thêm theo tên hoặc theo tiêu chí khác
                    return m1.getName().compareTo(m2.getName());
                }
            });

            // Set attribute cho milestoneList, sẽ được sử dụng trong view (JSP hoặc trang tương tự)
            request.setAttribute("milestoneList", milestoneList);
            request.setAttribute("parentMilestones", parentMilestones);

        } catch (NumberFormatException e) {
            System.out.println("Invalid project ID: " + projectIdParam);
            request.setAttribute("errorMessage", "Invalid project ID.");
        } catch (Exception e) {
            System.out.println("Error retrieving project details: " + e.getMessage());
            request.setAttribute("errorMessage", "Error retrieving project details.");
        }
    }

// Xử lý chỉnh sửa dự án
    private void editProject(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Start editProject");

        String projectIdString = request.getParameter("id");

        // Kiểm tra xem projectId có hợp lệ không
        if (projectIdString == null || projectIdString.isEmpty()) {
            System.out.println("Project ID is missing");
            request.setAttribute("errorMessage", "Project ID is required.");
            return; // Dừng phương thức nếu không có Project ID
        }

        try {
            // Chuyển ID dự án từ String sang Integer
            int projectId = Integer.parseInt(projectIdString);
            System.out.println("Getting project details for ID: " + projectId);

            // Lấy thông tin dự án từ service
            Project project = projectConfigService.getProjectById(projectId);
            List<User> managers = projectConfigService.getAllManagers(); // Lấy tất cả các quản lý
            List<Project> projectListName = projectConfigService.getAllProjects(); // Lấy tất cả các dự án
            List<Department> departments = projectConfigService.getAllDepartment(); // Lấy tất cả các phòng ban
            List<Setting> bizTerms = projectConfigService.getAllBizTerms(); // Lấy tất cả BizTerm

            // Nếu tìm thấy dự án
            if (project != null) {
                System.out.println("Project found: " + project.toString());
                System.out.println("Managers found: " + managers.toString());
                System.out.println("Departments found: " + departments.toString());
                System.out.println("BizTerms found: " + bizTerms.toString());

                // Gửi thông tin dự án, quản lý, danh sách phòng ban và bizTerms tới JSP
                request.setAttribute("project", project);
                request.setAttribute("listManagers", managers);
                request.setAttribute("projectListName", projectListName);
                request.setAttribute("departments", departments);
                request.setAttribute("bizTerms", bizTerms); // Thêm danh sách BizTerm
            } else {
                // Nếu không tìm thấy dự án, gửi thông báo lỗi
                System.out.println("Project not found for ID: " + projectId);
                request.setAttribute("errorMessage", "Project not found.");
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi ID không hợp lệ (không phải là số nguyên)
            System.out.println("Invalid project ID: " + projectIdString);
            request.setAttribute("errorMessage", "Invalid project ID.");
        } catch (Exception e) {
            // Xử lý các lỗi khác
            System.out.println("Error retrieving project information: " + e.getMessage());
            request.setAttribute("errorMessage", "Error retrieving project information.");
        }
    }

// Hàm xử lý lỗi
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        System.out.println("handleError called with message: " + errorMessage);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
    }

    protected void updateProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy thông tin từ request
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        String projectName = request.getParameter("projectName");
        String projectCode = request.getParameter("projectCode");
        String estimatedEffortStr = request.getParameter("estimatedEffort");
        int bizTermId = Integer.parseInt(request.getParameter("bizTerm"));

        // Kiểm tra các trường bắt buộc
        if (projectName == null || projectName.isEmpty()
                || projectCode == null || projectCode.isEmpty()
                || estimatedEffortStr == null || estimatedEffortStr.isEmpty()) {

            request.setAttribute("error", "All required fields must be filled in completely.");
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=detail").forward(request, response);
            return;
        }

        // Kiểm tra giá trị của estimatedEffort
        int estimatedEffort;
        try {
            estimatedEffort = Integer.parseInt(estimatedEffortStr);
            if (estimatedEffort <= 0) {
                throw new NumberFormatException("Estimated Effort must be greater than 0.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Estimated Effort must be a positive integer.");
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=detail").forward(request, response);
            return;
        }

        // Kiểm tra mã code đã tồn tại
        if (projectConfigService.isCodeExists(projectCode, projectId)) {
            request.setAttribute("error", "Project code already exists. Please use a different code.");
            Project project = projectConfigService.getProjectById(projectId);
            List<User> managers = projectConfigService.getAllManagers(); // Lấy tất cả các quản lý
            List<Project> projectListName = projectConfigService.getAllProjects(); // Lấy tất cả các dự án (có thể là danh sách các tên dự án)
            List<Department> departments = projectConfigService.getAllDepartment(); // Lấy tất cả các phòng ban
            List<Setting> bizTerms = projectConfigService.getAllBizTerms(); // Lấy tất cả BizTerm
            // Truyền đối tượng project vào request
            request.setAttribute("project", project);
            request.setAttribute("listManagers", managers);
            request.setAttribute("projectListName", projectListName);
            request.setAttribute("departments", departments); // Thêm danh sách phòng ban
            request.setAttribute("bizTerms", bizTerms); // Thêm danh sách BizTerm

            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=detail").forward(request, response);
            return;  // Dừng lại nếu mã code đã tồn tại
        }

        // Chuyển từ String sang Date sử dụng SimpleDateFormat
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        // Kiểm tra các trường bắt buộc
        if (startDateStr == null || startDateStr.isEmpty()
                || endDateStr == null || endDateStr.isEmpty()) {

            request.setAttribute("error", "All required fields must be filled in completely.");
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=detail").forward(request, response);
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = dateFormat.parse(startDateStr);  // Chuyển chuỗi startDate thành Date
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = dateFormat.parse(endDateStr);  // Chuyển chuỗi endDate thành Date
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=detail").forward(request, response);
            return;  // Dừng lại nếu có lỗi
        }

        // Lấy giá trị của lastUpdated từ form (nếu có)
        String lastUpdatedStr = request.getParameter("lastUpdated");

        Date lastUpdated = null;
        if (lastUpdatedStr != null && !lastUpdatedStr.isEmpty()) {
            try {
                lastUpdated = dateFormat.parse(lastUpdatedStr);  // Nếu có giá trị, chuyển thành Date
            } catch (Exception e) {
                // Nếu không thể chuyển, có thể để null hoặc gán giá trị hiện tại
                e.printStackTrace();
            }
        }

        // Nếu lastUpdated là null (hoặc không có giá trị từ form), gán thời gian hiện tại
        if (lastUpdated != null) {
            lastUpdated = new Date(); // Gán thời gian hiện tại
        }

        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        int departmentId = Integer.parseInt(request.getParameter("department"));
        int projectManagerId = Integer.parseInt(request.getParameter("projectManager"));

        // Tạo đối tượng Project và cập nhật thông tin
        Project project = new Project();
        project.setId(projectId);
        project.setName(projectName);
        project.setCode(projectCode);
        project.setEstimatedEffort(estimatedEffort);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setDetails(description);
        project.setStatus(status);
        project.setDepartmentId(departmentId);
        project.setUserId(projectManagerId);
        project.setLastUpdated(lastUpdated);  // Cập nhật giá trị lastUpdated
        project.setBizTerm(bizTermId);
        // Gọi service để cập nhật thông tin vào cơ sở dữ liệu
        boolean isUpdated = projectConfigService.updateProject(project);

        // Kiểm tra nếu update thành công
        if (isUpdated) {
            // Sau khi cập nhật thành công, lấy lại thông tin dự án và các dữ liệu liên quan
            Project updatedProject = projectConfigService.getProjectById(projectId);  // Lấy dự án mới nhất từ database
            List<User> managers = projectConfigService.getAllManagers(); // Lấy tất cả các quản lý
            List<Project> projectListName = projectConfigService.getAllProjects(); // Lấy tất cả các dự án (có thể là danh sách các tên dự án)
            List<Department> departments = projectConfigService.getAllDepartment(); // Lấy tất cả các phòng ban
            List<Setting> bizTerms = projectConfigService.getAllBizTerms(); // Lấy tất cả BizTerm

// Truyền lại thông tin dự án, quản lý, và phòng ban vào request
            request.setAttribute("project", updatedProject);
            request.setAttribute("listManagers", managers);
            request.setAttribute("projectListName", projectListName);
            request.setAttribute("departments", departments);
            request.setAttribute("bizTerms", bizTerms); // Thêm danh sách BizTerm
            request.setAttribute("message", "Project updated successfully.");
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=detail").forward(request, response);
        } else {
            // Sau khi cập nhật thành công, lấy lại thông tin dự án và các dữ liệu liên quan
            Project updatedProject = projectConfigService.getProjectById(projectId);  // Lấy dự án mới nhất từ database
            List<User> managers = projectConfigService.getAllManagers(); // Lấy tất cả các quản lý
            List<Project> projectListName = projectConfigService.getAllProjects(); // Lấy tất cả các dự án (có thể là danh sách các tên dự án)
            List<Department> departments = projectConfigService.getAllDepartment(); // Lấy tất cả các phòng ban
            List<Setting> bizTerms = projectConfigService.getAllBizTerms(); // Lấy tất cả BizTerm

// Truyền lại thông tin dự án, quản lý, và phòng ban vào request
            request.setAttribute("project", updatedProject);
            request.setAttribute("listManagers", managers);
            request.setAttribute("projectListName", projectListName);
            request.setAttribute("departments", departments);
            request.setAttribute("bizTerms", bizTerms); // Thêm danh sách BizTerm
            request.setAttribute("error", "Failed to update the project.");
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=detail").forward(request, response);
        }
    }

    protected void insertMilestone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy session và thông tin người dùng từ session
        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("user"); // Lấy đối tượng User từ session

        // Kiểm tra nếu người dùng đã đăng nhập
        if (loggedInUser == null) {
            request.setAttribute("errorMessage", "User is not logged in.");
            request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
            return;
        }

        // Lấy userId từ loggedInUser và gán vào trường createdBy của milestone
        int userId = loggedInUser.getId(); // Giả sử User có phương thức getId() trả về userId

        // Lấy thông tin từ form
        String milestoneName = request.getParameter("milestoneName");
        String parentMilestoneId = request.getParameter("parentMilestone");
        String projectIdParam = request.getParameter("projectId"); // Lấy projectId từ form
        String priority = request.getParameter("priority");
        String targetDate = request.getParameter("targetDate");
        String description = request.getParameter("description");

        // Chuyển đổi kiểu dữ liệu từ string sang các kiểu tương ứng
        int parentMilestone = (parentMilestoneId != null && !parentMilestoneId.isEmpty())
                ? Integer.parseInt(parentMilestoneId) : 0;
        int priorityValue = (priority != null && !priority.isEmpty()) ? Integer.parseInt(priority) : 0;
        int projectId = (projectIdParam != null && !projectIdParam.isEmpty())
                ? Integer.parseInt(projectIdParam) : 0; // Chuyển đổi projectId sang kiểu int

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date targetDateParsed = null;
        try {
            targetDateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(targetDate);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
            return;
        }

        // Lấy thời gian hiện tại cho lastUpdated
        String lastUpdated = dateFormat.format(new Date());

        // Tạo đối tượng Milestone và set các giá trị
        Milestone milestone = new Milestone();
        milestone.setCreatedBy(userId);
        milestone.setLastUpdated(lastUpdated); // Gán thời gian hiện tại
        milestone.setName(milestoneName);
        milestone.setParentMilestone(parentMilestone);
        milestone.setPriority(priorityValue);
        milestone.setTargetDate(targetDateParsed);
        milestone.setDetails(description);
        milestone.setProjectId(projectId); // Gán projectId
        milestone.setStatus(0);  // Status mặc định là 0

        // Thêm milestone qua service
        boolean isAdded = projectConfigService.addMilestone(milestone);
        List<Project> projectList = projectConfigService.getAllProjects();
        // Thông báo kết quả và chuyển hướng
        if (isAdded) {
            request.setAttribute("message", "Milestone added successfully.");
            request.setAttribute("projectList", projectList);
            request.setAttribute("projectList", projectList);

        } else {
            request.setAttribute("error", "Failed to add milestone.");
        }

        // Chuyển hướng về trang config project hoặc trang cần thiết
        request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=milestone").forward(request, response);
    }

    private void getMilestoneById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy ID từ tham số request
            String idParam = request.getParameter("id");
            System.out.println("Received ID: " + idParam);

            int id = Integer.parseInt(idParam);

            // Lấy thông tin milestone từ service
            Milestone milestone = projectConfigService.getMilestoneById(id);

            // Kiểm tra nếu không tìm thấy milestone
            if (milestone == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Milestone not found\"}");
                return;
            }

            // Chuyển đối tượng milestone thành JSON
            Gson gson = new Gson();
            String milestoneJson = gson.toJson(milestone);

            // Đặt kiểu dữ liệu trả về là JSON
            response.setContentType("application/json");
            response.getWriter().write(milestoneJson);
        } catch (NumberFormatException e) {
            // Xử lý khi id không phải là số hợp lệ
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid ID format\"}");
        }
    }

    protected void updateMilestone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String milestoneIdStr = request.getParameter("id"); // Nhận ID của milestone
        String milestoneName = request.getParameter("milestonename");
        String createdByStr = request.getParameter("createdById");
        String projectIdStr = request.getParameter("project_Id");
        String parentMilestoneIdStr = request.getParameter("parentMilestoneId");
        String priorityStr = request.getParameter("priority");
        String targetDateString = request.getParameter("targetdate");
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        String lastUpdated = request.getParameter("lastupdated");

        // Biến lưu lỗi
        StringBuilder errors = new StringBuilder();

        // Kiểm tra dữ liệu đầu vào
        int milestoneId = 0;
        try {
            milestoneId = Integer.parseInt(milestoneIdStr);
        } catch (NumberFormatException e) {
            errors.append("Invalid Milestone ID.<br>");
        }
        if (milestoneName == null || milestoneName.trim().isEmpty()) {
            errors.append("Milestone name cannot be empty.<br>");
        }
        int createdBy = 0;
        try {
            createdBy = Integer.parseInt(createdByStr);
        } catch (NumberFormatException e) {
            errors.append("Invalid Created By ID.<br>");
        }
        int projectId = 0;
        try {
            projectId = Integer.parseInt(projectIdStr);
        } catch (NumberFormatException e) {
            errors.append("Invalid Project ID.<br>");
        }
        Integer parentMilestoneId = null; // Sử dụng Integer thay vì int để cho phép null
        if (parentMilestoneIdStr != null && !parentMilestoneIdStr.trim().isEmpty()) {
            try {
                parentMilestoneId = Integer.parseInt(parentMilestoneIdStr);
            } catch (NumberFormatException e) {
                errors.append("Invalid Parent Milestone ID.<br>");
            }
        }

        int priority = 0;
        try {
            priority = Integer.parseInt(priorityStr);
        } catch (NumberFormatException e) {
            errors.append("Invalid Priority.<br>");
        }
        Date targetDate = null;
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (targetDateString != null && !targetDateString.isEmpty()) {
                targetDate = isoFormat.parse(targetDateString);
            }
        } catch (Exception e) {
            errors.append("Invalid Target Date format.<br>");
        }

        int statusInt = 0;
        try {
            statusInt = Integer.parseInt(status);
        } catch (NumberFormatException e) {
            errors.append("Invalid Status.<br>");
        }

        // Nếu không có giá trị lastUpdated, tự động gán thời gian hiện tại
        if (lastUpdated != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lastUpdated = dateFormat.format(new Date()); // Lấy thời gian hiện tại
        }
        System.out.println("Last Updated: " + lastUpdated); // Kiểm tra giá trị của lastUpdated

        // Nếu có lỗi, trả về trang với thông báo lỗi
        if (errors.length() > 0) {
            request.setAttribute("errors", errors.toString());
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=milestone").forward(request, response);
            return;
        }

        // Nếu không có lỗi, tiếp tục xử lý cập nhật
        Milestone milestone = new Milestone();
        milestone.setId(milestoneId); // Gán ID của milestone
        milestone.setName(milestoneName);
        milestone.setCreatedBy(createdBy);
        milestone.setProjectId(projectId);
        milestone.setParentMilestone(parentMilestoneId);
        milestone.setPriority(priority);
        milestone.setTargetDate(targetDate);
        milestone.setStatus(statusInt);
        milestone.setDetails(description);
        milestone.setLastUpdated(lastUpdated);  // Gán giá trị lastUpdated đã chỉnh sửa

        boolean success = projectConfigService.updateMilestone(milestone);

        if (success) {
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=milestone").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to update the milestone. Please try again.");
            request.getRequestDispatcher("/projectconfig?id=" + projectId + "&activeTab=milestone").forward(request, response);
        }
    }

    private void filterAndsearchMilestone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy các tham số từ request
        String statusFilterParam = request.getParameter("statusFilter");
        String searchKeyword = request.getParameter("searchKeyword");

        // Lấy projectId từ request, nếu có
        String projectId = request.getParameter("projectId");

        // Lấy danh sách milestones đã lọc, truyền vào projectId để lọc theo project hiện tại
        List<Milestone> milestoneList = projectConfigService.filterAndsearch(statusFilterParam, searchKeyword, projectId);

        // Gửi danh sách milestone đã lọc tới JSP
        request.setAttribute("milestoneList", milestoneList);

        // Giữ lại giá trị filter cho UI sau khi tải lại
        request.setAttribute("statusFilter", statusFilterParam);
        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("projectId", projectId);  // Giữ lại projectId để hiển thị lựa chọn hiện tại

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
    }

    protected void editMilestone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String milestoneIdParam = request.getParameter("id");

        System.out.println("Milestone ID Param: " + milestoneIdParam);

        if (milestoneIdParam != null) {
            try {
                int milestoneId = Integer.parseInt(milestoneIdParam);
                Milestone milestone = projectConfigService.getMilestoneById(milestoneId);
                System.out.println("Milestone Retrieved: " + milestone);

                if (milestone != null) {
                    request.setAttribute("milestone", milestone);
                    request.getRequestDispatcher("/WEB-INF/member/project-config-edit.jsp").forward(request, response);
                } else {
                    System.out.println("Milestone not found.");
                    request.setAttribute("errorMessage", "Milestone not found.");
                    request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid milestone ID: " + milestoneIdParam);
                e.printStackTrace();
                request.setAttribute("errorMessage", "Invalid milestone ID.");
                request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("errorMessage", "An unexpected error occurred.");
                request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
            }
        } else {
            System.out.println("Milestone ID is missing.");
            request.setAttribute("errorMessage", "Milestone ID is required.");
            request.getRequestDispatcher("/WEB-INF/member/project-config.jsp").forward(request, response);
        }
    }

    /**
     * Filters milestones based on the status and search keyword.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    private void listAllocation(String projectIdParam, HttpServletRequest request, HttpServletResponse response) {
        //Project Type User
        int projectId = Integer.parseInt(projectIdParam);
        String keywordUser = request.getParameter("keywordUser");
        String deptIdStr = request.getParameter("deptId");
        String roleIdStr = request.getParameter("roleId");
        String statusUserStr = request.getParameter("statusUser");

        Integer deptId = deptIdStr != null && !deptIdStr.isEmpty() ? Integer.valueOf(deptIdStr) : null;
        Integer roleId = roleIdStr != null && !roleIdStr.isEmpty() ? Integer.valueOf(roleIdStr) : null;
        Boolean statusUser = statusUserStr != null && !statusUserStr.isEmpty() ? Boolean.valueOf(statusUserStr) : null;

        List<Allocation> allocation = projectConfigService.getAllAllocations(projectId, keywordUser, deptId, roleId, statusUser);
        List<Department> listDept = deptService.getAllDepartments(null, true);
        List<ProjectTypeSetting> listRole = pjTypeService.getProjectRoleList(projectId);

        request.setAttribute("listDept", listDept);
        request.setAttribute("listRole", listRole);
        request.setAttribute("allocation", allocation);
        request.setAttribute("keywordUser", keywordUser);
        request.setAttribute("deptId", deptId);
        request.setAttribute("roleId", roleId);
        request.setAttribute("statusUser", statusUser);
    }

    private void showNewFormAllocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
//        int userId = Integer.parseInt(request.getParameter("userId"));
        String dept = request.getParameter("deptId");
        String role = request.getParameter("roleId");
        String startDateStr = request.getParameter("fromDate");
        String endDateStr = request.getParameter("toDate");
        String effortStr = request.getParameter("effort");
        String description = request.getParameter("descriptionAllocation");

        Integer deptId = dept != null && !dept.isEmpty() ? Integer.valueOf(dept) : null;
        Integer roleId = role != null && !role.isEmpty() ? Integer.valueOf(role) : null;
        LocalDate startDate = startDateStr != null && !startDateStr.isEmpty() ? LocalDate.parse(startDateStr) : null;
        LocalDate endDate = endDateStr != null && !endDateStr.isEmpty() ? LocalDate.parse(endDateStr) : null;
        Double effort = effortStr != null && !effortStr.isEmpty() ? Double.valueOf(effortStr) : null;

        Project project = projectConfigService.getProjectById(projectId);
        List<Department> listDept = deptService.getAllDepartments(null, true);
        List<ProjectTypeSetting> listRole = pjTypeService.getProjectRoleList(projectId);
        List<User> listMem = userService.getAllUsers(null, deptId, null, 1);

        request.setAttribute("project", project);
        request.setAttribute("listDept", listDept);
        request.setAttribute("listRole", listRole);
        request.setAttribute("listMem", listMem);
        request.setAttribute("projectId", projectId);
//        request.setAttribute("userId", userId);
        request.setAttribute("deptId", deptId);
        request.setAttribute("roleId", roleId);
        request.setAttribute("fromDate", startDate);
        request.setAttribute("toDate", endDate);
        request.setAttribute("effort", effort);
        request.setAttribute("descriptionAllocation", description);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/allocation-detail.jsp");
        dispatcher.forward(request, response);
    }

    private void insertAllocation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        int memId = Integer.parseInt(request.getParameter("memId"));
        LocalDate startDate = LocalDate.parse(request.getParameter("fromDate"));
        String endDateStr = request.getParameter("toDate");
        Double effort = Double.valueOf(request.getParameter("effort"));
        String description = request.getParameter("descriptionAllocation");

        LocalDate endDate = endDateStr != null && !endDateStr.isEmpty() ? LocalDate.parse(endDateStr) : null;

        Allocation al = new Allocation();
        al.setCreatedBy(userId);
        al.setStartDate(startDate);
        al.setEndDate(endDate);
        al.setEffortRate(effort);
        al.setDescription(description);
        al.setDeptId(deptId);
        al.setUserId(memId);
        al.setProjectId(projectId);
        al.setProjectRole(roleId);

        projectConfigService.insertAllocation(al);
        response.sendRedirect("projectconfig?id=" + projectId + "&activeTab=allocation");
    }

    private void showEditFormAllocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
//        int userId = Integer.parseInt(request.getParameter("userId"));
        int id = Integer.parseInt(request.getParameter("id"));
//        int deptId = Integer.parseInt(request.getParameter("deptId"));
        String action = request.getParameter("action");

        Project project = projectConfigService.getProjectById(projectId);
        Allocation al = projectConfigService.getAllocationById(id);
        List<Department> listDept = deptService.getAllDepartments(null, true);
        List<ProjectTypeSetting> listRole = pjTypeService.getProjectRoleList(projectId);
        List<User> listMem = userService.getAllUsers(null, al.getDept().getId(), null, 1);

        request.setAttribute("project", project);
        request.setAttribute("al", al);
        request.setAttribute("listDept", listDept);
        request.setAttribute("listRole", listRole);
        request.setAttribute("listMem", listMem);
        request.setAttribute("projectId", projectId);
//        request.setAttribute("userId", userId);
//        request.setAttribute("deptId", deptId);
        request.setAttribute("action", action);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/allocation-detail.jsp");
        dispatcher.forward(request, response);
    }

    private void updateAllocation(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int id = Integer.parseInt(request.getParameter("id"));
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        int memId = Integer.parseInt(request.getParameter("memId"));
        LocalDate startDate = LocalDate.parse(request.getParameter("fromDate"));
        String endDateStr = request.getParameter("toDate");
        Double effort = Double.valueOf(request.getParameter("effort"));
        String description = request.getParameter("descriptionAllocation");
        boolean status = Boolean.parseBoolean(request.getParameter("statusAllocation"));

        LocalDate endDate = endDateStr != null && !endDateStr.isEmpty() ? LocalDate.parse(endDateStr) : null;

        Allocation al = new Allocation();
        al.setId(id);
        al.setUpdateBy(userId);
        al.setStartDate(startDate);
        al.setEndDate(endDate);
        al.setEffortRate(effort);
        al.setDescription(description);
        al.setStatus(status);
        al.setDeptId(deptId);
        al.setUserId(memId);
        al.setProjectId(projectId);
        al.setProjectRole(roleId);

        projectConfigService.updateAllocation(al);
        response.sendRedirect("projectconfig?id=" + projectId + "&activeTab=allocation");
    }

    private void changeStatusAllocation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Allocation allocation = new Allocation();
        allocation.setId(id);
        allocation.setStatus(!status); // If status is true, set to false; if false, set to true
        allocation.setUpdateBy(userId);

        projectConfigService.changeStatusAllocation(allocation);
        response.sendRedirect("projectconfig?id=" + projectId + "&activeTab=allocation");
    }

    private void showNewFormMilestone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy projectId từ tham số request
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        // Lấy danh sách milestone dựa trên projectId
        List<Milestone> parentMilestones = projectConfigService.getMilestoneParentByProjectId(projectId);

        // Đưa dữ liệu vào request attribute
        request.setAttribute("projectId", projectId);
        request.setAttribute("parentMilestones", parentMilestones);

        // Chuyển tiếp đến milestone-detail.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/milestone-detail.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormMilestone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        String action = request.getParameter("action");

        List<Milestone> parentMilestones = projectConfigService.getMilestoneParentByProjectId(projectId);
        Milestone milestone = projectConfigService.getMilestoneById(id);

        request.setAttribute("projectId", projectId);
        request.setAttribute("parentMilestones", parentMilestones);
        request.setAttribute("milestone", milestone);
        request.setAttribute("action", action);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/milestone-detail.jsp");
        dispatcher.forward(request, response);
    }
}
