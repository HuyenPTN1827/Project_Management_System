/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.IssueDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Issue;
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
    
    //Deadline cannot be before today.
    public List<String> validateDeadline(Issue issue) {
        List<String> errors = new ArrayList<>();
        
        if (!validateStartDates(issue.getDeadline())) {
            errors.add("Deadline cannot be earlier than today.");
        }
        
        return errors;
    }
}
