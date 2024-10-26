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
import model.Project;
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
                case "/add-project":
                    projectAdd(request, response); // Hiển thị form thêm mới dự án
                    break;
                case "/edit-project":
                    editProject(request, response); // Hiển thị form cập nhật dự án
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
        String projectCode = request.getParameter("projectCode");
        String status = request.getParameter("status");
        String keyword = request.getParameter("keyword");

        List<Project> projects;

        // Kiểm tra từng điều kiện tìm kiếm
        if (projectCode != null && !projectCode.isEmpty()) {
            projects = searchByProjectCode(projectCode);
        } else if (status != null && !status.isEmpty()) {
            projects = searchByStatus(status);
        } else if (keyword != null && !keyword.isEmpty()) {
            projects = searchByKeyword(keyword);
        } else {
            projects = projectService.getAllProjects(); // Lấy tất cả dự án nếu không có điều kiện nào
        }

        // Lưu danh sách và các tham số vào request
        request.setAttribute("listProjects", projects);
        request.setAttribute("projectCode", projectCode);
        request.setAttribute("status", status);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("/WEB-INF/member/projectlist.jsp").forward(request, response); // Chuyển tiếp đến JSP
    }

    private List<Project> searchByProjectCode(String projectCode) {
        return projectService.getProjectsByCode(projectCode); // Tìm kiếm theo mã dự án
    }

    private List<Project> searchByStatus(String status) {
        // Kiểm tra và chuyển đổi trạng thái từ chuỗi sang boolean
        boolean isActive = Boolean.parseBoolean(status);
        return projectService.searchProjectsByStatus(isActive); // Tìm kiếm theo trạng thái
    }

    private List<Project> searchByKeyword(String keyword) {
        return projectService.searchProjectsByKeyword(keyword); // Tìm kiếm theo từ khóa
    }

    private void projectAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/member/project-add.jsp").forward(request, response); // Chuyển đến trang thêm dự án
    }

    private void editProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectIdString = request.getParameter("id"); // Lấy ID dự án dưới dạng String
        Project project = null;

        try {
            int projectId = Integer.parseInt(projectIdString); // Chuyển đổi String sang int
            project = projectService.getProjectById(projectId); // Lấy thông tin dự án để cập nhật

            // Nếu tìm thấy project
            if (project != null) {
                // Không cần ánh xạ lại typeCode và departmentCode từ service
                // Nếu typeCode và departmentCode đã có trong project, bạn chỉ cần sử dụng chúng

                // Lưu thông tin project vào request và chuyển đến trang chỉnh sửa
                request.setAttribute("project", project);
                request.getRequestDispatcher("/WEB-INF/member/project-edit.jsp").forward(request, response);
            } else {
                // Xử lý nếu không tìm thấy dự án
                request.setAttribute("errorMessage", "Project not found.");
                listProjects(request, response);
            }
        } catch (NumberFormatException e) {
            // Xử lý trường hợp ID không hợp lệ (không phải là số)
            request.setAttribute("errorMessage", "Invalid project ID.");
            listProjects(request, response); // Có thể redirect về danh sách dự án
        } catch (Exception e) {
            // Xử lý lỗi chung (ví dụ lỗi kết nối cơ sở dữ liệu)
            request.setAttribute("errorMessage", "An error occurred while processing the project.");
            listProjects(request, response);
        }
    }

   private void updateProject(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String projectIdString = request.getParameter("id");
    String name = request.getParameter("name");
    String code = request.getParameter("code");
    String typeIdString = request.getParameter("typeId");
    String departmentIdString = request.getParameter("departmentId");
    String startDateString = request.getParameter("startDate");
    String details = request.getParameter("details");
    String statusString = request.getParameter("status");

    try {
        int projectId = Integer.parseInt(projectIdString);
        int typeId = Integer.parseInt(typeIdString);
        int departmentId = Integer.parseInt(departmentIdString);
        boolean status = statusString.equals("active");

        Project project = new Project();
        project.setId(projectId);
        project.setName(name);
        project.setCode(code);
        project.setTypeId(typeId);
        project.setDepartmentId(departmentId);
        
        if (startDateString != null && !startDateString.isEmpty()) {
            LocalDate startDate = LocalDate.parse(startDateString);
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
            project.setStartDate(sqlStartDate);
        }

        project.setDetails(details);
        project.setStatus(status);

        projectService.updateProject(project);

        response.sendRedirect(request.getContextPath() + "/projectlist");
    } catch (NumberFormatException e) {
        request.setAttribute("errorMessage", "Invalid input format.");
        listProjects(request, response);
    } catch (DateTimeParseException e) {
        request.setAttribute("errorMessage", "Invalid date format.");
        listProjects(request, response);
    } catch (Exception e) {
        request.setAttribute("errorMessage", "Error updating project: " + e.getMessage());
        listProjects(request, response);
    }
}

private void insertProject(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String name = request.getParameter("name");
    String code = request.getParameter("code");
    String startDateString = request.getParameter("startDate");
    String details = request.getParameter("details");
    String statusString = request.getParameter("status");
    String bizTermString = request.getParameter("bizterm");
    String departmentIdString = request.getParameter("department");
    String typeIdString = request.getParameter("type");

    Project project = new Project();

    try {
        // Kiểm tra tham số không null
        if (name == null || code == null || details == null || statusString == null ||
            bizTermString == null || departmentIdString == null || typeIdString == null) {
            throw new IllegalArgumentException("One or more parameters are missing.");
        }

        project.setName(name);
        project.setCode(code);

        // Xử lý startDate nếu có
        if (startDateString != null && !startDateString.isEmpty()) {
            try {
                LocalDate startDate = LocalDate.parse(startDateString);
                project.setStartDate(java.sql.Date.valueOf(startDate));
            } catch (DateTimeParseException e) {
                request.setAttribute("errorMessage", "Invalid date format for start date. Please use yyyy-MM-dd.");
                projectAdd(request, response);
                return;
            }
        }

        project.setDetails(details);
        project.setStatus("active".equals(statusString)); // Đặt trạng thái dự án

        // Chuyển đổi bizTerm từ chuỗi sang int
        try {
            int bizTermId = Integer.parseInt(bizTermString);
            project.setBizTerm(bizTermId); // Đặt giá trị bizTerm là số
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid biz term ID.");
            projectAdd(request, response);
            return;
        }

        // Chuyển đổi departmentId và typeId
        try {
            int departmentId = Integer.parseInt(departmentIdString);
            int typeId = Integer.parseInt(typeIdString);
            project.setDepartmentId(departmentId);
            project.setTypeId(typeId);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format for department or type.");
            projectAdd(request, response);
            return;
        }

        // Kiểm tra xem departmentId và typeId có hợp lệ không
        if (departmentIdString.isEmpty() || typeIdString.isEmpty()) {
            request.setAttribute("errorMessage", "Department and Type must not be empty.");
            projectAdd(request, response);
            return;
        }

        // Gọi service để chèn project vào database
        boolean success = projectService.insertProject(project);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/projectlist");
        } else {
            request.setAttribute("errorMessage", "Error inserting project.");
            projectAdd(request, response);
        }
    } catch (Exception e) {
        request.setAttribute("errorMessage", "Error inserting project: " + e.getMessage());
        projectAdd(request, response);
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
