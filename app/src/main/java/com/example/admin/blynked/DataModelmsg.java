package com.example.admin.blynked;

/**
 * Created by Admin on 07-08-2015.
 */


/**
 * Created by Admin on 01-07-2015.
 */
public class DataModelmsg {

    private String name;

    private int imgId;
    public DataModelmsg(String name) {
        this.name = name;


    }
    public DataModelmsg(String name, int imgId) {
        this.name = name;

        this.imgId=imgId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }




    public int getOtherData() {
        return imgId;
    }


}

