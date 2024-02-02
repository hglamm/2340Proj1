package com.example.gt_scheduler.ui.gallery;

public class Exam {
    private String className;
    private String date;

    public Exam(String className, String date) {
        this.className = className;
        this.date = date;
    }

    // getters and setters for exam details
    public String getClassName() { return this.className; }
    public String getDate() { return this.date; }

    public void setClassName(String className) { this.className = className; }
    public void setDate(String date) { this.date = date; }
}
