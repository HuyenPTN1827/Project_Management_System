/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.IssueDAO;
import java.util.List;
import model.Issue;
import model.User;

/**
 *
 * @author kelma
 */
public class IssueService {
    private final IssueDAO issueDAO;
    
    public IssueService() {
        this.issueDAO = new IssueDAO();
    }
    
    public List<User> getAssigneeListByProjectId(int userId, Integer projectId) {
        return issueDAO.getAssigneeListByProjectId(userId, projectId);
    }
    
    // HuyenPTNHE160769
    // 22/10/2024
    // Get issues list
    public List<Issue> getAllIssues(int userId, String keyword, Integer project, Integer type, 
            Integer milestone, Integer scope, Integer assignee, Integer status) {
        return issueDAO.selectAllIssues(userId, keyword, project, type, milestone, scope, assignee, status);
    }
}
