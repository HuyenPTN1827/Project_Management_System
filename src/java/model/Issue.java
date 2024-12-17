/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author kelma
 */
public class Issue {

    private int id;
    private LocalDateTime last_updated;
    private String name;
    private String details;
    private LocalDate deadline;
    private int status;
    private int count;
    private int deptManager;

    private User created_by;
    private User assignee;
    private Milestone milestone;
    private WorkPackage scope;
    private Project project;
    private Setting type;

    public Issue() {
    }

    public Issue(int id, LocalDateTime last_updated, String name, String details, LocalDate deadline, int status, int count, int deptManager, User created_by, User assignee, Milestone milestone, WorkPackage scope, Project project, Setting type) {
        this.id = id;
        this.last_updated = last_updated;
        this.name = name;
        this.details = details;
        this.deadline = deadline;
        this.status = status;
        this.count = count;
        this.deptManager = deptManager;
        this.created_by = created_by;
        this.assignee = assignee;
        this.milestone = milestone;
        this.scope = scope;
        this.project = project;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(LocalDateTime last_updated) {
        this.last_updated = last_updated;
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(int deptManager) {
        this.deptManager = deptManager;
    }

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public WorkPackage getScope() {
        return scope;
    }

    public void setScope(WorkPackage scope) {
        this.scope = scope;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Setting getType() {
        return type;
    }

    public void setType(Setting type) {
        this.type = type;
    }

}
