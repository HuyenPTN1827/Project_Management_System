/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kelma
 */
public class ProjectTypeCriteria {

    private int id;
    private String name;
    private float weight;
    private String description;
    private boolean status;

    private ProjectType pjType;
    private ProjectPhase pjPhase;

    public ProjectTypeCriteria() {
    }

    public ProjectTypeCriteria(int id, String name, float weight, String description, boolean status, ProjectType pjType, ProjectPhase pjPhase) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.status = status;
        this.pjType = pjType;
        this.pjPhase = pjPhase;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
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

    public ProjectType getPjType() {
        return pjType;
    }

    public void setPjType(ProjectType pjType) {
        this.pjType = pjType;
    }

    public ProjectPhase getPjPhase() {
        return pjPhase;
    }

    public void setPjPhase(ProjectPhase pjPhase) {
        this.pjPhase = pjPhase;
    }
    
}
