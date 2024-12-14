/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Allocation;
import model.Department;
import model.Team;
import model.TeamMember;
import model.Milestone;
import model.User;
import model.Project;
import model.ProjectTypeSetting;
import model.Setting;
import service.ProjectService;

/**
 *
 * @author Admin
 */
public class ProjectConfigDAO {

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        // Cập nhật câu truy vấn để thực hiện JOIN với bảng department
        String sql = """
                     SELECT p.id, p.name, p.code, p.estimated_effort, p.start_date, p.details, p.end_date, 
                                      p.last_updated, p.status, p.type_id, p.department_id, d.name AS department_name
                                      FROM project p
                                      LEFT JOIN department d ON p.department_id = d.id;"""; // Thực hiện JOIN với bảng department

        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
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
                project.setDepartmentName(rs.getString("department_name"));

                // Thêm project vào danh sách
                projects.add(project);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Xử lý ngoại lệ
        }

        return projects; // Trả về danh sách các dự án
    }

    public List<Milestone> getMilestonesByProjectId(int projectId) {
        List<Milestone> milestones = new ArrayList<>();

        // Truy vấn lấy thông tin từ bảng milestone và project
        String sql = "SELECT m.id, m.created_by, m.last_updated, m.name, "
                + "m.parent_milestone, m.priority, m.target_date, m.status, "
                + "m.actual_date, m.details, m.project_id, p.name AS project_name, "
                + "pm.name AS parent_milestone_name "
                + "FROM milestone m "
                + "LEFT JOIN project p ON m.project_id = p.id "
                + "LEFT JOIN milestone pm ON m.parent_milestone = pm.id "
                + "WHERE m.project_id = ?"; // Điều kiện lọc theo projectId

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, projectId); // Thiết lập giá trị cho tham số projectId
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Milestone milestone = new Milestone();
                milestone.setId(rs.getInt("id"));
                milestone.setCreatedBy(rs.getInt("created_by"));
                milestone.setLastUpdated(rs.getString("last_updated"));
                milestone.setName(rs.getString("name"));
                milestone.setParentMilestone(rs.getInt("parent_milestone")); // Lấy ID của milestone cha
                milestone.setParentMilestoneName(rs.getString("parent_milestone_name")); // Lấy tên milestone cha
                milestone.setPriority(rs.getInt("priority"));
                milestone.setTargetDate(rs.getDate("target_date"));
                milestone.setStatus(rs.getInt("status"));
                milestone.setActualDate(rs.getDate("actual_date"));
                milestone.setDetails(rs.getString("details"));
                milestone.setProjectId(rs.getInt("project_id"));
                milestone.setProjectName(rs.getString("project_name")); // Lấy tên project

                milestones.add(milestone);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Xử lý ngoại lệ
        }
        return milestones; // Trả về danh sách milestone
    }

    public List<Milestone> getMilestoneParentByProjectId(int projectId) {
        List<Milestone> milestones = new ArrayList<>();

        // Truy vấn lấy thông tin từ bảng milestone mà có parent_milestone là NULL và thuộc projectId cụ thể
        String sql = "SELECT m.id, m.created_by, m.last_updated, m.name, "
                + "m.parent_milestone, m.priority, m.target_date, m.status, "
                + "m.actual_date, m.details, m.project_id, p.name AS project_name, "
                + "pm.name AS parent_milestone_name "
                + "FROM milestone m "
                + "LEFT JOIN project p ON m.project_id = p.id "
                + "LEFT JOIN milestone pm ON m.parent_milestone = pm.id "
                + "WHERE m.project_id = ? AND m.parent_milestone IS NULL"; // Thêm điều kiện lọc projectId và parent_milestone NULL

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            // Set projectId vào PreparedStatement
            stm.setInt(1, projectId);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Milestone milestone = new Milestone();
                milestone.setId(rs.getInt("id"));
                milestone.setCreatedBy(rs.getInt("created_by"));
                milestone.setLastUpdated(rs.getString("last_updated"));
                milestone.setName(rs.getString("name"));
                milestone.setParentMilestone(rs.getInt("parent_milestone")); // Lấy tên milestone cha (nếu có)
                milestone.setPriority(rs.getInt("priority"));
                milestone.setTargetDate(rs.getDate("target_date"));
                milestone.setStatus(rs.getInt("status"));
                milestone.setActualDate(rs.getDate("actual_date"));
                milestone.setDetails(rs.getString("details"));
                milestone.setProjectId(rs.getInt("project_id"));
                milestone.setProjectName(rs.getString("project_name")); // Lấy tên project
                milestone.setParentMilestoneName(rs.getString("parent_milestone_name"));

                milestones.add(milestone);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Xử lý ngoại lệ
        }
        return milestones; // Trả về danh sách milestone thuộc projectId và không có parent_milestone
    }

    public List<Milestone> filterAndsearch(String status, String searchKeyword, String projectId) {
        List<Milestone> milestones = new ArrayList<>();
        String sql = "SELECT m.id, m.created_by, m.last_updated, m.name, m.parent_milestone, "
                + "m.priority, m.target_date, m.status, m.actual_date, m.details, m.project_id "
                + "FROM milestone m WHERE m.project_id = ?"; // Điều kiện lọc theo projectId

        // Thêm điều kiện trạng thái nếu không rỗng
        if (status != null && !status.isEmpty()) {
            sql += " AND m.status = ?";
        }

        // Thêm điều kiện tìm kiếm theo từ khóa nếu không rỗng
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql += " AND LOWER(m.name) LIKE ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            int parameterIndex = 1;

            // Thiết lập projectId
            stm.setString(parameterIndex++, projectId);

            // Thiết lập trạng thái nếu có
            if (status != null && !status.isEmpty()) {
                stm.setString(parameterIndex++, status);
            }

            // Thiết lập từ khóa tìm kiếm nếu có
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                stm.setString(parameterIndex++, "%" + searchKeyword.toLowerCase() + "%");
            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Milestone milestone = new Milestone();
                milestone.setId(rs.getInt("id"));
                milestone.setCreatedBy(rs.getInt("created_by"));
                milestone.setLastUpdated(rs.getString("last_updated"));
                milestone.setName(rs.getString("name"));
                milestone.setParentMilestone(rs.getInt("parent_milestone"));
                milestone.setPriority(rs.getInt("priority"));
                milestone.setTargetDate(rs.getDate("target_date"));
                milestone.setStatus(rs.getInt("status"));
                milestone.setActualDate(rs.getDate("actual_date"));
                milestone.setDetails(rs.getString("details"));
                milestone.setProjectId(rs.getInt("project_id"));

                milestones.add(milestone);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return milestones;
    }

    // Phương thức để lấy một milestone theo ID
    public Milestone getMilestoneById(int milestoneId) {
        Milestone milestone = null;

        // Truy vấn lấy dữ liệu milestone và tên của parent milestone, kèm theo username của người tạo
        String sql = "SELECT m.id, m.created_by, u.username, m.last_updated, m.name, m.parent_milestone, "
                + "m.priority, m.target_date, m.status, m.actual_date, m.details, m.project_id, "
                + "pm.name AS parent_name, p.name AS project_name " // Lấy tên của project
                + "FROM milestone m "
                + "LEFT JOIN milestone pm ON m.parent_milestone = pm.id "
                + "LEFT JOIN user u ON m.created_by = u.id "
                + "LEFT JOIN project p ON m.project_id = p.id " // JOIN với bảng project
                + "WHERE m.id = ?";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            stm.setInt(1, milestoneId); // Thiết lập giá trị cho tham số milestoneId
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                milestone = new Milestone();
                milestone.setId(rs.getInt("id"));
                milestone.setCreatedbyuserName(rs.getString("username"));
                milestone.setCreatedBy(rs.getInt("created_by"));// Gán username thay vì ID
                milestone.setLastUpdated(rs.getString("last_updated"));
                milestone.setName(rs.getString("name"));
                milestone.setParentMilestone(rs.getInt("parent_milestone"));
                milestone.setPriority(rs.getInt("priority"));
                milestone.setTargetDate(rs.getDate("target_date"));
                milestone.setStatus(rs.getInt("status"));
                milestone.setActualDate(rs.getDate("actual_date"));
                milestone.setDetails(rs.getString("details"));
                milestone.setProjectName(rs.getString("project_name"));
                milestone.setProjectId(rs.getInt("project_id"));

                // Lấy thêm tên của parent milestone
                milestone.setParentMilestoneName(rs.getString("parent_name"));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return milestone; // Trả về milestone tìm được
    }

    public boolean updateMilestone(Milestone milestone) {
        String sql = "UPDATE milestone SET created_by = ?, last_updated = ?, name = ?, parent_milestone = ?, "
                + "priority = ?, target_date = ?, status = ?, actual_date = ?, details = ?, project_id = ? "
                + "WHERE id = ?";

        boolean isUpdated = false;

        if (milestone == null || milestone.getId() <= 0 || milestone.getName() == null || milestone.getPriority() <= 0
                || milestone.getProjectId() <= 0) {
            return false; // Dữ liệu không hợp lệ
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, milestone.getCreatedBy());
            stm.setString(2, milestone.getLastUpdated());
            stm.setString(3, milestone.getName());
            // Kiểm tra parentMilestone
            if (milestone.getParentMilestone() != null) {
                stm.setInt(4, milestone.getParentMilestone());
            } else {
                stm.setNull(4, java.sql.Types.INTEGER);
            }
            stm.setInt(5, milestone.getPriority());
            stm.setDate(6, milestone.getTargetDate() != null ? new java.sql.Date(milestone.getTargetDate().getTime()) : null);
            stm.setInt(7, milestone.getStatus());
            stm.setDate(8, milestone.getActualDate() != null ? new java.sql.Date(milestone.getActualDate().getTime()) : null);
            stm.setString(9, milestone.getDetails());
            stm.setInt(10, milestone.getProjectId());
            stm.setInt(11, milestone.getId());

            int rowsAffected = stm.executeUpdate();
            isUpdated = rowsAffected > 0; // Cập nhật thành công nếu > 0
        } catch (SQLException e) {
            System.err.println("Failed to update milestone: " + e.getMessage());
            e.printStackTrace();
        }

        return isUpdated;
    }

    public Project getProjectById(int id) {
        Project project = null; // Khởi tạo project

        // Câu lệnh SQL để lấy thông tin dự án, bao gồm cả tên loại dự án và biz_term từ bảng setting
        String sql = "SELECT DISTINCT p.id, p.code, p.name, p.details, p.start_date, p.end_date, p.last_updated, "
                + "p.estimated_effort, p.status, p.type_id, p.department_id, p.user_id, "
                + "pt.code AS type_code, pt.name AS type_name, d.code AS department_code, d.name AS department_name, "
                + "s.name AS biz_term_name " // Thêm cột biz_term_name từ bảng setting
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id " // JOIN với bảng project_type
                + "LEFT JOIN department d ON p.department_id = d.id " // JOIN với bảng department
                + "LEFT JOIN setting s ON p.biz_term = s.id " // JOIN với bảng setting dựa trên bizterm
                + "WHERE p.id = ?"; // Điều kiện WHERE để tìm dự án theo ID

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, id); // Thiết lập ID dự án
            ResultSet rs = stm.executeQuery();

            if (rs.next()) { // Nếu có kết quả
                project = new Project(); // Tạo đối tượng Project
                project.setId(rs.getInt("id"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setEndDate(rs.getDate("end_date"));
                project.setLastUpdated(rs.getDate("last_updated"));
                project.setEstimatedEffort(rs.getInt("estimated_effort"));
                project.setStatus(rs.getInt("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));
                project.setUserId(rs.getInt("user_id"));
                project.setTypeCode(rs.getString("type_code")); // Mã loại dự án
                project.setTypeName(rs.getString("type_name")); // Tên loại dự án
                project.setDepartmentCode(rs.getString("department_code")); // Mã bộ phận
                project.setDepartmentName(rs.getString("department_name")); // Tên bộ phận
                project.setBizTermName(rs.getString("biz_term_name")); // Lấy tên biz_term từ bảng setting

            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Xử lý lỗi SQL
        }

        return project; // Trả về Project hoặc null nếu không tìm thấy
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

    public boolean updateProject(Project project) {
        String sql = "UPDATE project SET name = ?, code = ?, estimated_effort = ?, "
                + "start_date = ?, end_date = ?, details = ?, status = ?, department_id = ?, "
                + "user_id = ?, biz_term = ?, last_updated = ? WHERE id = ?";

        try (Connection conn = BaseDAO.getConnection(); // Sử dụng BaseDAO.getConnection()
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Gán các giá trị từ đối tượng project vào PreparedStatement
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getCode());
            stmt.setInt(3, project.getEstimatedEffort());
            stmt.setDate(4, new java.sql.Date(project.getStartDate().getTime())); // Chuyển java.util.Date sang java.sql.Date
            stmt.setDate(5, new java.sql.Date(project.getEndDate().getTime()));   // Chuyển java.util.Date sang java.sql.Date
            stmt.setString(6, project.getDetails());
            stmt.setInt(7, project.getStatus());
            stmt.setInt(8, project.getDepartmentId());
            stmt.setInt(9, project.getUserId());
            stmt.setInt(10, project.getBizTerm()); // Thêm biz_term vào PreparedStatement
            stmt.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis())); // Thêm thời gian hiện tại vào last_updated_at
            stmt.setInt(12, project.getId()); // Đảm bảo rằng project.getId() trả về id của dự án cần cập nhật

            // Thực thi câu lệnh UPDATE và kiểm tra số dòng bị ảnh hưởng
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Nếu có ít nhất 1 dòng được cập nhật, trả về true

        } catch (SQLException e) {
            // In thông báo lỗi nếu có vấn đề khi thực thi SQL
            System.err.println("SQL Error: " + e.getMessage());
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT id, name FROM department";  // Lấy danh sách tất cả các phòng ban

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);  // Xử lý lỗi SQL
        }

        return departments;
    }

// Phương thức thêm một milestone vào cơ sở dữ liệu
    public boolean insertMilestone(Milestone milestone) {
        String sql = "INSERT INTO milestone (project_id, name, status, created_by, last_updated, parent_milestone, priority, target_date, actual_date, details) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean isAdded = false;

        try (Connection conn = BaseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh SQL
            stmt.setInt(1, milestone.getProjectId()); // project_id
            stmt.setString(2, milestone.getName()); // name
            stmt.setInt(3, milestone.getStatus()); // status
            stmt.setInt(4, milestone.getCreatedBy()); // created_by
            stmt.setString(5, milestone.getLastUpdated()); // last_updated

            // Xử lý parentMilestone
            if (milestone.getParentMilestone() > 0) { // Kiểm tra giá trị hợp lệ (> 0)
                stmt.setInt(6, milestone.getParentMilestone());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER); // Gán null nếu không có parent milestone
            }

            stmt.setInt(7, milestone.getPriority()); // priority

            // Xử lý targetDate
            if (milestone.getTargetDate() != null) {
                stmt.setDate(8, new java.sql.Date(milestone.getTargetDate().getTime()));
            } else {
                stmt.setNull(8, java.sql.Types.DATE); // Gán null nếu không có target date
            }

            // Xử lý actualDate
            if (milestone.getActualDate() != null) {
                stmt.setDate(9, new java.sql.Date(milestone.getActualDate().getTime()));
            } else {
                stmt.setNull(9, java.sql.Types.DATE); // Gán null nếu không có actual date
            }

            // Xử lý details
            if (milestone.getDetails() != null && !milestone.getDetails().isEmpty()) {
                stmt.setString(10, milestone.getDetails()); // Gán giá trị cho details
            } else {
                stmt.setNull(10, java.sql.Types.VARCHAR); // Gán null nếu không có details
            }

            // Thực thi câu lệnh và kiểm tra xem có thêm thành công không
            isAdded = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Ghi log lỗi SQL
        }

        return isAdded; // Trả về true nếu thêm thành công, ngược lại trả về false
    }

    public static void main(String[] args) {
        // Giả sử bạn muốn lấy milestone với ID là 1
        int milestoneId = 1;

        // Tạo đối tượng DAO để gọi hàm getMilestoneById
        ProjectConfigDAO milestoneDAO = new ProjectConfigDAO();
        Milestone milestone = milestoneDAO.getMilestoneById(milestoneId);

        // Kiểm tra xem milestone có được trả về hay không
        if (milestone != null) {
            System.out.println("Milestone ID: " + milestone.getId());
            System.out.println("Milestone Name: " + milestone.getName());
            System.out.println("Created By: " + milestone.getCreatedbyuserName());
            System.out.println("Project Name: " + milestone.getProjectName());
            System.out.println("Parent Milestone: " + milestone.getParentMilestoneName());
            System.out.println("Priority: " + milestone.getPriority());
            System.out.println("Target Date: " + milestone.getTargetDate());
            System.out.println("Status: " + milestone.getStatus());
            System.out.println("Actual Date: " + milestone.getActualDate());
            System.out.println("Details: " + milestone.getDetails());
        } else {
            System.out.println("No milestone found with ID " + milestoneId);
        }
    }

//    HuyenPTNHE160769    
//    Select all allocations
    public List<Allocation> selectAllAllocations(int projectId, String keyword, Integer deptId, Integer roleId, Boolean status) {
        List<Allocation> allocation = new ArrayList<>();

        String sql = """
                     SELECT a.project_id, a.id, a.user_id, u.full_name, u.username, a.dept_id, 
                     d.code, a.start_date, a.end_date, a.project_role, r.name, a.effort_rate, a.status
                     FROM allocation a
                     JOIN department d ON a.dept_id = d.id
                     JOIN user u ON a.user_id = u.id
                     JOIN project p ON a.project_id = p.id
                     JOIN project_type_setting r ON a.project_role = r.id
                     WHERE a.project_id = ?""";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(u.full_name) LIKE ? OR LOWER(u.username) LIKE ?)";
        }
        if (deptId != null) {
            sql += " AND a.dept_id = ?";
        }
        if (roleId != null) {
            sql += " AND a.project_role = ?";
        }
        if (status != null) {
            sql += " AND a.status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, projectId);
            int index = 2;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";

                stm.setString(index++, keywordPattern);
                stm.setString(index++, keywordPattern);
            }
            if (deptId != null) {
                stm.setInt(index++, deptId);
            }
            if (roleId != null) {
                stm.setInt(index++, roleId);
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Allocation a = new Allocation();
                a.setId(rs.getInt("a.id"));
                Date startDate = rs.getDate("a.start_date");
                if (startDate != null) {
                    a.setStartDate(BaseDAO.MyDateUtil.getUtilDate((java.sql.Date) startDate));
                }
                Date endDate = rs.getDate("a.end_date");
                if (endDate != null) {
                    a.setEndDate(BaseDAO.MyDateUtil.getUtilDate((java.sql.Date) endDate));
                }
                a.setEffortRate(rs.getDouble("a.effort_rate"));
                a.setStatus(rs.getBoolean("a.status"));

                User u = new User();
                u.setId(rs.getInt("a.user_id"));
                u.setFull_name(rs.getString("u.full_name"));
                u.setUsername(rs.getString("u.username"));
                a.setUser(u);

                Department d = new Department();
                d.setId(rs.getInt("a.dept_id"));
                d.setCode(rs.getString("d.code"));
                a.setDept(d);

                ProjectTypeSetting r = new ProjectTypeSetting();
                r.setId(rs.getInt("a.project_role"));
                r.setName(rs.getString("r.name"));
                a.setRole(r);

                Project p = new Project();
                p.setId(rs.getInt("a.project_id"));
                a.setProject(p);

                allocation.add(a);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return allocation;
    }

//    Select allocation by id
    public Allocation selectAllocationByID(int id) {
        Allocation a = null;

        String sql = """
                     SELECT a.id, a.created_by, u1.full_name, u1.username, a.created_at, 
                     a.updated_by, u2.full_name, u2.username, a.last_updated,
                     a.dept_id, d.name, d.code, a.user_id, u3.full_name, u3.username, 
                     a.project_id, p.name, p.code, a.project_role, r.name,
                     a.start_date, a.end_date, a.effort_rate, a.description, a.status
                     FROM allocation a
                     JOIN department d ON a.dept_id = d.id
                     JOIN user u1 ON a.created_by = u1.id
                     LEFT JOIN user u2 ON a.updated_by = u2.id
                     JOIN user u3 ON a.user_id = u3.id
                     JOIN project p ON a.project_id = p.id
                     JOIN project_type_setting r ON a.project_role = r.id
                     WHERE a.id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                a = new Allocation();
                a.setId(rs.getInt("a.id"));
                a.setCreatedAt(rs.getTimestamp("a.created_at").toLocalDateTime());
                a.setLastUpdated(rs.getTimestamp("a.last_updated").toLocalDateTime());
                Date startDate = rs.getDate("a.start_date");
                if (startDate != null) {
                    a.setStartDate(BaseDAO.MyDateUtil.getUtilDate((java.sql.Date) startDate));
                }
                Date endDate = rs.getDate("a.end_date");
                if (endDate != null) {
                    a.setEndDate(BaseDAO.MyDateUtil.getUtilDate((java.sql.Date) endDate));
                }

                a.setEffortRate(rs.getDouble("a.effort_rate"));
                a.setDescription(rs.getString("a.description"));
                a.setStatus(rs.getBoolean("a.status"));

                User u1 = new User();
                u1.setId(rs.getInt("a.created_by"));
                u1.setFull_name(rs.getString("u1.full_name"));
                u1.setUsername(rs.getString("u1.username"));
                a.setCreated_by(u1);

                // Updated By User (nullable)
                if (rs.getInt("updated_by") != 0) {
                    User u2 = new User();
                    u2.setId(rs.getInt("a.updated_by"));
                    u2.setFull_name(rs.getString("u2.full_name"));
                    u2.setUsername(rs.getString("u2.username"));
                    a.setUpdated_by(u2);
                }

                User u3 = new User();
                u3.setId(rs.getInt("a.user_id"));
                u3.setFull_name(rs.getString("u3.full_name"));
                u3.setUsername(rs.getString("u3.username"));
                a.setUser(u3);

                Department d = new Department();
                d.setId(rs.getInt("a.dept_id"));
                d.setName(rs.getString("d.name"));
                d.setCode(rs.getString("d.code"));
                a.setDept(d);

                ProjectTypeSetting r = new ProjectTypeSetting();
                r.setId(rs.getInt("a.project_role"));
                r.setName(rs.getString("r.name"));
                a.setRole(r);

                Project p = new Project();
                p.setId(rs.getInt("a.project_id"));
                p.setName(rs.getString("p.name"));
                p.setCode(rs.getString("p.code"));
                a.setProject(p);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return a;
    }

//    Add new allocation
    public int insertAllocation(Allocation allocation) throws SQLException {
        int result = 0;
        String sql = """
                     INSERT INTO allocation (created_by, created_at, last_updated, start_date, 
                     end_date, effort_rate, description, dept_id, user_id, project_id, project_role)
                     VALUES (?, NOW(), NOW(), ?, ?, ?, ?, ?, ?, ?, ?)""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, allocation.getCreatedBy());
            stm.setDate(2, BaseDAO.MyDateUtil.getSQLDate(allocation.getStartDate()));
            stm.setDate(3, BaseDAO.MyDateUtil.getSQLDate(allocation.getEndDate()));
            stm.setDouble(4, allocation.getEffortRate());
            stm.setString(5, allocation.getDescription());
            stm.setInt(6, allocation.getDeptId());
            stm.setInt(7, allocation.getUserId());
            stm.setInt(8, allocation.getProjectId());
            stm.setInt(9, allocation.getProjectRole());
            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    Update an allocation
    public boolean updateAllocation(Allocation allocation) throws SQLException {
        boolean rowUpdated = false;

        String sql = """
                     UPDATE allocation SET updated_by = ?, last_updated = NOW(), 
                     start_date = ?, end_date = ?, effort_rate = ?, description = ?, 
                     status = ?, dept_id = ?, user_id = ?, project_id = ?, project_role = ?
                     WHERE id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, allocation.getUpdateBy());
            stm.setDate(2, BaseDAO.MyDateUtil.getSQLDate(allocation.getStartDate()));
            stm.setDate(3, BaseDAO.MyDateUtil.getSQLDate(allocation.getEndDate()));
            stm.setDouble(4, allocation.getEffortRate());
            stm.setString(5, allocation.getDescription());
            stm.setBoolean(6, allocation.isStatus());
            stm.setInt(7, allocation.getDeptId());
            stm.setInt(8, allocation.getUserId());
            stm.setInt(9, allocation.getProjectId());
            stm.setInt(10, allocation.getProjectRole());
            stm.setInt(11, allocation.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    Change status of an allocation
    public boolean changeStatusAllocation(Allocation allocation) throws SQLException {
        boolean rowUpdated = false;

        String activateSql = "UPDATE allocation SET updated_by = ?, last_updated = NOW(), end_date = NULL, status = 1 WHERE id = ?;";
        String deactivateSql = "UPDATE allocation SET updated_by = ?, last_updated = NOW(), end_date = CURDATE(), status = 0 WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection()) {
            PreparedStatement stm;
            if (!allocation.isStatus()) { // Check if status is false
                stm = cnt.prepareStatement(deactivateSql);
                stm.setInt(1, allocation.getUpdateBy());
            } else {  // Check if status is true
                stm = cnt.prepareStatement(activateSql);
                stm.setInt(1, allocation.getUpdateBy());
            }
            stm.setInt(2, allocation.getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

    public boolean isCodeExists(String code, int projectId) {
        String query = "SELECT 1 FROM project WHERE code = ? AND id != ? LIMIT 1"; // Loại trừ ID của dự án hiện tại
        try (Connection conn = BaseDAO.getConnection(); // Sử dụng phương thức getConnection từ BaseDAO
                 PreparedStatement stmt = conn.prepareStatement(query)) {

            // Gán giá trị vào câu truy vấn
            stmt.setString(1, code);
            stmt.setInt(2, projectId);

            // Thực thi truy vấn
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Nếu có kết quả trả về, mã đã tồn tại
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Ghi log lỗi để kiểm tra
        }
        return false; // Mặc định trả về false nếu có lỗi
    }

    public List<Setting> getAllBizTerms() {
        String query = "SELECT DISTINCT id, name FROM setting ";

        List<Setting> bizTerms = new ArrayList<>();

        try (Connection conn = BaseDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            // Duyệt qua từng bản ghi trong ResultSet
            while (rs.next()) {
                // Tạo đối tượng Setting
                Setting setting = new Setting();
                setting.setId(rs.getInt("id"));
                setting.setName(rs.getString("name"));

                // Thêm đối tượng vào danh sách
                bizTerms.add(setting);
            }

        } catch (SQLException e) {
            BaseDAO.printSQLException(e); // Ghi log lỗi chi tiết
        }

        return bizTerms; // Trả về danh sách Setting
    }

}
