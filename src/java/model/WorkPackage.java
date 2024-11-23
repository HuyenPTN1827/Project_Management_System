package model;

import java.sql.Timestamp;

public class WorkPackage {

    private int id;
    private int createdBy;
    private Timestamp lastUpdated;
    private String title;
    private String complexity;
    private int plannedEffort;
    private int status;
    private Integer actualEffort; // Optional, can be null
    private String details;
    private Integer projectId; // Optional, can be null
    private Integer userId; // Optional, can be null

    // Default constructor
    public WorkPackage() {
    }

    // Full constructor
    public WorkPackage(int id, int createdBy, Timestamp lastUpdated, String title, String complexity,
                       int plannedEffort, int status, Integer actualEffort, String details,
                       Integer projectId, Integer userId) {
        this.id = id;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.title = title;
        this.complexity = complexity;
        this.plannedEffort = plannedEffort;
        this.status = status;
        this.actualEffort = actualEffort;
        this.details = details;
        this.projectId = projectId;
        this.userId = userId;
    }

    // Getters and Setters

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

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public int getPlannedEffort() {
        return plannedEffort;
    }

    public void setPlannedEffort(int plannedEffort) {
        this.plannedEffort = plannedEffort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getActualEffort() {
        return actualEffort;
    }

    public void setActualEffort(Integer actualEffort) {
        this.actualEffort = actualEffort;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WorkPackage{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", lastUpdated=" + lastUpdated +
                ", title='" + title + '\'' +
                ", complexity='" + complexity + '\'' +
                ", plannedEffort=" + plannedEffort +
                ", status=" + status +
                ", actualEffort=" + actualEffort +
                ", details='" + details + '\'' +
                ", projectId=" + projectId +
                ", userId=" + userId +
                '}';
    }
}
