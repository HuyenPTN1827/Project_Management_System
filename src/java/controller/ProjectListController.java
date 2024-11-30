package controller;

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
import model.Milestone;
import model.Project;
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
                case "/projectlist":
                    listProjects(request, response); // Hiển thị danh sách dự án
                    break;
                case "/insert-project":
                    insertProject(request, response); // Xử lý thêm dự án mới
                    break;
                
                default:
                    listProjects(request, response); // Mặc định hiển thị danh sách dự án
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy trạng thái từ request và chuyển sang int
        String statusStr = request.getParameter("status");
        String keyword = request.getParameter("keyword");

        int status = -1; // Giá trị mặc định nếu không có status
        if (statusStr != null && !statusStr.isEmpty()) {
            try {
                status = Integer.parseInt(statusStr); // Chuyển đổi trạng thái từ chuỗi thành int
            } catch (NumberFormatException e) {
                System.out.println("Trạng thái không hợp lệ, mặc định là -1");
                // Xử lý nếu trạng thái không hợp lệ (nếu cần)
            }
        }

        List<Project> projects;

        // Lấy danh sách Manager từ Service
        List<User> managers = projectService.getAllManagers();

        // Kiểm tra điều kiện tìm kiếm
        if (status >= 0 && status <= 5) {
            projects = searchByStatus(status); // Tìm kiếm theo trạng thái
        } else if (keyword != null && !keyword.isEmpty()) {
            projects = searchByKeyword(keyword); // Tìm kiếm theo từ khóa
        } else {
            projects = projectService.getAllProjects(); // Lấy tất cả dự án nếu không có điều kiện nào
        }

        // Lưu danh sách và các tham số vào request
        request.setAttribute("listManagers", managers);
        request.setAttribute("listProjects", projects);
        request.setAttribute("status", status);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response); // Chuyển tiếp đến JSP
    }

    private List<Project> searchByStatus(int status) {
        List<Project> projects = new ArrayList<>();

        // Kiểm tra xem trạng thái có hợp lệ hay không (0 <= status <= 5)
        if (status >= 0 && status <= 5) {
            // Gọi phương thức tìm kiếm các dự án theo trạng thái
            projects = projectService.searchProjectsByStatus(status);
        } else {
            System.out.println("Trạng thái không hợp lệ. Vui lòng chọn từ 0 đến 5.");
        }

        return projects;
    }

    private List<Project> searchByKeyword(String keyword) {
        return projectService.searchProjectsByKeyword(keyword); // Tìm kiếm theo từ khóa
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

        Project project = new Project();
        Allocation allocation = new Allocation();
        Milestone milestone = new Milestone();  // Khởi tạo đối tượng milestone

        try {
            // Kiểm tra các trường bắt buộc
            if (name == null || code == null || departmentIdString == null || typeIdString == null
                    || startDateString == null || endDateString == null || projectManagerIdString == null) {
                request.setAttribute("errorMessage", "Tất cả các trường bắt buộc phải được điền đầy đủ.");
                request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
                return;
            }

            project.setName(name);
            project.setCode(code);

            // Xử lý startDate
            try {
                LocalDate startDate = LocalDate.parse(startDateString);
                project.setStartDate(java.sql.Date.valueOf(startDate));
            } catch (DateTimeParseException e) {
                request.setAttribute("errorMessage", "Định dạng ngày bắt đầu không hợp lệ. Vui lòng sử dụng định dạng yyyy-MM-dd.");
                request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
                return;
            }

            // Xử lý endDate
            try {
                LocalDate endDate = LocalDate.parse(endDateString);
                project.setEndDate(java.sql.Date.valueOf(endDate));
            } catch (DateTimeParseException e) {
                request.setAttribute("errorMessage", "Định dạng ngày kết thúc không hợp lệ. Vui lòng sử dụng định dạng yyyy-MM-dd.");
                request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
                return;
            }

            // Gán các giá trị khác
            project.setDetails(details);

            // Chuyển đổi và kiểm tra departmentId và typeId
            try {
                int departmentId = Integer.parseInt(departmentIdString);
                int typeId = Integer.parseInt(typeIdString);
                int projectManagerId = Integer.parseInt(projectManagerIdString);

                project.setDepartmentId(departmentId);
                project.setTypeId(typeId);
                project.setUserId(projectManagerId);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Định dạng số không hợp lệ cho Department, Type hoặc Project Manager.");
                request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
                return;
            }

            // Xử lý estimatedEffort
            try {
                if (estimatedEffortString != null && !estimatedEffortString.isEmpty()) {
                    int estimatedEffort = Integer.parseInt(estimatedEffortString);
                    project.setEstimatedEffort(estimatedEffort);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Định dạng số không hợp lệ cho Estimated Effort.");
                request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
                return;
            }

            // Lấy full_name của người dùng từ session
            HttpSession session = request.getSession(false);
            User loggedInUser = (User) session.getAttribute("user"); // Lấy đối tượng User từ session
            // Kiểm tra nếu người dùng đã đăng nhập
            if (loggedInUser == null) {
                request.setAttribute("errorMessage", "User is not logged in.");
                request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
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
                request.setAttribute("errorMessage", "Có lỗi khi thêm dự án.");
                request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Có lỗi khi thêm dự án: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response);
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
    
}
