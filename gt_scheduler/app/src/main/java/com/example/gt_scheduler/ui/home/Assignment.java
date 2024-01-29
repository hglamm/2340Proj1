package com.example.gt_scheduler.ui.home;

public class Assignment {
    private String title;
    private String dueDate;
    private String className;

    public Assignment(String title, String dueDate, String className) {
        this.title = title;
        this.dueDate = dueDate;
        this.className = className;
    }

    // Getter methods for accessing assignment details
    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getClassName() {
        return className;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
