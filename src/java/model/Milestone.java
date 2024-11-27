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
public class Milestone {

    private int id;
    private int createdBy;          // người tạo milestone
    private String lastUpdated;        // thời gian cập nhật cuối cùng
    private String name;               // tên milestone
    private Integer parentMilestone;    // milestone cha nếu có
    private int priority;           // mức độ ưu tiên
    private Date targetDate;           // ngày dự kiến hoàn thành
    private int status;             // trạng thái hiện tại
    private Date actualDate;           // ngày hoàn thành thực tế
    private String details;            // chi tiết của milestone
    private int projectId;             // ID của dự án liên quan

    private String parentMilestoneName;
    private String projectName;
    private String createdbyuserName;
    public Milestone() {
    }

    public Milestone(int id, int createdBy, String lastUpdated, String name, Integer parentMilestone, int priority, Date targetDate, int status, Date actualDate, String details, int projectId) {
        this.id = id;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.name = name;
        this.parentMilestone = parentMilestone;
        this.priority = priority;
        this.targetDate = targetDate;
        this.status = status;
        this.actualDate = actualDate;
        this.details = details;
        this.projectId = projectId;
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

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentMilestone() {
        return parentMilestone;
    }

    public void setParentMilestone(Integer parentMilestone) {
        this.parentMilestone = parentMilestone;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getParentMilestoneName() {
        return parentMilestoneName;
    }

    public void setParentMilestoneName(String parentMilestoneName) {
        this.parentMilestoneName = parentMilestoneName;
    }

    public String getCreatedbyuserName() {
        return createdbyuserName;
    }

    public void setCreatedbyuserName(String createdbyuserName) {
        this.createdbyuserName = createdbyuserName;
    }
   
}
