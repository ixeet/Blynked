package com.example.admin.blynked;

/**
 * Created by Admin on 31-07-2015.
 */

import android.graphics.Bitmap;



/**
 * Created by Admin on 27-07-2015.
 */

/**
 * Created by Admin on 24-07-2015.
 */
public class Eventdata1 {

    private String name;
    private String number;
    private String location;
    private Bitmap picture;

    public Eventdata1(String phName, String phNumber) {
        this.name=phName;
        this.number=phNumber;
        this.location=location;

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
    public String toString() {


        // TODO Auto-generated method stub

        String c=this.name+this.number;
        return c;


    }

    public Bitmap getPicture() {
        return picture;
    }
}


