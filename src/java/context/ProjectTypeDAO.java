/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import context.BaseDAO.MyDateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ProjectType;
import model.ProjectTypeSetting;
import model.ProjectType_User;
import model.User;

/**
 *
 * @author kelma
 */
public class ProjectTypeDAO {

//    HuyenPTNHE160769
//    05/10/2024        
//    Admin select all project types
    public List<ProjectType> selectAllProjectTypes(String keyword, Boolean status) {
        List<ProjectType> projectType = new ArrayList<>();

        String sql = "SELECT * FROM pms.project_type WHERE 1=1";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (LOWER(code) LIKE ? OR LOWER(name) LIKE ?)";
        }
        if (status != null) {
            sql += " AND status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            int index = 1;

            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";

                stm.setString(index++, "%" + keywordPattern + "%");
                stm.setString(index++, "%" + keywordPattern + "%");
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("id"));
                pt.setCode(rs.getString("code"));
                pt.setName(rs.getString("name"));
                pt.setDetails(rs.getString("details"));
                pt.setStatus(rs.getBoolean("status"));

                projectType.add(pt);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return projectType;
    }

//    HuyenPTNHE160769
//    05/10/2024      
//    Admin select project type by id
    public ProjectType selectProjectTypeByID(int id) {
        ProjectType pt = null;

        String sql = "SELECT * FROM pms.project_type WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                pt = new ProjectType();
                pt.setId(rs.getInt("id"));
                pt.setCode(rs.getString("code"));
                pt.setName(rs.getString("name"));
                pt.setDetails(rs.getString("details"));
                pt.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return pt;
    }

//    HuyenPTNHE160769
//    05/10/2024       
//    Admin add new project type
    public int insertProjectType(ProjectType projectType) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO pms.project_type (code, name, details) VALUES (?, ?, ?);";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, projectType.getCode());
            stm.setString(2, projectType.getName());
            stm.setString(3, projectType.getDetails());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    05/10/2024         
//    Admin update a project type
    public boolean updateProjectType(ProjectType projectType) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.project_type SET code = ?, name = ?, details = ?, status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, projectType.getCode());
            stm.setString(2, projectType.getName());
            stm.setString(3, projectType.getDetails());
            stm.setBoolean(4, projectType.isStatus());
            stm.setInt(5, projectType.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    05/10/2024      
//    Admin change status of a project type
    public boolean changeStatusProjectType(ProjectType projectType) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.project_type SET status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setBoolean(1, projectType.isStatus());
            stm.setInt(2, projectType.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    17/10/2024      
//    Admin select all project type users
    public List<ProjectType_User> selectAllProjectTypeUsers(String keyword, Integer roleId, Boolean status, int typeId) {
        List<ProjectType_User> ptUsers = new ArrayList<>();

        String sql = """
                     SELECT ut.id, ut.user_id, u.full_name, ut.role_id, pts.name, ut.type_id, 
                     ut.start_date, ut.end_date, ut.status FROM pms.user_type ut
                     INNER JOIN pms.user u ON ut.user_id = u.id
                     INNER JOIN pms.project_type pt ON ut.type_id = pt.id
                     INNER JOIN pms.project_type_setting pts ON ut.role_id = pts.id
                     WHERE ut.type_id = ?""";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND LOWER(u.full_name) LIKE ?";
        }
        if (roleId != null) {
            sql += " AND ut.role_id = ?";
        }
        if (status != null) {
            sql += " AND ut.status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, typeId);

            int index = 2;
            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";
                stm.setString(index++, "%" + keywordPattern + "%");
            }
            if (roleId != null) {
                stm.setInt(index++, roleId);
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProjectType_User ptu = new ProjectType_User();
                ptu.setId(rs.getInt("ut.id"));

                // Handle potential null values for dates
                Date startDate = rs.getDate("ut.start_date");
                if (startDate != null) {
                    ptu.setStart_date(MyDateUtil.getUtilDate((java.sql.Date) startDate));
                }

                Date endDate = rs.getDate("ut.end_date");
                if (endDate != null) {
                    ptu.setEnd_date(MyDateUtil.getUtilDate((java.sql.Date) endDate));
                }

                ptu.setStatus(rs.getBoolean("ut.status"));

                User u = new User();
                u.setId(rs.getInt("ut.user_id"));
                u.setFull_name(rs.getString("u.full_name"));
                ptu.setUser(u);

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("ut.type_id"));
                ptu.setPjType(pt);

                ProjectTypeSetting pts = new ProjectTypeSetting();
                pts.setId(rs.getInt("ut.role_id"));  // Set role_id as the ID
                pts.setName(rs.getString("pts.name"));  // Set name as a string
                ptu.setPtSetting(pts);

                ptUsers.add(ptu);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return ptUsers;
    }

//    HuyenPTNHE160769
//    18/10/2024      
//    Admin change status of a project type users
    public boolean changeStatusProjectTypeUser(ProjectType_User ptUser) throws SQLException {
        boolean rowUpdated = false;

        String activateSql = "UPDATE pms.user_type SET status = ?, end_date = NULL WHERE id = ? AND type_id = ?;";
        String deactivateSql = "UPDATE pms.user_type SET status = ?, end_date = CURDATE() WHERE id = ? AND type_id = ?;";

        try (Connection cnt = BaseDAO.getConnection()) {
            PreparedStatement stm;
            if (!ptUser.isStatus()) { // Check if status is false
                stm = cnt.prepareStatement(deactivateSql);
                stm.setBoolean(1, ptUser.isStatus());  // Change to inactive
            } else {  // Check if status is true
                stm = cnt.prepareStatement(activateSql);
                stm.setBoolean(1, ptUser.isStatus()); // Change to active
            }
            stm.setInt(2, ptUser.getId());
            stm.setInt(3, ptUser.getPjType().getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    22/10/2024       
//    Admin add new project type user
    public int insertProjectTypeUser(ProjectType_User ptUser) throws SQLException {
        int result = 0;
        String sql = """
                     INSERT INTO pms.user_type (user_id, type_id, start_date, status, role_id)
                     VALUES (?, ?, CURDATE(), 1, ?);""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, ptUser.getUser().getId());
            stm.setInt(2, ptUser.getPjType().getId());
            stm.setInt(3, ptUser.getPtSetting().getId());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    05/10/2024      
//    Admin select project type by id
    public ProjectType_User selectProjectTypeUserByID(int id) {
        ProjectType_User ptu = null;

        String sql = """
                     SELECT ut.id, ut.user_id, u.full_name, u.email, u.mobile, ut.role_id, pts.name, ut.type_id, 
                                          ut.start_date, ut.end_date, ut.status FROM pms.user_type ut
                                          INNER JOIN pms.user u ON ut.user_id = u.id
                                          INNER JOIN pms.project_type pt ON ut.type_id = pt.id
                                          INNER JOIN pms.project_type_setting pts ON ut.role_id = pts.id
                                          WHERE ut.id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ptu = new ProjectType_User();
                ptu.setId(rs.getInt("ut.id"));

                // Handle potential null values for dates
                Date startDate = rs.getDate("ut.start_date");
                if (startDate != null) {
                    ptu.setStart_date(MyDateUtil.getUtilDate((java.sql.Date) startDate));
                }

                Date endDate = rs.getDate("ut.end_date");
                if (endDate != null) {
                    ptu.setEnd_date(MyDateUtil.getUtilDate((java.sql.Date) endDate));
                }

                ptu.setStatus(rs.getBoolean("ut.status"));

                User u = new User();
                u.setId(rs.getInt("ut.user_id"));
                u.setFull_name(rs.getString("u.full_name"));
                u.setEmail(rs.getString("u.email"));
                u.setMobile(rs.getString("u.mobile"));
                ptu.setUser(u);

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("ut.type_id"));
                ptu.setPjType(pt);

                ProjectTypeSetting pts = new ProjectTypeSetting();
                pts.setId(rs.getInt("ut.role_id"));
                pts.setName(rs.getString("pts.name"));
                ptu.setPtSetting(pts);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return ptu;
    }

//    HuyenPTNHE160769
//    05/10/2024         
//    Admin update a project type user
    public boolean updateProjectTypeUser(ProjectType_User ptUser) throws SQLException {
        boolean rowUpdated = false;

        String activateSql = "UPDATE pms.user_type SET status = ?, role_id = ?, end_date = NULL WHERE id = ? AND type_id = ?;";
        String deactivateSql = "UPDATE pms.user_type SET status = ?, role_id = ?, end_date = CURDATE() WHERE id = ? AND type_id = ?;";

        try (Connection cnt = BaseDAO.getConnection()) {
            PreparedStatement stm;
            if (!ptUser.isStatus()) { // Check if status is false
                stm = cnt.prepareStatement(deactivateSql);
                stm.setBoolean(1, ptUser.isStatus());  // Change to inactive
            } else {  // Check if status is true
                stm = cnt.prepareStatement(activateSql);
                stm.setBoolean(1, ptUser.isStatus()); // Change to active
            }
            stm.setInt(2, ptUser.getPtSetting().getId());
            stm.setInt(3, ptUser.getId());
            stm.setInt(4, ptUser.getPjType().getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

}
