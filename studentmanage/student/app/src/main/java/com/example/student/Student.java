package com.example.student;

import android.graphics.Bitmap;
import java.io.Serializable;

public class Student implements Serializable {
    private byte[] imageId;
    private String id;
    private String name;
    private String sex;
    private String stuclass;
    private String check;
    private int score;
    public Bitmap bp;

    public void setStudent(byte[] ii, String i, String n, String s, String sc) {
        imageId = ii;
        id = i;
        name = n;
        sex = s;
        stuclass = sc;
    }
    public void setStudent(byte[] ii, String i, String n, String s, String sc,String c,int sco) {
        imageId = ii;
        id = i;
        name = n;
        sex = s;
        stuclass = sc;
        check=c;
        score=sco;
    }
    public void setImageId(byte[] ii) {
        imageId = ii;
    }
    public void setId(String i) {
        id = i;
    }
    public void setName(String n) {
        name = n;
    }
    public void setSex(String s) {
        sex = s;
    }
    public void setStuclass(String sc) {
        stuclass = sc;
    }
    public void setScore(int s) {
        score= s;
    }
    public void setCheck(String c) {
        check= c;
    }
    public byte[] getImageId() {
        return imageId;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }
    public String getStuclass() {
        return stuclass;
    }
    public String getCheck(){
        return check;
    }
    public int getScore(){
        return score;
    }

    @Override
    public String toString() {
        return imageId+"   "+id+"   "+name+"   "+sex+"   "+stuclass+"   "+check+"   ";
    }
}
