/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kelma
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String full_name;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String notes;
    private int status;
    private int role_id;
    
    private Department dept;
    private Setting setting;
    private ArrayList<Department> depts = new ArrayList<>();
    private ArrayList<Setting> settings = new ArrayList<>();

    public User() {
    }

    //fix
    public User(int id, String full_name, String email, String mobile, String password, String notes, int status, int role_id , Department dept, Setting setting) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.notes = notes;
        this.status = status;
        this.role_id = role_id;
        this.dept = dept;
        this.setting = setting;
    }

    public User(int id, String full_name, String username, String email, String mobile, String password, String notes, int status, int role_id, Department dept, Setting setting) {
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.notes = notes;
        this.status = status;
        this.role_id = role_id;
        this.dept = dept;
        this.setting = setting;
    }

     public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public ArrayList<Department> getDepts() {
        return depts;
    }

    public void setDepts(ArrayList<Department> depts) {
        this.depts = depts;
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public void setSettings(ArrayList<Setting> settings) {
        this.settings = settings;
    }

}
