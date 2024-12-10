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
//BachHD
public class Project {
   

    private int id;
    private String name;
    private String code;
    private int estimatedEffort;   
    private Date startDate;
    private String details;
    private Date endDate;
    private Date lastUpdated;
    private int status;
    private int typeId;
    private int departmentId;
    private int userId;
    private int bizTerm;
    
    // Các trường bổ sung có thể liên kết với bảng khác (nếu có)
    private String departmentName;
    private String typeName;
    private String typeCode;
    private String departmentCode;
    private String settingName;
    private int createdBy;
    private String bizTermName;
    
    private User user;
    
    public Project() {
    }

    public Project(int id, String name, String code, int estimatedEffort, Date startDate, String details, Date endDate, Date lastUpdated, int status, int typeId, int departmentId, int userId, int bizTerm, String departmentName, String typeName, String typeCode, String departmentCode, String settingName, int createdBy, String bizTermName, User user) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.estimatedEffort = estimatedEffort;
        this.startDate = startDate;
        this.details = details;
        this.endDate = endDate;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.typeId = typeId;
        this.departmentId = departmentId;
        this.userId = userId;
        this.bizTerm = bizTerm;
        this.departmentName = departmentName;
        this.typeName = typeName;
        this.typeCode = typeCode;
        this.departmentCode = departmentCode;
        this.settingName = settingName;
        this.createdBy = createdBy;
        this.bizTermName = bizTermName;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEstimatedEffort() {
        return estimatedEffort;
    }

    public void setEstimatedEffort(int estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getStatus() {
        return status;
    }
                                                         
    public void setStatus(int status) {
        this.status = status;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getBizTerm() {
        return bizTerm;
    }

    public void setBizTerm(int bizTerm) {
        this.bizTerm = bizTerm;
    }

    public String getBizTermName() {
        return bizTermName;
    }

    public void setBizTermName(String bizTermName) {
        this.bizTermName = bizTermName;
    }

}
