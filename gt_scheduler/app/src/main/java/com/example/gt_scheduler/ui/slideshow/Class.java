package com.example.gt_scheduler.ui.slideshow;

public class Class {
    private String className;
    private String instructor;
    private String days;
    private String time;
    private String location;

    public Class(String className, String instructor, String days, String time, String location) {
        this.className = className;
        this.instructor = instructor;
        this.days = days;
        this.time = time;
        this.location = location;
    }

    // getters and setters
    public String getClassName() {return this.className;}
    public String getInstructor() {return this.instructor;}
    public String getDays() {return this.days;}
    public String getTime() {return this.time;}
    public String getLocation() {return this.location;}

    public void setClassName(String className) { this.className = className; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setDays(String days) {this.days = days;}
    public void setTime(String time) { this.time = time; }
    public void setLocation(String location) {this.location = location;}
}
