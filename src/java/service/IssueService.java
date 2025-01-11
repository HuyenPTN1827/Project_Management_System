/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.IssueDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Issue;
import model.Project;
import model.User;

/**
 *
 * @author HuyenPTNHE160769
 */
public class IssueService extends BaseServive {

    private final IssueDAO issueDAO;

    public IssueService() {
        this.issueDAO = new IssueDAO();
    }

    // Get assignees list
    public List<User> getMemberListByProjectId(Integer projectId) {
        return issueDAO.getMemberListByProjectId(projectId);
    }

    // Get issues list
    public List<Issue> getAllIssues(String keyword, Integer project, Integer type,
            Integer milestone, Integer assigner, Integer assignee, Integer status) {
        return issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
    }

    // Get 10 lastest issues
    public List<Issue> get10LastestIssues(int userId) {
        return issueDAO.select10LastestIssues(userId);
    }

    // Count issues
    public List<Issue> countIssues(Integer deptId, Integer bizTerm) {
        return issueDAO.countIssues(deptId, bizTerm);
    }

    // Get a issue information by id
    public Issue getIssueById(int id) {
        return issueDAO.selectIssueByID(id);
    }

    // Add new issue information
    public int insertIssue(Issue issue) throws SQLException {
        return issueDAO.insertIssue(issue);
    }

    // Update a issue information
    public boolean updateIssue(Issue issue) throws SQLException {
        return issueDAO.updateIssue(issue);
    }

    //The deadline cannot be before the project start date or after the project end date.
    public List<String> validateDeadline(Issue issue, Project project) {
        List<String> errors = new ArrayList<>();

        if (project == null) {
            errors.add("Project information is missing.");
            return errors; // Nếu project null, dừng kiểm tra
        }

        String startDate = project.getStartDate().toString();
        String endDate = project.getEndDate().toString();
        LocalDate deadline = issue.getDeadline();

        if (deadline == null) {
            errors.add("Deadline is missing.");
            return errors; // Nếu deadline null, dừng kiểm tra
        }

        // Kiểm tra nếu deadline trước ngày bắt đầu
        if (startDate != null && deadline.isBefore(LocalDate.parse(startDate))) {
            errors.add("Deadline cannot be earlier than the project start date (" + startDate + ").");
        }

        // Kiểm tra nếu deadline sau ngày kết thúc
        if (endDate != null && deadline.isAfter(LocalDate.parse(endDate))) {
            errors.add("Deadline cannot be later than the project end date (" + endDate + ").");
        }

        return errors;
    }

}
