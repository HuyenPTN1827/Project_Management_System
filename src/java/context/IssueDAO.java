/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Issue;
import model.Milestone;
import model.Project;
import model.Setting;
import model.User;
import model.WorkPackage;

/**
 *
 * @author HuyenPTNHE160769
 */
public class IssueDAO {

    // Get assignee list
    public List<User> getAssigneeListByProjectId(int userId, Integer projectId) {
        List<User> user = new ArrayList<>();
        String sql = """
                     SELECT DISTINCT u.id, u.username, u.full_name
                     FROM pms.issue i
                     JOIN pms.allocation a ON i.project_id = a.project_id
                     JOIN pms.user u ON i.assignee = u.id
                     WHERE a.user_id = ?
                     AND (u.status = 1 OR u.status = 0)""";
        if (projectId != null) {
            sql += " AND i.project_id = ?";
        }
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            stm.setInt(1, userId);
            if (projectId != null) {
                stm.setInt(2, projectId);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User assignee = new User();
                assignee.setId(rs.getInt("u.id"));
                assignee.setFull_name(rs.getString("u.full_name"));
                assignee.setUsername(rs.getString("u.username"));
                user.add(assignee);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return user;
    }

    // Get issues list
    public List<Issue> selectAllIssues(int userId, String keyword, Integer project, Integer type,
            Integer milestone, Integer scope, Integer assignee, Integer status) {
        List<Issue> issue = new ArrayList<>();

        String sql = """
                     SELECT i.id, i.created_by, i.milestone_id, m.name, i.work_package, 
                     wp.title, i.assignee, u2.username, i.deadline, i.status, i.name, 
                     i.type, s.name, i.project_id, p.code, i.details
                     FROM pms.issue i 
                     JOIN pms.project p ON i.project_id = p.id
                     JOIN pms.milestone m ON i.milestone_id = m.id
                     JOIN pms.work_package wp ON i.work_package = wp.id
                     JOIN pms.user u1 ON i.created_by = u1.id 
                     JOIN pms.user u2 ON i.assignee = u2.id
                     JOIN pms.setting s ON i.type = s.id
                     JOIN pms.allocation a ON i.project_id = a.project_id AND i.created_by = a.user_id
                     WHERE i.created_by = ?""";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND LOWER(i.name) LIKE ?";
        }
        if (project != null) {
            sql += " AND i.project_id = ?";
        }
        if (type != null) {
            sql += " AND i.type = ?";
        }
        if (milestone != null) {
            sql += " AND i.milestone_id = ?";
        }
        if (scope != null) {
            sql += " AND i.work_package = ?";
        }
        if (assignee != null) {
            sql += " AND i.assignee = ?";
        }
        if (status != null) {
            sql += " AND i.status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, userId);
            int index = 2;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";
                stm.setString(index++, keywordPattern);
            }
            if (project != null) {
                stm.setInt(index++, project);
            }
            if (type != null) {
                stm.setInt(index++, type);
            }
            if (milestone != null) {
                stm.setInt(index++, milestone);
            }
            if (scope != null) {
                stm.setInt(index++, scope);
            }
            if (assignee != null) {
                stm.setInt(index++, assignee);
            }
            if (status != null) {
                stm.setInt(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Issue i = new Issue();
                i.setId(rs.getInt("i.id"));
                i.setName(rs.getString("i.name"));
                i.setDetails(rs.getString("i.details"));
                i.setStatus(rs.getInt("i.status"));
                // Handle potential null values for dates
                Date deadline = rs.getDate("i.deadline");
                if (deadline != null) {
                    i.setDeadline(BaseDAO.MyDateUtil.getUtilDate((java.sql.Date) deadline));
                }

                Setting s = new Setting();
                s.setId(rs.getInt("i.type"));
                s.setName(rs.getString("s.name"));
                i.setType(s);

                Project p = new Project();
                p.setId(rs.getInt("i.project_id"));
                p.setCode(rs.getString("p.code"));
                i.setProject(p);

                Milestone m = new Milestone();
                m.setId(rs.getInt("i.milestone_id"));
                m.setName(rs.getString("m.name"));
                i.setMilestone(m);

                WorkPackage wp = new WorkPackage();
                wp.setId(rs.getInt("i.work_package"));
                wp.setTitle(rs.getString("wp.title"));
                i.setScope(wp);

                User u1 = new User();
                u1.setId(rs.getInt("i.assignee"));
                u1.setUsername(rs.getString("u2.username"));
                i.setAssignee(u1);

                User u2 = new User();
                u2.setId(rs.getInt("i.created_by"));
                i.setCreated_by(u2);

                issue.add(i);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return issue;
    }

//    HuyenPTNHE160769
//    04/10/2024       
//    Admin select dept by id
//    public Department selectDepartmentByID(int id) {
//        Department d = null;
//
//        String sql = "SELECT * FROM pms.department WHERE id = ?;";
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
//            stm.setInt(1, id);
//
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                d = new Department();
//                d.setId(rs.getInt("id"));
//                d.setCode(rs.getString("code"));
//                d.setName(rs.getString("name"));
//                d.setDetails(rs.getString("details"));
//                d.setParentId(rs.getInt("parent"));
//                d.setStatus(rs.getBoolean("status"));
//            }
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return d;
//    }
//
////    HuyenPTNHE160769
////    04/10/2024       
////    Admin add new dept
//    public int insertDepartment(Department dept, Integer parent) throws SQLException {
//        int result = 0;
//        String sql = "INSERT INTO pms.department (code, name, details, parent) "
//                + "VALUES (?, ?, ?, ?);";
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
//            stm.setString(1, dept.getCode());
//            stm.setString(2, dept.getName());
//            stm.setString(3, dept.getDetails());
//            // Set parent (allow null)
//            if (parent != null) {
//                stm.setInt(4, parent);
//            } else {
//                stm.setNull(4, Types.INTEGER);
//            }
//
//            result = stm.executeUpdate();
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return result;
//    }
//
////    HuyenPTNHE160769
////    04/10/2024         
////    Admin update a dept
//    public boolean updateDepartment(Department dept, Integer parent) throws SQLException {
//        boolean rowUpdated = false;
//
//        String sql = "UPDATE pms.department SET code = ?, name = ?, details = ?, "
//                + "parent = ?, status = ? WHERE id = ?;";
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
//            stm.setString(1, dept.getCode());
//            stm.setString(2, dept.getName());
//            stm.setString(3, dept.getDetails());
//            // Set parent (allow null)
//            if (parent != null) {
//                stm.setInt(4, parent);
//            } else {
//                stm.setNull(4, Types.INTEGER);
//            }
//            stm.setBoolean(5, dept.isStatus());
//            stm.setInt(6, dept.getId());
//
//            rowUpdated = stm.executeUpdate() > 0;
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return rowUpdated;
//    }
//
////    HuyenPTNHE160769
////    04/10/2024      
////    Admin change status of a dept
//    public boolean changeStatusDepartment(Department dept) throws SQLException {
//        boolean rowUpdated = false;
//
//        String sql = "UPDATE pms.department SET status = ? WHERE id = ?;";
//
//        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
//            stm.setBoolean(1, dept.isStatus());
//            stm.setInt(2, dept.getId());
//
//            rowUpdated = stm.executeUpdate() > 0;
//        } catch (SQLException e) {
//            BaseDAO.printSQLException(e);
//        }
//        return rowUpdated;
//    }
}
