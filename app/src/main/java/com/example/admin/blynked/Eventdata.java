package com.example.admin.blynked;

/**
 * Created by Admin on 27-07-2015.
 */

import android.graphics.Bitmap;

/**
 * Created by Admin on 24-07-2015.
 */
public class Eventdata {

    private String name;
    private String number;
    private String location;
    private Bitmap picture;
    String time;

    public Eventdata(String phName, String phNumber,String time, String location) {
        this.name=phName;
        this.number=phNumber;
        this.location=location;
        this.time=time;
       // this.picture=photo;
    }
    public void setName(String nam) {
        this.name=nam;
    }
    public String getName() {
        return name;
    }
    public void setlocation(String nam) {
        this.location=nam;
    }
    public String getlocation() {
        return location;
    }
    public void setNumber(String num) {
        this.number=num;
    }
    public String getNumber() {
        return number;
    }
    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Bitmap getPicture() {
        return picture;
    }
    public void setTime(String num) {
        this.time=num;
    }
    public String getTime() {
        return time;
    }
}

