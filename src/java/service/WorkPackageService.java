package service;

import context.WorkPackageDAO;
import java.sql.SQLException;
import java.util.List;
import model.WorkPackage;

public class WorkPackageService {

    private final WorkPackageDAO workPackageDAO = new WorkPackageDAO();

    public WorkPackage getOne(int id) throws SQLException {
        return workPackageDAO.getOne(id);
    }

    public List<WorkPackage> getList(String title, String status) throws SQLException {
        return workPackageDAO.getList(title, status);
    }

    public void createWorkPackage(WorkPackage workPackage) throws SQLException {
        workPackageDAO.create(workPackage);
    }

    public void updateWorkPackage(WorkPackage workPackage) throws SQLException {
        workPackageDAO.update(workPackage);
    }

    public void changeStatus(int id, int newStatus) throws SQLException {
        workPackageDAO.changeStatus(id, newStatus);
    }
    
    //HuyenPTNHE160769
    public List<WorkPackage> getWorkPackageByProjectId(int userId, Integer projectId) {
        return workPackageDAO.getWorkPackageByProjectId(userId, projectId);
    }
}
