/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class Allocation {
    private int id;
    private int createdBy;
    private Date createdAt;
    private Date lastUpdated;
    private Date updateBy;
    private Date startDate;
    private Date endDate;
    private double effortRate;
    private String description;
    private boolean status;
    private int deptId;
    private int userId;
    private int projectId;
    private int projectRole;
    
    private Department dept;
    private User user;
    private Project project;
    private ProjectTypeSetting role;

    public Allocation() {
    }

    public Allocation(int id, int createdBy, Date createdAt, Date lastUpdated, Date updateBy, Date startDate, Date endDate, double effortRate, String description, boolean status, int deptId, int userId, int projectId, int projectRole, Department dept, User user, Project project, ProjectTypeSetting role) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.updateBy = updateBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.effortRate = effortRate;
        this.description = description;
        this.status = status;
        this.deptId = deptId;
        this.userId = userId;
        this.projectId = projectId;
        this.projectRole = projectRole;
        this.dept = dept;
        this.user = user;
        this.project = project;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Date updateBy) {
        this.updateBy = updateBy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getEffortRate() {
        return effortRate;
    }

    public void setEffortRate(double effortRate) {
        this.effortRate = effortRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(int projectRole) {
        this.projectRole = projectRole;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectTypeSetting getRole() {
        return role;
    }

    public void setRole(ProjectTypeSetting role) {
        this.role = role;
    }
    
}
