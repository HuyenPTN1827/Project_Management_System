package context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.WorkPackage;

public class WorkPackageDAO {

    // Fetch a single work package by ID
    public WorkPackage getOne(int id) throws SQLException {
        String query = "SELECT * FROM work_package WHERE id = ?";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToWorkPackage(rs);
            }
        }
        return null;
    }

    // Fetch a list of work packages with optional filters (title and status)
    public List<WorkPackage> getList(String title, Integer status) throws SQLException {
        String query = "SELECT * FROM work_package WHERE 1=1";
        List<Object> params = new ArrayList<>();
        if (title != null && !title.isEmpty()) {
            query += " AND title LIKE ?";
            params.add("%" + title + "%");
        }
        if (status != null) {
            query += " AND status = ?";
            params.add(status);
        }
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            List<WorkPackage> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapResultSetToWorkPackage(rs));
            }
            return list;
        }
    }

    // Insert a new work package
    public void create(WorkPackage workPackage) throws SQLException {
        String query = "INSERT INTO work_package (created_by, last_updated, title, complexity, planned_effort, status, actual_effort, details, project_id, user_id) "
                + "VALUES (?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setWorkPackageParameters(ps, workPackage);
            ps.executeUpdate();
        }
    }

    // Update an existing work package
    public void update(WorkPackage workPackage) throws SQLException {
        String query = "UPDATE work_package SET last_updated = NOW(), title = ?, complexity = ?, planned_effort = ?, status = ?, actual_effort = ?, details = ?, project_id = ?, user_id = ? WHERE id = ?";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            setWorkPackageParameters(ps, workPackage);
            ps.setInt(10, workPackage.getId());
            ps.executeUpdate();
        }
    }

    // Change the status of a work package
    public void changeStatus(int id, int newStatus) throws SQLException {
        String query = "UPDATE work_package SET status = ? WHERE id = ?";
        try (Connection conn = BaseDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, newStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private WorkPackage mapResultSetToWorkPackage(ResultSet rs) throws SQLException {
        return new WorkPackage(
            rs.getInt("id"),
            rs.getInt("created_by"),
            rs.getTimestamp("last_updated"),
            rs.getString("title"),
            rs.getString("complexity"),
            rs.getInt("planned_effort"),
            rs.getInt("status"),
            rs.getInt("actual_effort"),
            rs.getString("details"),
            rs.getInt("project_id"),
            rs.getInt("user_id")
        );
    }

    private void setWorkPackageParameters(PreparedStatement ps, WorkPackage workPackage) throws SQLException {
        ps.setInt(1, workPackage.getCreatedBy());
        ps.setString(2, workPackage.getTitle());
        ps.setString(3, workPackage.getComplexity());
        ps.setInt(4, workPackage.getPlannedEffort());
        ps.setInt(5, workPackage.getStatus());
        ps.setObject(6, workPackage.getActualEffort(), Types.INTEGER);
        ps.setString(7, workPackage.getDetails());
        ps.setObject(8, workPackage.getProjectId(), Types.INTEGER);
        ps.setObject(9, workPackage.getUserId(), Types.INTEGER);
    }
}
