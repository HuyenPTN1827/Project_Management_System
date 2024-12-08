/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Milestone;
import model.Project;
import model.Allocation;
import model.Department;
import model.ProjectType;
import model.User;

/**
 *
 * @author Admin
 */
public class ProjectDAO {

    //BachHD
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();

        // Cập nhật câu SQL để lấy thêm tên loại dự án từ bảng project_type
        String sql = "SELECT p.id, p.name, p.code, p.estimated_effort, p.start_date, p.details, "
                + "p.end_date, p.last_updated, p.status, p.type_id, p.department_id, d.name AS department_name, "
                + "pt.name AS type_name "
                + // Lấy tên loại dự án từ bảng project_type
                "FROM project p "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id"; // Thêm LEFT JOIN với bảng project_type

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setCode(rs.getString("code"));
                project.setEstimatedEffort(rs.getInt("estimated_effort"));
                project.setStartDate(rs.getDate("start_date"));
                project.setDetails(rs.getString("details"));
                project.setEndDate(rs.getDate("end_date"));
                project.setLastUpdated(rs.getDate("last_updated"));
                project.setStatus(rs.getInt("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));
                // Lấy tên phòng ban từ kết quả truy vấn
                project.setDepartmentName(rs.getString("department_name"));
                // Lấy tên loại dự án từ kết quả truy vấn
                project.setTypeName(rs.getString("type_name"));

                projects.add(project);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return projects;
    }

    // Tìm kiếm dự án theo trạng thái
    public List<Project> getProjectsByStatus(int status) {
        List<Project> projects = new ArrayList<>();
        // Cập nhật câu truy vấn SQL để bỏ phần liên quan đến setting (biz_term)
        String sql = "SELECT p.*, pt.code AS type_code, d.code AS department_code "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "WHERE p.status = ?"; // Điều kiện tìm kiếm theo trạng thái

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, status); // Thiết lập trạng thái tìm kiếm
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setEndDate(rs.getDate("end_date")); // Lấy ngày kết thúc
                project.setLastUpdated(rs.getDate("last_updated")); // Lấy ngày cập nhật
                project.setEstimatedEffort(rs.getInt("estimated_effort")); // Lấy nỗ lực ước tính
                project.setStatus(rs.getInt("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));

                // Lấy mã loại dự án và mã bộ phận (không lấy tên setting nữa)
                project.setTypeCode(rs.getString("type_code"));
                project.setDepartmentCode(rs.getString("department_code"));

                projects.add(project); // Thêm dự án vào danh sách
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return projects; // Trả về danh sách dự án tìm thấy
    }

    public List<Project> searchProjectsByKeyword(String keyword) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.code, p.estimated_effort, p.start_date, p.details, "
                + "p.end_date, p.last_updated, p.status, p.type_id, p.department_id, "
                + "pt.code AS type_code, d.code AS department_code "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "WHERE p.name LIKE ? OR p.code LIKE ?"; // Tìm kiếm theo tên hoặc mã dự án

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%"; // Thêm ký tự đại diện để tìm kiếm gần đúng
            stm.setString(1, searchKeyword);
            stm.setString(2, searchKeyword);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setCode(rs.getString("code"));
                project.setEstimatedEffort(rs.getInt("estimated_effort"));
                project.setStartDate(rs.getDate("start_date"));
                project.setDetails(rs.getString("details"));
                project.setEndDate(rs.getDate("end_date"));
                project.setLastUpdated(rs.getDate("last_updated"));
                project.setStatus(rs.getInt("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));

                // Lấy thêm mã loại dự án và mã bộ phận
                project.setTypeCode(rs.getString("type_code"));
                project.setDepartmentCode(rs.getString("department_code"));

                projects.add(project); // Thêm dự án vào danh sách
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return projects; // Trả về danh sách dự án tìm thấy
    }

    public Project getProjectById(int id) {
        Project project = null; // Khởi tạo project

        // Câu lệnh SQL để lấy thông tin dự án với các trường cần thiết
        String sql = "SELECT p.id, p.code, p.name, p.details, p.start_date, p.end_date, p.last_updated, "
                + "p.estimated_effort, p.status, p.type_id, p.department_id, pt.code AS type_code, d.code AS department_code "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "WHERE p.id = ?"; // Điều kiện WHERE để lấy dự án theo ID

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, id); // Thiết lập ID dự án để tìm kiếm
            ResultSet rs = stm.executeQuery();

            if (rs.next()) { // Kiểm tra nếu có kết quả
                project = new Project(); // Khởi tạo đối tượng Project
                project.setId(rs.getInt("id"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setEndDate(rs.getDate("end_date")); // Lấy ngày kết thúc
                project.setLastUpdated(rs.getDate("last_updated")); // Lấy ngày cập nhật
                project.setEstimatedEffort(rs.getInt("estimated_effort")); // Lấy nỗ lực ước tính
                project.setStatus(rs.getInt("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));

                // Lấy mã loại dự án và mã bộ phận (có thể bỏ nếu không cần thiết)
                project.setTypeCode(rs.getString("type_code")); // Lấy mã loại dự án
                project.setDepartmentCode(rs.getString("department_code")); // Lấy mã bộ phận
            }
        } catch (SQLException e) {
            // Ghi lại lỗi SQL
            BaseDAO.printSQLException(e);
        }

        return project; // Trả về đối tượng Project tìm thấy, hoặc null nếu không tìm thấy
    }

    public boolean updateProject(Project project) {
        // Cập nhật câu lệnh SQL để loại bỏ trường 'biz_term'
        String sql = "UPDATE project SET code = ?, name = ?, details = ?, start_date = ?, "
                + "end_date = ?, last_updated = ?, status = ?, type_id = ?, department_id = ?, "
                + "estimated_effort = ? WHERE id = ?"; // Câu lệnh SQL để cập nhật dự án

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            // In giá trị của các trường để debug (nên loại bỏ trong môi trường production)
            System.out.println("Updating project with ID: " + project.getId());
            System.out.println("Code: " + project.getCode());
            System.out.println("Name: " + project.getName());
            System.out.println("Details: " + project.getDetails());
            System.out.println("Start Date: " + project.getStartDate());
            System.out.println("End Date: " + project.getEndDate());
            System.out.println("Last Updated: " + project.getLastUpdated());
            System.out.println("Estimated Effort: " + project.getEstimatedEffort());
            System.out.println("Status: " + project.getStatus());
            System.out.println("Type ID: " + project.getTypeId());
            System.out.println("Department ID: " + project.getDepartmentId());

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stm.setString(1, project.getCode());
            stm.setString(2, project.getName());
            stm.setString(3, project.getDetails());
            stm.setDate(4, new java.sql.Date(project.getStartDate().getTime()));

            // Xử lý trường ngày kết thúc (null nếu không có giá trị)
            if (project.getEndDate() != null) {
                stm.setDate(5, new java.sql.Date(project.getEndDate().getTime()));
            } else {
                stm.setNull(5, java.sql.Types.DATE); // Xử lý trường null cho end_date
            }

            // Cập nhật thời gian hiện tại vào trường last_updated
            stm.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));

            stm.setInt(7, project.getStatus());
            stm.setInt(8, project.getTypeId());
            stm.setInt(9, project.getDepartmentId());
            stm.setInt(10, project.getEstimatedEffort());
            stm.setInt(11, project.getId()); // ID của dự án cần cập nhật

            int rowsAffected = stm.executeUpdate(); // Thực hiện câu lệnh cập nhật

            return rowsAffected > 0; // Trả về true nếu có dòng nào bị ảnh hưởng
        } catch (SQLException e) {
            // Ghi log chi tiết lỗi
            BaseDAO.printSQLException(e);
        }

        return false; // Trả về false nếu có lỗi xảy ra
    }

    public boolean insertProject(Project project, Allocation allocation, Milestone milestone) {
        
        // Chuỗi SQL để chèn vào bảng project
        String sqlProject = "INSERT INTO project (name, code, estimated_effort, start_date, details, end_date, last_updated, status, type_id, department_id, user_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Chuỗi SQL để chèn vào bảng allocation (dữ liệu liên quan đến phân bổ)
        String allocationSql = "INSERT INTO allocation (created_by, created_at, last_updated, start_date, "
                + "status, dept_id, user_id, project_id, project_role) "
                + "VALUES (?, NOW(), NOW(), CURDATE(), 1, ?, ?, ?, ?)"; // 'active' là giá trị cố định cho cột status

        // Chuỗi SQL để chèn vào bảng milestone
        String sqlMilestone = "INSERT INTO milestone (project_id, name, status, created_by, last_updated, parent_milestone, priority, target_date, actual_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = BaseDAO.getConnection()) {
            // Bắt đầu transaction để đảm bảo tính toàn vẹn
            cnt.setAutoCommit(false);

            try (PreparedStatement stmProject = cnt.prepareStatement(sqlProject, Statement.RETURN_GENERATED_KEYS)) {
                // Thiết lập các tham số cho PreparedStatement cho bảng project
                stmProject.setString(1, project.getName());
                stmProject.setString(2, project.getCode());
                stmProject.setInt(3, project.getEstimatedEffort());
                stmProject.setDate(4, new java.sql.Date(project.getStartDate().getTime()));
                stmProject.setString(5, project.getDetails());

                // Xử lý trường end_date nếu có
                if (project.getEndDate() != null) {
                    stmProject.setDate(6, new java.sql.Date(project.getEndDate().getTime()));
                } else {
                    stmProject.setNull(6, java.sql.Types.DATE);
                }

                // Thiết lập thời gian hiện tại cho trường last_updated
                stmProject.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
                stmProject.setInt(8, project.getStatus());
                stmProject.setInt(9, project.getTypeId());
                stmProject.setInt(10, project.getDepartmentId());
                stmProject.setInt(11, project.getUserId());

                // Thực thi câu lệnh và lấy khóa chính được tạo
                int affectedRows = stmProject.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmProject.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int projectId = generatedKeys.getInt(1); // Lấy ID của dự án mới tạo

                            milestone.setLastUpdated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // last_updated
                            milestone.setName(project.getName() + " - Main Milestone"); // milestone_name
                            milestone.setParentMilestone(null); // NULL vì là milestone cha
                            milestone.setPriority(1); // Mức độ ưu tiên cao
                            milestone.setTargetDate(project.getEndDate()); // Ngày dự kiến hoàn thành dự án
                            milestone.setStatus(0); // Trạng thái mặc định
                            milestone.setActualDate(null); // Chưa hoàn thành

                            // Sau khi chèn dự án, chèn dữ liệu phân bổ vào bảng allocation
                            try (PreparedStatement stmAllocation = cnt.prepareStatement(allocationSql)) {
                                stmAllocation.setInt(1, allocation.getCreatedBy()); // created_by
                                stmAllocation.setInt(2, allocation.getDeptId()); // dept_id
                                stmAllocation.setInt(3, allocation.getUserId()); // user_id
                                stmAllocation.setInt(4, projectId); // project_id
                                stmAllocation.setInt(5, 1); // project_role

                                // Thực thi câu lệnh chèn vào bảng allocation
                                int allocationRows = stmAllocation.executeUpdate();
                                if (allocationRows > 0) {
                                    // Sau khi chèn allocation thành công, chèn milestone vào bảng milestone
                                    try (PreparedStatement stmMilestone = cnt.prepareStatement(sqlMilestone)) {
                                        stmMilestone.setInt(1, projectId); // project_id
                                        stmMilestone.setString(2, milestone.getName()); // milestone_name
                                        stmMilestone.setInt(3, milestone.getStatus()); // status
                                        stmMilestone.setInt(4, milestone.getCreatedBy()); // created_by
                                        stmMilestone.setString(5, milestone.getLastUpdated()); // last_updated
                                        stmMilestone.setObject(6, milestone.getParentMilestone()); // parent_milestone
                                        stmMilestone.setInt(7, milestone.getPriority()); // priority
                                        stmMilestone.setDate(8, new java.sql.Date(milestone.getTargetDate().getTime())); // target_date
                                        stmMilestone.setObject(9, milestone.getActualDate()); // actual_date

                                        int milestoneRows = stmMilestone.executeUpdate();
                                        if (milestoneRows > 0) {
                                            // Commit transaction nếu tất cả các bảng đều được chèn thành công
                                            cnt.commit();
                                            project.setId(projectId); // Lưu lại ID dự án vào đối tượng project
                                            return true;
                                        } else {
                                            // Rollback nếu chèn milestone thất bại
                                            cnt.rollback();
                                        }
                                    }
                                } else {
                                    // Rollback nếu chèn allocation thất bại
                                    cnt.rollback();
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                // Nếu có lỗi, rollback lại toàn bộ giao dịch
                cnt.rollback();
                BaseDAO.printSQLException(e);
            }

        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return false; // Nếu không thành công, trả về false
    }

    private void setProjectParameters(PreparedStatement stm, Project project) throws SQLException {
        stm.setString(1, project.getName());          // set name
        stm.setString(2, project.getCode());          // set code
        stm.setInt(3, project.getEstimatedEffort());  // set estimatedEffort
        stm.setDate(4, new java.sql.Date(project.getStartDate().getTime())); // set startDate

        // Nếu `details` là null, thay thế bằng chuỗi rỗng
        stm.setString(5, project.getDetails() != null ? project.getDetails() : "");

        // Xử lý trường hợp `endDate` có thể là null
        if (project.getEndDate() != null) {
            stm.setDate(6, new java.sql.Date(project.getEndDate().getTime())); // set endDate
        } else {
            stm.setNull(6, java.sql.Types.DATE);
        }

        // Nếu `lastUpdated` là null, thay thế bằng ngày hiện tại
        if (project.getLastUpdated() != null) {
            stm.setDate(7, new java.sql.Date(project.getLastUpdated().getTime())); // set lastUpdated
        } else {
            stm.setDate(7, new java.sql.Date(System.currentTimeMillis())); // Ngày hiện tại
        }

        stm.setInt(8, project.getStatus());        // set status
        stm.setInt(9, project.getTypeId());           // set typeId
        stm.setInt(10, project.getDepartmentId());    // set departmentId
    }

    public List<User> getAllManagers() {
        List<User> managers = new ArrayList<>();

        // Câu SQL để lấy các manager có role_id = 4
        String sql = "SELECT id, full_name, email, mobile, password, notes, status, role_id, username "
                + "FROM user WHERE role_id = 4"; // Lọc theo role_id = 4

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setNotes(rs.getString("notes"));
                user.setStatus(rs.getInt("status"));
                user.setRole_id(rs.getInt("role_id"));
                user.setUsername(rs.getString("username"));

                // Thêm user vào danh sách managers
                managers.add(user);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return managers;
    }

    public List<Project> getProjectsDropDown() {
        List<Project> projects = new ArrayList<>();

        String sql = "SELECT * FROM pms.project";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                projects.add(project);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return projects;
    }

//    HuyenPTNHE160769
    public List<Project> getProjectListByUserID(int userId) {
        List<Project> project = new ArrayList<>();

        String sql = """
                     SELECT DISTINCT p.id, p.name, p.code FROM pms.project p 
                     JOIN pms.allocation a ON p.id = a.project_id 
                     WHERE a.user_id = ? 
                     ORDER BY p.id DESC;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("p.id"));
                p.setName(rs.getString("p.name"));
                p.setCode(rs.getString("p.code"));
                project.add(p);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return project;
    }

//    HuyenPTNHE160769
    public List<Milestone> getMilestonesByProjectId(Integer projectId) {
        List<Milestone> milestones = new ArrayList<>();
        String sql = """
                     SELECT DISTINCT m.id, m.name FROM pms.milestone m
                     JOIN pms.allocation a ON m.project_id = a.project_id
                     WHERE 1=1""";
        if (projectId != null) {
            sql += " AND m.project_id = ?";
        }
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            if (projectId != null) {
                stm.setInt(1, projectId);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Milestone m = new Milestone();
                m.setId(rs.getInt("m.id"));
                m.setName(rs.getString("m.name"));
                milestones.add(m);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return milestones;
    }

    public Project getProjectsName(int id) {
        String sql = "SELECT * FROM pms.project where id = " + id;
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                return project;
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return null;
    }
    
    public List<ProjectType> getAllProjectTypes() {
    List<ProjectType> projectTypes = new ArrayList<>();
    String sql = "SELECT id, code, name FROM project_type";

    try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ProjectType projectType = new ProjectType();
            projectType.setId(rs.getInt("id"));
            projectType.setCode(rs.getString("code"));
            projectType.setName(rs.getString("name"));

            // Thêm projectType vào danh sách
            projectTypes.add(projectType);
        }
    } catch (SQLException e) {
        BaseDAO.printSQLException(e);
    }

    return projectTypes;
}
public List<Department> getAllDepartments() {
    List<Department> departments = new ArrayList<>();
    String sql = "SELECT id, code, name FROM department";

    try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setCode(rs.getString("code"));
            department.setName(rs.getString("name"));

            // Thêm department vào danh sách
            departments.add(department);
        }
    } catch (SQLException e) {
        BaseDAO.printSQLException(e);
    }

    return departments;
}

public boolean isCodeExists(String code) {
    String query = "SELECT 1 FROM project WHERE code = ? LIMIT 1"; // Dùng LIMIT 1 để tối ưu
    try (Connection conn = BaseDAO.getConnection(); // Sử dụng phương thức getConnection từ BaseDAO
         PreparedStatement stmt = conn.prepareStatement(query)) {
        // Gán giá trị mã code vào câu truy vấn
        stmt.setString(1, code);

        // Thực thi truy vấn
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next(); // Nếu có kết quả trả về, mã đã tồn tại
        }
    } catch (SQLException e) {
        BaseDAO.printSQLException(e); // Ghi log lỗi để kiểm tra
    }
    return false; // Mặc định trả về false nếu có lỗi
}




}
