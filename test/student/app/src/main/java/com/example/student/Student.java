package com.example.student;

public class Student {
    private int imageId;
    private String id;
    private String name;
    private String sex;
    private String stuclass;

    public void setStudent(int ii, String i, String n, String s, String sc) {
        imageId = ii;
        id = i;
        name = n;
        sex = s;
        stuclass = sc;
    }

    public void setImageId(int ii) {
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

    public int getImageId() {
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

    @Override
    public String toString() {
        return imageId+"   "+id+"   "+name+"   "+sex+"   "+stuclass+"   ";
    }
}
