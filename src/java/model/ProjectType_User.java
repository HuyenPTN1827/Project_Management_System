package model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author kelma
 */
public class ProjectType_User {

    private int id;
    private LocalDate start_date;
    private LocalDate end_date;
    private boolean status;

    private User user;
    private ProjectType pjType;
    private ProjectTypeSetting ptSetting;
    private Setting setting;

    public ProjectType_User() {
    }

    public ProjectType_User(int id, LocalDate start_date, LocalDate end_date, boolean status, User user, ProjectType pjType, ProjectTypeSetting ptSetting, Setting setting) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.user = user;
        this.pjType = pjType;
        this.ptSetting = ptSetting;
        this.setting = setting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProjectType getPjType() {
        return pjType;
    }

    public void setPjType(ProjectType pjType) {
        this.pjType = pjType;
    }

    public ProjectTypeSetting getPtSetting() {
        return ptSetting;
    }

    public void setPtSetting(ProjectTypeSetting ptSetting) {
        this.ptSetting = ptSetting;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

}
