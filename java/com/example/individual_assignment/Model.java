package com.example.individual_assignment;

public class Model {

    String title;
    String reporter;
    String desc;
    String date;

    //then create an constructor that will be called by MainActivity.java

    public Model(String title, String reporter, String desc, String date) {
        this.title = title;
        this.reporter = reporter;
        this.desc = desc;
        this.date = date;
    }

    //then create getter and setter methods


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

