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
    private int bizTerm;
    private String code;
    private String name;
    private String details;
    private Date startDate;
    private boolean status;
    private int typeId;
    private int departmentId;
    
    
    private String typeCode;
    private String departmentCode;
    private String settingName;

    public Project() {
    }

    public Project(int id, int bizTerm, String code, String name, String details, Date startDate, boolean status, int typeId, int departmentId, String typeCode, String departmentCode, String settingName) {
        this.id = id;
        this.bizTerm = bizTerm;
        this.code = code;
        this.name = name;
        this.details = details;
        this.startDate = startDate;
        this.status = status;
        this.typeId = typeId;
        this.departmentId = departmentId;
        this.typeCode = typeCode;
        this.departmentCode = departmentCode;
        this.settingName = settingName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBizTerm() {
        return bizTerm;
    }

    public void setBizTerm(int bizTerm) {
        this.bizTerm = bizTerm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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

    
    
    
}
