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
    public List<User> getMemberListByProjectId(Integer projectId) {
        List<User> user = new ArrayList<>();
//        String sql = """
//                     SELECT DISTINCT u.id, u.username, u.full_name
//                     FROM issue i
//                     JOIN allocation a ON i.project_id = a.project_id
//                     JOIN user u ON i.assignee = u.id
//                     WHERE a.user_id = ?
//                     AND (u.status = 1 OR u.status = 0)""";
        String sql = """
                     SELECT DISTINCT u.id, u.username, u.full_name
                     FROM allocation a
                     JOIN user u ON a.user_id = u.id
                     WHERE (u.status = 1 OR u.status = 0)""";
        if (projectId != null) {
            sql += " AND a.project_id = ?";
        }
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql)) {
            if (projectId != null) {
                stm.setInt(1, projectId);
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
    public List<Issue> selectAllIssues(String keyword, Integer project, Integer type,
            Integer milestone, Integer assigner, Integer assignee, Integer status) {
        List<Issue> issue = new ArrayList<>();

        String sql = """
                     SELECT DISTINCT i.id, i.created_by, u1.username, i.milestone_id, m.name, 
                     i.assignee, u2.username, i.deadline, i.status, i.name, 
                     i.type, s.name, i.project_id, p.code, i.details, p.user_id, du.user_id
                     FROM issue i 
                     JOIN project p ON i.project_id = p.id
                     LEFT JOIN dept_user du ON p.department_id = du.dept_id AND du.role_id = 3
                     JOIN milestone m ON i.milestone_id = m.id
                     JOIN user u1 ON i.created_by = u1.id 
                     JOIN user u2 ON i.assignee = u2.id
                     JOIN setting s ON i.type = s.id
                     JOIN allocation a ON i.project_id = a.project_id
                     WHERE 1=1""";

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
//        if (scope != null) {
//            sql += " AND i.work_package = ?";
//        }
        if (assigner != null) {
            sql += " AND i.created_by = ?";
        }
        if (assignee != null) {
            sql += " AND i.assignee = ?";
        }
        if (status != null) {
            sql += " AND i.status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;

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
//            if (scope != null) {
//                stm.setInt(index++, scope);
//            }
            if (assigner != null) {
                stm.setInt(index++, assigner);
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
                i.setDeptManager(rs.getInt("du.user_id"));
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
                p.setUserId(rs.getInt("p.user_id"));
                i.setProject(p);

                Milestone m = new Milestone();
                m.setId(rs.getInt("i.milestone_id"));
                m.setName(rs.getString("m.name"));
                i.setMilestone(m);
//
//                WorkPackage wp = new WorkPackage();
//                wp.setId(rs.getInt("i.work_package"));
//                wp.setTitle(rs.getString("wp.title"));
//                i.setScope(wp);

                User u1 = new User();
                u1.setId(rs.getInt("i.created_by"));
                u1.setUsername(rs.getString("u1.username"));
                i.setCreated_by(u1);

                User u2 = new User();
                u2.setId(rs.getInt("i.assignee"));
                u2.setUsername(rs.getString("u2.username"));
                i.setAssignee(u2);

                issue.add(i);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return issue;
    }

    // Get 10 lastest issues
    public List<Issue> select10LastestIssues(int userId) {
        List<Issue> issue = new ArrayList<>();

        String sql = """
                     SELECT DISTINCT i.id, i.created_by, u1.username, u1.full_name, i.milestone_id, m.name, 
                     i.assignee, u2.username, u2.full_name, i.deadline, i.status, i.name, 
                     i.type, s.name, i.project_id, p.code, p.name, i.details
                     FROM issue i 
                     JOIN project p ON i.project_id = p.id
                     JOIN milestone m ON i.milestone_id = m.id
                     JOIN user u1 ON i.created_by = u1.id 
                     JOIN user u2 ON i.assignee = u2.id
                     JOIN setting s ON i.type = s.id
                     JOIN allocation a ON i.project_id = a.project_id
                     WHERE i.created_by = ? OR i.assignee = ?
                     ORDER BY i.id DESC LIMIT 10 OFFSET 0;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, userId);
            stm.setInt(2, userId);
            
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
                p.setName(rs.getString("p.name"));
                i.setProject(p);

                Milestone m = new Milestone();
                m.setId(rs.getInt("i.milestone_id"));
                m.setName(rs.getString("m.name"));
                i.setMilestone(m);

                User u1 = new User();
                u1.setId(rs.getInt("i.created_by"));
                u1.setUsername(rs.getString("u1.username"));
                u1.setFull_name(rs.getString("u1.full_name"));
                i.setCreated_by(u1);

                User u2 = new User();
                u2.setId(rs.getInt("i.assignee"));
                u2.setUsername(rs.getString("u2.username"));
                u2.setFull_name(rs.getString("u2.full_name"));
                i.setAssignee(u2);

                issue.add(i);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return issue;
    }

//    Select issue by id
    public Issue selectIssueByID(int id) {
        Issue i = null;

        String sql = """
                     SELECT i.id, i.created_by, u1.username, i.last_updated, i.milestone_id, m.name, 
                     i.assignee, u2.username, i.deadline, i.status, i.name, i.type, s.name, 
                     i.project_id, p.name, p.code, i.details, u1.full_name, u2.full_name
                     FROM issue i 
                     JOIN project p ON i.project_id = p.id
                     JOIN milestone m ON i.milestone_id = m.id
                     JOIN user u1 ON i.created_by = u1.id 
                     JOIN user u2 ON i.assignee = u2.id
                     JOIN setting s ON i.type = s.id
                     JOIN allocation a ON i.project_id = a.project_id
                     WHERE i.id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                i = new Issue();
                i.setId(rs.getInt("i.id"));
                i.setName(rs.getString("i.name"));
                i.setDetails(rs.getString("i.details"));
                i.setStatus(rs.getInt("i.status"));
                // Handle potential null values for dates
                Date deadline = rs.getDate("i.deadline");
                if (deadline != null) {
                    i.setDeadline(BaseDAO.MyDateUtil.getUtilDate((java.sql.Date) deadline));
                }
                i.setLast_updated(rs.getTimestamp("i.last_updated").toLocalDateTime());

                Setting s = new Setting();
                s.setId(rs.getInt("i.type"));
                s.setName(rs.getString("s.name"));
                i.setType(s);

                Project p = new Project();
                p.setId(rs.getInt("i.project_id"));
                p.setCode(rs.getString("p.code"));
                p.setName(rs.getString("p.name"));
                i.setProject(p);

                Milestone m = new Milestone();
                m.setId(rs.getInt("i.milestone_id"));
                m.setName(rs.getString("m.name"));
                i.setMilestone(m);

                User u1 = new User();
                u1.setId(rs.getInt("i.created_by"));
                u1.setUsername(rs.getString("u1.username"));
                u1.setFull_name(rs.getString("u1.full_name"));
                i.setCreated_by(u1);

                User u2 = new User();
                u2.setId(rs.getInt("i.assignee"));
                u2.setUsername(rs.getString("u2.username"));
                u2.setFull_name(rs.getString("u2.full_name"));
                i.setAssignee(u2);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return i;
    }

//    Add new issue
    public int insertIssue(Issue issue) throws SQLException {
        int result = 0;
        String sql = """
                     INSERT INTO issue (created_by, last_updated, milestone_id, 
                     assignee, deadline, status, name, type, project_id, details)
                     VALUES (?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?);""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, issue.getCreated_by().getId());
            stm.setInt(2, issue.getMilestone().getId());
            stm.setInt(3, issue.getAssignee().getId());
            stm.setDate(4, BaseDAO.MyDateUtil.getSQLDate(issue.getDeadline()));
            stm.setInt(5, issue.getStatus());
            stm.setString(6, issue.getName());
            stm.setInt(7, issue.getType().getId());
            stm.setInt(8, issue.getProject().getId());
            stm.setString(9, issue.getDetails());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    Update a issue
    public boolean updateIssue(Issue issue) throws SQLException {
        boolean rowUpdated = false;

        String sql = """
                     UPDATE issue SET last_updated = NOW(), milestone_id = ?, assignee = ?, 
                     deadline = ?, status = ?, name =?, type = ?, project_id = ?, details = ?
                     WHERE id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, issue.getMilestone().getId());
            stm.setInt(2, issue.getAssignee().getId());
            stm.setDate(3, BaseDAO.MyDateUtil.getSQLDate(issue.getDeadline()));
            stm.setInt(4, issue.getStatus());
            stm.setString(5, issue.getName());
            stm.setInt(6, issue.getType().getId());
            stm.setInt(7, issue.getProject().getId());
            stm.setString(8, issue.getDetails());
            stm.setInt(9, issue.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

    // Count issues
    public List<Issue> countIssues(Integer deptId, Integer bizTerm) {
        List<Issue> issue = new ArrayList<>();

        String sql = """
                     SELECT i.status, COUNT(*) AS count 
                     FROM issue i 
                     JOIN project p ON i.project_id = p.id
                     LEFT JOIN department d ON p.department_id = d.id
                     WHERE 1=1 """;

        if (deptId != null) {
            sql += " AND p.department_id = ?";
        }
        if (bizTerm != null) {
            sql += " AND p.biz_term = ?";
        }
        
        sql += " GROUP BY i.status ORDER BY i.status ASC;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;
            if (deptId != null) {
                stm.setInt(index++, deptId);
            }
            if (bizTerm != null) {
                stm.setInt(index++, bizTerm);
            }
           
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Issue i = new Issue();
                i.setStatus(rs.getInt("i.status"));
                i.setCount(rs.getInt("count"));
                issue.add(i);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return issue;
    }
}
