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
import model.Project;

/**
 *
 * @author Admin
 */
public class ProjectDAO {

    //BachHD
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();

        String sql = "SELECT p.*, pt.code AS type_code, d.code AS department_code, s.name AS setting_name "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "LEFT JOIN setting s ON p.biz_term = s.id;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setBizTerm(rs.getInt("biz_term"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setStatus(rs.getBoolean("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));

                // Lấy mã loại dự án và mã bộ phận
                project.setTypeCode(rs.getString("type_code")); // Cần thêm setter cho typeCode trong Project
                project.setDepartmentCode(rs.getString("department_code")); // Cần thêm setter cho departmentCode trong Project
                project.setSettingName(rs.getString("setting_name"));
                projects.add(project);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return projects;
    }

    public List<Project> getProjectsByCode(String projectCode) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.*, pt.code AS type_code, d.code AS department_code, s.name AS setting_name "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "LEFT JOIN setting s ON p.biz_term = s.id "
                + "WHERE p.code LIKE ?";  // Thêm điều kiện WHERE cho mã dự án, có thể dùng LIKE để hỗ trợ tìm kiếm gần đúng

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setString(1, "%" + projectCode + "%"); // Truyền giá trị mã dự án với ký tự đại diện để tìm kiếm gần đúng
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setBizTerm(rs.getInt("biz_term"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setStatus(rs.getBoolean("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));

                // Lấy mã loại dự án, mã bộ phận, và tên setting
                project.setTypeCode(rs.getString("type_code"));
                project.setDepartmentCode(rs.getString("department_code"));
                project.setSettingName(rs.getString("setting_name"));

                projects.add(project); // Thêm dự án vào danh sách
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return projects; // Trả về danh sách dự án tìm thấy
    }

    // Tìm kiếm dự án theo trạng thái
    public List<Project> getProjectsByStatus(boolean status) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.*, pt.code AS type_code, d.code AS department_code, s.name AS setting_name "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "LEFT JOIN setting s ON p.biz_term = s.id "
                + "WHERE p.status = ?"; // Điều kiện tìm kiếm theo trạng thái

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setBoolean(1, status); // Thiết lập trạng thái tìm kiếm
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setBizTerm(rs.getInt("biz_term"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setStatus(rs.getBoolean("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));

                // Lấy mã loại dự án, mã bộ phận, và tên setting
                project.setTypeCode(rs.getString("type_code"));
                project.setDepartmentCode(rs.getString("department_code"));
                project.setSettingName(rs.getString("setting_name"));

                projects.add(project); // Thêm dự án vào danh sách
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return projects; // Trả về danh sách dự án tìm thấy
    }

    public List<Project> searchProjectsByKeyword(String keyword) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.*, pt.code AS type_code, d.code AS department_code, s.name AS setting_name "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "LEFT JOIN setting s ON p.biz_term = s.id "
                + "WHERE p.name LIKE ? OR p.code LIKE ?"; // Tìm kiếm theo tên hoặc mã dự án

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%"; // Thêm ký tự đại diện để tìm kiếm gần đúng
            stm.setString(1, searchKeyword);
            stm.setString(2, searchKeyword);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setBizTerm(rs.getInt("biz_term"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setStatus(rs.getBoolean("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));
                project.setTypeCode(rs.getString("type_code"));
                project.setDepartmentCode(rs.getString("department_code"));
                project.setSettingName(rs.getString("setting_name"));

                projects.add(project); // Thêm dự án vào danh sách
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return projects; // Trả về danh sách dự án tìm thấy
    }

    public Project getProjectById(int id) {
        Project project = null; // Khởi tạo project

        // Câu lệnh SQL để lấy thông tin dự án cùng với các thông tin từ bảng liên quan
        String sql = "SELECT p.*, pt.code AS type_code, d.code AS department_code, s.name AS setting_name "
                + "FROM project p "
                + "LEFT JOIN project_type pt ON p.type_id = pt.id "
                + "LEFT JOIN department d ON p.department_id = d.id "
                + "LEFT JOIN setting s ON p.biz_term = s.id "
                + "WHERE p.id = ?"; // Điều kiện WHERE để lấy dự án theo ID

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, id); // Thiết lập ID dự án để tìm kiếm
            ResultSet rs = stm.executeQuery();

            if (rs.next()) { // Kiểm tra nếu có kết quả
                project = new Project(); // Khởi tạo đối tượng Project
                project.setId(rs.getInt("id"));
                project.setBizTerm(rs.getInt("biz_term"));
                project.setCode(rs.getString("code"));
                project.setName(rs.getString("name"));
                project.setDetails(rs.getString("details"));
                project.setStartDate(rs.getDate("start_date"));
                project.setStatus(rs.getBoolean("status"));
                project.setTypeId(rs.getInt("type_id"));
                project.setDepartmentId(rs.getInt("department_id"));

                // Lấy mã loại dự án, mã bộ phận và tên setting
                project.setTypeCode(rs.getString("type_code")); // Lấy mã loại dự án
                project.setDepartmentCode(rs.getString("department_code")); // Lấy mã bộ phận
                project.setSettingName(rs.getString("setting_name")); // Lấy tên setting
            }
        } catch (SQLException e) {
            // Ghi lại lỗi SQL
            BaseDAO.printSQLException(e);
        }

        return project; // Trả về đối tượng Project tìm thấy, hoặc null nếu không tìm thấy
    }

    public boolean updateProject(Project project) {
        String sql = "UPDATE project SET biz_term = ?, code = ?, name = ?, details = ?, start_date = ?, "
                + "status = ?, type_id = ?, department_id = ? WHERE id = ?"; // Câu lệnh SQL để cập nhật dự án

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            // In giá trị của các trường để debug
            System.out.println("Updating project with ID: " + project.getId());
            System.out.println("BizTerm: " + project.getBizTerm());
            System.out.println("Code: " + project.getCode());
            System.out.println("Name: " + project.getName());
            System.out.println("Details: " + project.getDetails());
            System.out.println("Start Date: " + project.getStartDate());
            System.out.println("Status: " + project.isStatus());
            System.out.println("Type ID: " + project.getTypeId());
            System.out.println("Department ID: " + project.getDepartmentId());

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            stm.setInt(1, project.getBizTerm());
            stm.setString(2, project.getCode());
            stm.setString(3, project.getName());
            stm.setString(4, project.getDetails());
            stm.setDate(5, new java.sql.Date(project.getStartDate().getTime()));
            stm.setBoolean(6, project.isStatus());
            stm.setInt(7, project.getTypeId());
            stm.setInt(8, project.getDepartmentId());
            stm.setInt(9, project.getId()); // ID của dự án cần cập nhật

            int rowsAffected = stm.executeUpdate(); // Thực hiện câu lệnh cập nhật

            return rowsAffected > 0; // Trả về true nếu có dòng nào bị ảnh hưởng
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return false; // Trả về false nếu có lỗi xảy ra
    }

    public boolean insertProject(Project project) {
        String sql = "INSERT INTO project (biz_term, code, name, details, start_date, status, type_id, department_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {

            // Thiết lập các tham số từ đối tượng project
            setProjectParameters(stm, project);

            // Thực thi câu lệnh và kiểm tra kết quả
            int affectedRows = stm.executeUpdate();
            return affectedRows > 0; // Trả về true nếu có dòng nào bị ảnh hưởng

        } catch (SQLException e) {
            // Ghi log chi tiết lỗi
            BaseDAO.printSQLException(e);
        }

        return false; // Trả về false nếu có lỗi xảy ra
    }

    private void setProjectParameters(PreparedStatement stm, Project project) throws SQLException {
        // Thiết lập các tham số cho PreparedStatement
        stm.setInt(1, project.getBizTerm()); // Biz term ID

        // Thiết lập giá trị cho các tham số còn lại
        stm.setString(2, project.getCode());
        stm.setString(3, project.getName());
        stm.setString(4, project.getDetails());

        // Chuyển đổi java.util.Date sang java.sql.Date, nếu null thì set null
        if (project.getStartDate() != null) {
            stm.setDate(5, new java.sql.Date(project.getStartDate().getTime()));
        } else {
            stm.setNull(5, java.sql.Types.DATE);
        }

        // Trạng thái dự án (boolean)
        stm.setBoolean(6, project.isStatus());

        // Type ID và Department ID (các giá trị kiểu int)
        stm.setInt(7, project.getTypeId());
        stm.setInt(8, project.getDepartmentId());
    }

    public static void main(String[] args) {
        ProjectDAO projectDAO = new ProjectDAO(); // Giả sử bạn có một lớp DAO tên là ProjectDAO
        int testProjectId = 1; // Thay thế bằng ID dự án mà bạn muốn kiểm tra

        // Gọi phương thức getProjectById
        Project project = projectDAO.getProjectById(testProjectId);

        // Kiểm tra kết quả
        if (project != null) {
            System.out.println("Project ID: " + project.getId());
            System.out.println("Project Code: " + project.getCode());
            System.out.println("Project Name: " + project.getName());
            System.out.println("Project Details: " + project.getDetails());
            System.out.println("Start Date: " + project.getStartDate());
            System.out.println("Status: " + project.isStatus());
            System.out.println("Type Code: " + project.getTypeCode());
            System.out.println("Department Code: " + project.getDepartmentCode());
            System.out.println("Setting Name: " + project.getSettingName());
        } else {
            System.out.println("No project found with ID: " + testProjectId);
        }
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

    public Project getProjectsName(int id ) {
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
}
