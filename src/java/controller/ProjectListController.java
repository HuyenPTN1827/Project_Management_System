package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import model.Allocation;
import model.Department;
import model.Milestone;
import model.Project;
import model.ProjectType;
import model.Setting;
import model.User;
import service.ProjectService;
//BachHD

@WebServlet
public class ProjectListController extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService(); // Khởi tạo ProjectService
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath(); // Lấy action từ URL

        try {
            switch (action) {
                case "/projectlist" ->
                    listProjects(request, response); // Hiển thị danh sách dự án
                case "/add-project" ->
                    showNewForm(request, response); // Hiển thị danh sách dự án
                case "/check-project-code" ->
                    checkProjectCode(request, response);
                case "/insert-project" ->
                    insertProject(request, response); // Xử lý thêm dự án mới

                default ->
                    listProjects(request, response); // Mặc định hiển thị danh sách dự án
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy các tham số từ request
        String statusStr = request.getParameter("status");
        String keyword = request.getParameter("keyword");
        String projectTypeStr = request.getParameter("typeId");
        String departmentStr = request.getParameter("deptId");

        int status = -1; // Giá trị mặc định nếu không có status
        int projectType = -1; // Giá trị mặc định nếu không có loại dự án
        int department = -1; // Giá trị mặc định nếu không có phòng ban

        // Chuyển đổi status từ String sang int
        if (statusStr != null && !statusStr.isEmpty()) {
            try {
                status = Integer.parseInt(statusStr); // Chuyển đổi trạng thái từ chuỗi thành int
            } catch (NumberFormatException e) {
                System.out.println("Trạng thái không hợp lệ, mặc định là -1");
            }
        }

        // Chuyển đổi projectType và department từ String sang int nếu cần
        if (projectTypeStr != null && !projectTypeStr.isEmpty()) {
            try {
                projectType = Integer.parseInt(projectTypeStr);
            } catch (NumberFormatException e) {
                System.out.println("Loại dự án không hợp lệ, mặc định là -1");
            }
        }

        if (departmentStr != null && !departmentStr.isEmpty()) {
            try {
                department = Integer.parseInt(departmentStr);
            } catch (NumberFormatException e) {
                System.out.println("Phòng ban không hợp lệ, mặc định là -1");
            }
        }

        // Lấy danh sách Manager, ProjectType và Department từ Service
        List<User> managers = projectService.getAllManagers();
        List<ProjectType> projectTypes = projectService.getAllProjectType();
        List<Department> departments = projectService.getAllDepartment();

        // Lấy danh sách tất cả dự án
        List<Project> projects = projectService.getAllProjects(); // Lấy tất cả dự án trước

        // Tìm kiếm theo các điều kiện
        if (status >= 0 && status <= 5) {
            projects = searchByStatus(status, projects); // Tìm kiếm theo trạng thái
        }
        if (keyword != null && !keyword.isEmpty()) {
            projects = searchByKeyword(keyword, projects); // Tìm kiếm theo từ khóa
        }
        if (projectType >= 0) {
            projects = searchByProjectType(projectType, projects); // Tìm kiếm theo loại dự án
        }
        if (department >= 0) {
            projects = searchByDepartment(department, projects); // Tìm kiếm theo phòng ban
        }

        // Nếu không có kết quả, có thể thêm thông báo cho người dùng
        if (projects.isEmpty()) {
            request.setAttribute("message", "Không tìm thấy dự án nào.");
        }

        // Lưu danh sách vào request
        request.setAttribute("listManagers", managers);
        request.setAttribute("listProjectTypes", projectTypes);
        request.setAttribute("listDepartments", departments);
        request.setAttribute("listProjects", projects);
        request.setAttribute("status", status);
        request.setAttribute("keyword", keyword);
        request.setAttribute("projectType", projectType);
        request.setAttribute("department", department);

        request.getRequestDispatcher("/WEB-INF/member/project-list.jsp").forward(request, response);
    }

    private List<Project> searchByStatus(int status, List<Project> projects) {
        List<Project> filteredProjects = new ArrayList<>();

        // Kiểm tra trạng thái có hợp lệ hay không (0 <= status <= 5)
        if (status >= 0 && status <= 5) {
            // Duyệt qua danh sách dự án và lọc theo trạng thái
            for (Project project : projects) {
                if (project.getStatus() == status) {
                    filteredProjects.add(project);
                }
            }
        } else {
            System.out.println("Trạng thái không hợp lệ.");
        }

        return filteredProjects; // Trả về danh sách dự án đã lọc
    }

    private List<Project> searchByKeyword(String keyword, List<Project> projects) {
        List<Project> filteredProjects = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            // Duyệt qua danh sách dự án và lọc theo từ khóa
            for (Project project : projects) {
                if (project.getName().toLowerCase().contains(keyword.toLowerCase())
                        || project.getCode().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredProjects.add(project);
                }
            }
        }

        return filteredProjects; // Trả về danh sách dự án đã lọc theo từ khóa
    }

    private List<Project> searchByDepartment(int department, List<Project> projects) {
        List<Project> filteredProjects = new ArrayList<>();

        if (department >= 0) {
            // Duyệt qua danh sách dự án và lọc theo phòng ban
            for (Project project : projects) {
                if (project.getDepartmentId() == department) {
                    filteredProjects.add(project);
                }
            }
        } else {
            System.out.println("Department ID không hợp lệ.");
        }

        return filteredProjects; // Trả về danh sách dự án đã lọc
    }

    private List<Project> searchByProjectType(int projectType, List<Project> projects) {
        List<Project> filteredProjects = new ArrayList<>();

        if (projectType >= 0) {
            // Duyệt qua danh sách dự án và lọc theo loại dự án
            for (Project project : projects) {
                if (project.getTypeId() == projectType) {
                    filteredProjects.add(project);
                }
            }
        } else {
            System.out.println("Project type ID không hợp lệ.");
        }

        return filteredProjects; // Trả về danh sách dự án đã lọc
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách Manager từ Service
        List<User> managers = projectService.getAllManagers();
        List<ProjectType> projecttypes = projectService.getAllProjectType();
        List<Department> departments = projectService.getAllDepartment();
        List<Setting> bizTerms = projectService.getAllBizTerm();
        // Path to user information input form page
        request.setAttribute("listDepartments", departments);
        request.setAttribute("listProjectTypes", projecttypes);
        request.setAttribute("listManagers", managers);
        request.setAttribute("listBizTerms", bizTerms);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/project-add.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String startDateString = request.getParameter("startDate");
        String endDateString = request.getParameter("endDate");
        String details = request.getParameter("details");
        String departmentIdString = request.getParameter("department");
        String typeIdString = request.getParameter("type");
        String estimatedEffortString = request.getParameter("estimatedEffort");
        String projectManagerIdString = request.getParameter("projectManagerId");
        String bizTermIdString = request.getParameter("bizterm");
        Project project = new Project();
        Allocation allocation = new Allocation();
        Milestone milestone = new Milestone();  // Khởi tạo đối tượng milestone

        try {
            // Kiểm tra các trường bắt buộc
            if (name == null || name.isEmpty() || code == null || code.isEmpty() || estimatedEffortString == null || estimatedEffortString.isEmpty()
                    || departmentIdString == null || departmentIdString.isEmpty() || typeIdString == null || typeIdString.isEmpty()
                    || startDateString == null || startDateString.isEmpty() || endDateString == null || endDateString.isEmpty()
                    || projectManagerIdString == null || projectManagerIdString.isEmpty() || bizTermIdString == null || bizTermIdString.isEmpty()) {

                request.setAttribute("errorMessage", "All required fields must be filled in completely.");
                request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").include(request, response);

                return;
            }

            // Kiểm tra mã code đã tồn tại
//            if (projectService.isCodeExists(code)) {
//                request.setAttribute("errorMessage", "Project code already exists. Please use a different code.");
//                request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").include(request, response);
//                return;
//            }
            project.setName(name);
            project.setCode(code);

            // Xử lý startDate
            try {
                LocalDate startDate = LocalDate.parse(startDateString);
                project.setStartDate(java.sql.Date.valueOf(startDate));
            } catch (DateTimeParseException e) {

                request.setAttribute("errorMessage", "Invalid start date format.");
                request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response);

                return;
            }

            // Xử lý endDate
            try {
                LocalDate endDate = LocalDate.parse(endDateString);
                project.setEndDate(java.sql.Date.valueOf(endDate));
            } catch (DateTimeParseException e) {

                request.setAttribute("errorMessage", "Invalid end date format.");
                request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response);

                return;
            }

            // Gán các giá trị khác
            project.setDetails(details);

            // Chuyển đổi và kiểm tra departmentId và typeId
            try {
                int departmentId = Integer.parseInt(departmentIdString);
                int typeId = Integer.parseInt(typeIdString);
                int projectManagerId = Integer.parseInt(projectManagerIdString);
                int bizTermId = Integer.parseInt(bizTermIdString);

                project.setDepartmentId(departmentId);
                project.setTypeId(typeId);
                project.setUserId(projectManagerId);
                project.setBizTerm(bizTermId);
            } catch (NumberFormatException e) {

                request.setAttribute("errorMessage", "Invalid number format for Department, Type or Project Manager.");
                request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response);

                return;
            }

            // Xử lý estimatedEffort
            try {
                if (estimatedEffortString != null && !estimatedEffortString.isEmpty()) {
                    int estimatedEffort = Integer.parseInt(estimatedEffortString);
                    // Kiểm tra estimatedEffort phải là số dương lớn hơn 0
                    if (estimatedEffort <= 0) {
                        request.setAttribute("errorMessage", "Estimated Effort must be a positive number greater than 0.");
                        request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response);
                        return;
                    }

                    project.setEstimatedEffort(estimatedEffort);
                }
            } catch (NumberFormatException e) {

                request.setAttribute("errorMessage", "Invalid number format for Estimated Effort.");
                request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response);

                return;
            }

            // Lấy full_name của người dùng từ session
            HttpSession session = request.getSession(false);
            User loggedInUser = (User) session.getAttribute("user"); // Lấy đối tượng User từ session
            // Kiểm tra nếu người dùng đã đăng nhập
            if (loggedInUser == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Lấy userId từ loggedInUser và gán vào trường createdBy của project
            int userId = loggedInUser.getId(); // Giả sử User có phương thức getId() trả về userId
            project.setCreatedBy(userId); // Lưu userId vào project

            // Gán các giá trị cho allocation
            allocation.setCreatedBy(userId);
//            allocation.setCreatedAt(new java.util.Date());  // Thiết lập thời gian tạo
//            allocation.setStartDate(java.sql.Date.valueOf(LocalDate.parse(startDateString))); // Ngày bắt đầu
            allocation.setDeptId(Integer.parseInt(departmentIdString));
            allocation.setUserId(Integer.parseInt(projectManagerIdString));
            allocation.setProjectRole(4); // Giả sử project_role là 4

            // Tạo milestone cha và gán các giá trị
            // Tạo milestone cha sau khi thêm project thành công
            milestone.setCreatedBy(userId);
            System.out.println("CreatedBy User ID: " + milestone.getCreatedBy());
            milestone.setLastUpdated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            milestone.setName(project.getName() + " - Main Milestone");
            milestone.setParentMilestone(null); // NULL vì là milestone cha
            milestone.setPriority(1); // Mức độ ưu tiên cao
            milestone.setTargetDate(project.getEndDate()); // Ngày dự kiến hoàn thành dự án
            milestone.setStatus(0); // Trạng thái mặc định
            milestone.setActualDate(null); // Chưa hoàn thành

            // Gọi service để thêm project, allocation và milestone vào database
            boolean success = projectService.insertProject(project, allocation, milestone);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/projectlist");
            } else {

                request.setAttribute("errorMessage", "There was an error adding the project.");
                request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "There was an error adding the project: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Xử lý request
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Xử lý request
    }

    @Override
    public String getServletInfo() {
        return "Project List Controller";
    }

    private void checkProjectCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        boolean exists = projectService.isCodeExists(code);

        // Return the result as JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"exists\": " + exists + "}");
    }

}
