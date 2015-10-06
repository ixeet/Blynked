package com.example.admin.blynked;

/**
 * Created by Admin on 01-07-2015.
 */
public class DataModel {

    private String name;

    private int imgId;
    public DataModel(String name) {
        this.name = name;


    }
    public DataModel(String name, int imgId) {
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
 public int getOtherData1() {
        return imgId;
    }

}
