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
    private String status;
    private int deptId;
    private int userId;
    private int projectId;
    private int projectRole;

    public Allocation() {
    }

    public Allocation(int id, int createdBy, Date createdAt, Date lastUpdated, Date updateBy, Date startDate, Date endDate, double effortRate, String description, String status, int deptId, int userId, int projectId, int projectRole) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    
}
