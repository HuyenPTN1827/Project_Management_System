/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * @author Admin
 */
public class Allocation {
    private int id;
    private int createdBy;
    private LocalDateTime createdAt;
    private int updateBy;
    private LocalDateTime lastUpdated;
    private LocalDate startDate;
    private LocalDate endDate;
    private double effortRate;
    private String description;
    private boolean status;
    private int deptId;
    private int userId;
    private int projectId;
    private int projectRole;
    private int total_projects;
    
    private Department dept;
    private User user;
    private User created_by;
    private User updated_by;
    private Project project;
    private ProjectTypeSetting role;

    public Allocation() {
    }

    public Allocation(int id, int createdBy, LocalDateTime createdAt, int updateBy, LocalDateTime lastUpdated, LocalDate startDate, LocalDate endDate, double effortRate, String description, boolean status, int deptId, int userId, int projectId, int projectRole, int total_projects, Department dept, User user, User created_by, User updated_by, Project project, ProjectTypeSetting role) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updateBy = updateBy;
        this.lastUpdated = lastUpdated;
        this.startDate = startDate;
        this.endDate = endDate;
        this.effortRate = effortRate;
        this.description = description;
        this.status = status;
        this.deptId = deptId;
        this.userId = userId;
        this.projectId = projectId;
        this.projectRole = projectRole;
        this.total_projects = total_projects;
        this.dept = dept;
        this.user = user;
        this.created_by = created_by;
        this.updated_by = updated_by;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public int getTotal_projects() {
        return total_projects;
    }

    public void setTotal_projects(int total_projects) {
        this.total_projects = total_projects;
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

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

    public User getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(User updated_by) {
        this.updated_by = updated_by;
    }

}
