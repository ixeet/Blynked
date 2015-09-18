package com.example.admin.blynked;

/**
 * Created by Admin on 10-08-2015.
 */
public class DataModelsetting {
    private String title;
    public DataModelsetting(String t) {
        this.title=t;


    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String s) {
        this.title=s;
    }
}
