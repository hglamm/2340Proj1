package com.example.gt_scheduler.ui.todolist;

public class ToDoList {

    private String title;
    private String dueDate;
    private String categoryName;
    private String description;

    public ToDoList(String title, String dueDate, String categoryName, String description) {
        this.title = title;
        this.dueDate = dueDate;
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getter methods for accessing assignment details
    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public String getDescription() { return description; }

    // Setter methods for updating assignment details
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public void setDescription(String description) { this.description = description; }
}

//package com.example.gt_scheduler.ui.todolist;
//
//public class ToDoList {
//
//    private String title;
//    private String dueDate;
//    private String categoryName;
//
//    public ToDoList(String title, String dueDate, String categoryName) {
//        this.title = title;
//        this.dueDate = dueDate;
//        this.categoryName = categoryName;
//    }
//
//    // Getter methods for accessing assignment details
//    public String getTitle() {
//        return title;
//    }
//
//    public String getDueDate() {
//        return dueDate;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setDueDate(String dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//}
