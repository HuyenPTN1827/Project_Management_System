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
import model.ProjectPhase;
import model.ProjectType;
import model.ProjectTypeCriteria;
import model.ProjectTypeSetting;
import model.ProjectType_User;
import model.Setting;
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

                stm.setString(index++, keywordPattern);
                stm.setString(index++, keywordPattern);
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
        String sql = "INSERT INTO pms.project_type (code, name, details, status) VALUES (?, ?, ?, ?);";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, projectType.getCode());
            stm.setString(2, projectType.getName());
            stm.setString(3, projectType.getDetails());
            stm.setBoolean(4, projectType.isStatus());

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

    // HuyenPTNHE160769
    // 29/09/2024
    // Get roles list
    public List<ProjectTypeSetting> getProjectRolesList(int projectId) {
        List<ProjectTypeSetting> pjSetting = new ArrayList<>();

        String sql = """
                     SELECT pts.id, pts.name, pts.value FROM pms.project_type_setting pts 
                     JOIN pms.project_type pt ON pts.type_id = pt.id
                     JOIN pms.project p ON pt.id = p.type_id
                     WHERE pts.type = 'Project Role' 
                     AND pts.status = 1 
                     AND p.id = ?
                     ORDER BY pts.priority ASC;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, projectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProjectTypeSetting pts = new ProjectTypeSetting();
                pts.setId(rs.getInt("id"));
                pts.setName(rs.getString("name"));
                pts.setValue(rs.getString("value"));
                pjSetting.add(pts);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return pjSetting;
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
                     INNER JOIN pms.setting pts ON ut.role_id = pts.id
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
                stm.setString(index++, keywordPattern);
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

                Setting s = new Setting();
                s.setId(rs.getInt("ut.role_id"));
                s.setName(rs.getString("pts.name"));
                ptu.setSetting(s);

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
            stm.setInt(3, ptUser.getSetting().getId());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    05/10/2024      
//    Admin select project type user by id
    public ProjectType_User selectProjectTypeUserByID(int id) {
        ProjectType_User ptu = null;

        String sql = """
                     SELECT ut.id, ut.user_id, u.full_name, u.email, u.mobile, ut.role_id, pts.name, ut.type_id, 
                                          ut.start_date, ut.end_date, ut.status FROM pms.user_type ut
                                          INNER JOIN pms.user u ON ut.user_id = u.id
                                          INNER JOIN pms.project_type pt ON ut.type_id = pt.id
                                          INNER JOIN pms.setting pts ON ut.role_id = pts.id
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

                Setting pts = new Setting();
                pts.setId(rs.getInt("ut.role_id"));
                pts.setName(rs.getString("pts.name"));
                ptu.setSetting(pts);
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
            stm.setInt(2, ptUser.getSetting().getId());
            stm.setInt(3, ptUser.getId());
            stm.setInt(4, ptUser.getPjType().getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    29/10/2024      
//    Admin select all project type criteria
    public List<ProjectTypeCriteria> selectAllProjectTypeCriteria(String keyword, Integer phaseId, Boolean status, int typeId) {
        List<ProjectTypeCriteria> ptCriteria = new ArrayList<>();

        String sql = """
                     SELECT ec.id, ec.name, ec.weight, ec.status, ec.phase_id, pp.name, pp.type_id
                     FROM pms.eval_criteria ec 
                     INNER JOIN pms.project_phase pp ON ec.phase_id = pp.id
                     WHERE pp.type_id = ?""";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND LOWER(ec.name) LIKE ?";
        }
        if (phaseId != null) {
            sql += " AND ec.phase_id = ?";
        }
        if (status != null) {
            sql += " AND ec.status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, typeId);

            int index = 2;
            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";
                stm.setString(index++, keywordPattern);
            }
            if (phaseId != null) {
                stm.setInt(index++, phaseId);
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProjectTypeCriteria ptc = new ProjectTypeCriteria();
                ptc.setId(rs.getInt("ec.id"));
                ptc.setName(rs.getString("ec.name"));
                ptc.setWeight(rs.getFloat("ec.weight"));
                ptc.setStatus(rs.getBoolean("ec.status"));

                ProjectPhase pp = new ProjectPhase();
                pp.setId(rs.getInt("ec.phase_id"));
                pp.setName(rs.getString("pp.name"));
                ptc.setPjPhase(pp);

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("pp.type_id"));
                ptc.setPjType(pt);

                ptCriteria.add(ptc);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }

        return ptCriteria;
    }

//    HuyenPTNHE160769
//    29/10/2024      
//    Admin change status of a project type criteria
    public boolean changeStatusProjectTypeCriteria(ProjectTypeCriteria ptc) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.eval_criteria SET status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setBoolean(1, ptc.isStatus());
            stm.setInt(2, ptc.getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

//    HuyenPTNHE160769
//    30/10/2024      
//    Admin select project type criteria by id
    public ProjectTypeCriteria selectProjectTypeCriteriaByID(int id) {
        ProjectTypeCriteria ptc = null;

        String sql = """
                     SELECT ec.id, ec.name, ec.weight, ec.description, ec.status, 
                     ec.phase_id, pp.name, pp.type_id
                     FROM pms.eval_criteria ec 
                     INNER JOIN pms.project_phase pp ON ec.phase_id = pp.id
                     WHERE ec.id = ?;""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ptc = new ProjectTypeCriteria();
                ptc.setId(rs.getInt("ec.id"));
                ptc.setName(rs.getString("ec.name"));
                ptc.setWeight(rs.getFloat("ec.weight"));
                ptc.setDescription(rs.getString("ec.description"));
                ptc.setStatus(rs.getBoolean("ec.status"));

                ProjectPhase pp = new ProjectPhase();
                pp.setId(rs.getInt("ec.phase_id"));
                pp.setName(rs.getString("pp.name"));
                ptc.setPjPhase(pp);

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("pp.type_id"));
                ptc.setPjType(pt);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return ptc;
    }

//    HuyenPTNHE160769
//    30/10/2024       
//    Admin add new project type criteria
    public int insertProjectTypeCriteria(ProjectTypeCriteria ptc) throws SQLException {
        int result = 0;
        String sql = """
                     INSERT INTO pms.eval_criteria (name, weight, description, phase_id)
                     VALUES (?, ?, ?, ?);""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, ptc.getName());
            stm.setFloat(2, ptc.getWeight());
            stm.setString(3, ptc.getDescription());
            stm.setInt(4, ptc.getPjPhase().getId());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

//    HuyenPTNHE160769
//    30/10/2024         
//    Admin update a project type criteria
    public boolean updateProjectTypeCriteria(ProjectTypeCriteria ptc) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.eval_criteria SET name = ?, weight =?, description = ?, status = ?, phase_id = ? WHERE id =?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, ptc.getName());
            stm.setFloat(2, ptc.getWeight());
            stm.setString(3, ptc.getDescription());
            stm.setBoolean(4, ptc.isStatus());
            stm.setInt(5, ptc.getPjPhase().getId());
            stm.setInt(6, ptc.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

    // HuyenPTNHE160769
    // 29/10/2024
    // Get project phases list
    public List<ProjectPhase> selectAllProjectPhase(int typeId, String keyword, Boolean status) {
        List<ProjectPhase> phase = new ArrayList<>();

        String sql = "SELECT * FROM pms.project_phase WHERE type_id = ?";

        // Add search conditions if any
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND LOWER(name) LIKE ?";
        }
        if (status != null) {
            sql += " AND status = ?";
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, typeId);

            int index = 2;
            if (keyword != null && !keyword.isEmpty()) {
                String keywordPattern = "%" + keyword.toLowerCase().trim() + "%";
                stm.setString(index++, keywordPattern);
            }
            if (status != null) {
                stm.setBoolean(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProjectPhase p = new ProjectPhase();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPriority(rs.getInt("priority"));
                p.setStatus(rs.getBoolean("status"));

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("type_id"));
                p.setPjType(pt);

                phase.add(p);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return phase;
    }

// chiennkhe161554
// 30/10/2024
// Admin select project phase by id
    public ProjectPhase selectProjectPhaseByID(int id) {
        ProjectPhase phase = null;

        String sql = "SELECT * FROM pms.project_phase WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                phase = new ProjectPhase();
                phase.setId(rs.getInt("id"));
                phase.setName(rs.getString("name"));
                phase.setPriority(rs.getInt("priority"));
                phase.setDetails(rs.getString("details"));
                phase.setStatus(rs.getBoolean("status"));

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("type_id"));
                phase.setPjType(pt);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return phase;
    }

// chiennkhe161554
// 30/10/2024
// Admin add new project phase
    public int insertProjectPhase(ProjectPhase phase) throws SQLException {
        int result = 0;
        String sql = """
                 INSERT INTO pms.project_phase (name, priority, details, type_id, status)
                 VALUES (?, ?, ?, ?, ?);""";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, phase.getName());
            stm.setInt(2, phase.getPriority());
            stm.setString(3, phase.getDetails());
            stm.setInt(4, phase.getPjType().getId());
            stm.setBoolean(5, phase.isStatus());

            result = stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return result;
    }

// chiennkhe161554
// 30/10/2024
// Admin update a project phase
    public boolean updateProjectPhase(ProjectPhase phase) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.project_phase SET name = ?, priority = ?, details = ?, status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, phase.getName());
            stm.setInt(2, phase.getPriority());
            stm.setString(3, phase.getDetails());
            stm.setBoolean(4, phase.isStatus());
            stm.setInt(5, phase.getId());

            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

// chiennkhe161554
// 30/10/2024
// Admin change status of a project phase
    public boolean changeStatusProjectPhase(ProjectPhase phase) throws SQLException {
        boolean rowUpdated = false;

        String sql = "UPDATE pms.project_phase SET status = ? WHERE id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setBoolean(1, phase.isStatus());
            stm.setInt(2, phase.getId());
            rowUpdated = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return rowUpdated;
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Get list of ProjectTypeSetting with search by name or value and filter by status
    public List<ProjectTypeSetting> getAllProjectTypeSettings(String keyword, Boolean status, String type, int typeId) throws SQLException {
        List<ProjectTypeSetting> list = new ArrayList<>();

        String sql = "SELECT * FROM pms.project_type_setting WHERE type_id = ?";

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += (" AND (name LIKE ? OR value LIKE ?)");
        }
        if ("parent".equals(type)) {
            sql += " AND (type IS NULL OR type = '')";
        } else if (type != null && !type.isEmpty()) {
            sql += " AND type like ?";
        }
        if (status != null) {
            sql += (" AND status = ?");
        }

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setInt(1, typeId);
            int paramIndex = 2;

            // Set parameters for keyword search
            if (keyword != null && !keyword.trim().isEmpty()) {
                String searchPattern = "%" + keyword.toLowerCase().trim() + "%";
                stmt.setString(paramIndex++, searchPattern);
                stmt.setString(paramIndex++, searchPattern);
            }
            // Set type only if it is not "parent" and not empty
            if (type != null && !type.isEmpty() && !"parent".equals(type)) {
                stmt.setString(paramIndex++, "%" + type + "%");
            }
            // Set parameter for status filter
            if (status != null) {
                stmt.setBoolean(paramIndex++, status);
            }
            System.out.println(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProjectTypeSetting s = new ProjectTypeSetting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setType(rs.getString("type"));
                s.setValue(rs.getString("value"));
                s.setPriority(rs.getInt("priority"));
                s.setStatus(rs.getBoolean("status"));
                s.setDescription(rs.getString("description"));

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("type_id"));
                s.setPjType(pt);

                list.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return list;
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Get ProjectTypeSetting by ID
    public ProjectTypeSetting getProjectTypeSettingById(int id) throws SQLException {
        ProjectTypeSetting setting = null;

        String sql = "SELECT * FROM pms.project_type_setting WHERE id = ?";
        System.out.println(sql);

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                setting = new ProjectTypeSetting();
                setting.setId(rs.getInt("id"));
                setting.setName(rs.getString("name"));
                setting.setType(rs.getString("type"));
                setting.setValue(rs.getString("value"));
                setting.setPriority(rs.getInt("priority"));
                setting.setStatus(rs.getBoolean("status"));
                setting.setDescription(rs.getString("description"));

                ProjectType pt = new ProjectType();
                pt.setId(rs.getInt("type_id"));
                setting.setPjType(pt);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return setting;
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Create new ProjectTypeSetting
    public void createProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        String sql = "INSERT INTO pms.project_type_setting (name, type, value, "
                + "priority, description, type_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(sql);

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setString(1, setting.getName());
            stm.setString(2, setting.getType());
            stm.setString(3, setting.getValue());
            stm.setInt(4, setting.getPriority());
            stm.setString(5, setting.getDescription());
            stm.setInt(6, setting.getPjType().getId());
            stm.setBoolean(7, setting.isStatus());
            stm.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Update existing ProjectTypeSetting
    public void updateProjectTypeSetting(ProjectTypeSetting setting) throws SQLException {
        String sql = "UPDATE pms.project_type_setting SET name = ?, type = ?, value = ?, "
                + "priority = ?, status = ?, description = ? WHERE id = ?;";
        System.out.println(sql);

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setString(1, setting.getName());
            stmt.setString(2, setting.getType());
            stmt.setString(3, setting.getValue());
            stmt.setInt(4, setting.getPriority());
            stmt.setBoolean(5, setting.isStatus());
            stmt.setString(6, setting.getDescription());
            stmt.setInt(7, setting.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
    }

    // TrươngHBHE151011
    // 17/10/2024
    // Change the status of ProjectTypeSetting by ID
    public void changeStatusById(int id, boolean newStatus) throws SQLException {
        String sql = "UPDATE pms.project_type_setting SET status = ? WHERE id = ?";
        System.out.println(sql);
        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stmt = cnt.prepareStatement(sql);) {
            stmt.setBoolean(1, newStatus);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
    }

    // HuyenPTNHE160769
    // 11/11/2024
    // Get type list
    public List<ProjectTypeSetting> getTypeList(int id) {
        List<ProjectTypeSetting> setting = new ArrayList<>();

        String sql = "SELECT * FROM pms.project_type_setting WHERE (type IS NULL OR type = '') AND type_id = ?;";

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(sql);) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProjectTypeSetting s = new ProjectTypeSetting();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setValue(rs.getString("value"));
                setting.add(s);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return setting;
    }

}
