package com.example.admin.blynked;

import android.app.Application;
import android.content.pm.PackageInfo;

/**
 * Created by Admin on 22-07-2015.
 */
public class Globals extends Application {
    private int data=200;
    private int flag=1;
    private int c=0;
    private int cal=1;
    private int hid=1;
    private int timer=0;
    PackageInfo packageInfo;
    int favflag=0;
    int sstatus=0;
    int check=1;
    int b=1;
    int power=1;
    int exp=1;
    int yes=0;
    int s=0;
    int auto=0;
    int fb=0;


    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public int getData(){
        return this.data;
    }
    public int getFlag(){
        return this.flag;
    }
    public int gethid(){
        return this.hid;
    }
    public void sethid(int d){
        this.hid=d;
    }
    public int getc(){
        return this.c;
    }
    public int getcal(){
        return this.cal;
    }
    public void setcal(int d){
        this.cal=d;
    }
    public void setc(int d){
        this.c=d;
    }
    public void setData(int d){
        this.data=d;
    }
    public void setFlag(int d){
        this.flag=d;
    }
    public int gettimer(){
        return this.timer;
    }
    public void settimer(int d){
        this.timer=d;
    }
    public int getfavflag(){
        return this.favflag;
    }
    public void setfavflag(int d){
        this.favflag=d;
    }
    public int getsstatus(){
        return this.sstatus;
    }
    public void setsstatus(int d){
        this.sstatus=d;
    }
    public int getcheck(){
        return this.check;
    }
    public void setcheck(int d){
        this.check=d;
    }
    public int getb(){
        return this.b;
    }
    public void setb(int d){
        this.b=d;
    }
    public int getexp(){
        return this.exp;
    }
    public void setexp(int d){
        this.exp=d;
    }
    public int getpower(){
        return this.power;
    }
    public void setpower(int d){
        this.power=d;
    }
    public int getyes(){
        return this.yes;
    }
    public void setyes(int d){
        this.yes=d;
    }
    public int gets(){
        return this.s;
    }
    public void sets(int d){
        this.s=d;
    }
    public int getauto(){
        return this.auto;
    }
    public void setauto(int d){
        this.auto=d;
    }
    public int getfb(){
        return this.fb;
    }
    public void setfb(int d){
        this.fb=d;
    }
}