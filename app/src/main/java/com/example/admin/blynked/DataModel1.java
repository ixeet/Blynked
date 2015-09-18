package com.example.admin.blynked;

import android.graphics.Bitmap;

/**
 * Created by Admin on 01-07-2015.
 */

public class DataModel1 {
    private String name;
    private Bitmap photo;
    public boolean selected;

    private String OtherData;
    public DataModel1(String name) {
        this.name = name;


    }
    public DataModel1(String name, String otherData ) {
        this.name = name;

        this.OtherData = otherData;




    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String toString() {


        // TODO Auto-generated method stub

String c=this.name+this.OtherData;
        return c;


    }


    public String getOtherData() {
        return this.OtherData;
    }
    public Bitmap getphoto() {
        return this.photo;
    }

    public void setOtherData(String otherData) {
        OtherData = otherData;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}