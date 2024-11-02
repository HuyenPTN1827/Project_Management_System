/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kelma
 */
public class ProjectPhase {

    private int id;
    private String name;
    private int priority;
    private String details;
    private boolean final_phase;
    private int completion_rate;
    private boolean status;

    private ProjectType pjType;

    public ProjectPhase() {
    }

    public ProjectPhase(int id, String name, int priority, String details, boolean final_phase, int completion_rate, boolean status, ProjectType pjType) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.details = details;
        this.final_phase = final_phase;
        this.completion_rate = completion_rate;
        this.status = status;
        this.pjType = pjType;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isFinal_phase() {
        return final_phase;
    }

    public void setFinal_phase(boolean final_phase) {
        this.final_phase = final_phase;
    }

    public int getCompletion_rate() {
        return completion_rate;
    }

    public void setCompletion_rate(int completion_rate) {
        this.completion_rate = completion_rate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ProjectType getPjType() {
        return pjType;
    }

    public void setPjType(ProjectType pjType) {
        this.pjType = pjType;
    }
    
}
