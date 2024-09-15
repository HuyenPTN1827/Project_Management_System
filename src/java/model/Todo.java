/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author kelma
 */
public class Todo {

    private Long id;
    private String title;
    private String description;
    private LocalDate targetDate;
    private boolean status;
    private ArrayList<User> user = new ArrayList<>();

    public Todo() {
    }

    public Todo(Long id, String title, String description, LocalDate targetDate, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }

//    public Todo(String title, String description, LocalDate targetDate, boolean status) {
//        this.title = title;
//        this.description = description;
//        this.targetDate = targetDate;
//        this.status = status;
//    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (int) (id ^ (id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Todo other = (Todo) obj;
        return Objects.equals(this.id, other.id);
    }

}
