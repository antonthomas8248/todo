package com.studyspring.todocrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoDto {

    private int id;
    @NotBlank(message = "Title is required")
    @Size(min= 2, max=50,message ="Title must between 2 and 50 characters")
    private String title;
    @NotBlank(message = "Description is required")
    @Size(min= 2, max=50,message ="Description  must between 2 and 50 characters")
    private String description;
    @NotBlank(message = "Date is required")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

