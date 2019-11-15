package com.example.student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDao {
    private DatabaseAccess databaseAccess;
    private Cursor cursor;

    public DatabaseDao(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }
    //增加
    public long addStudent(Student s)
    {
        SQLiteDatabase studentDataBase=databaseAccess.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("imageId",s.getImageId());
        values.put("id",s.getId());
        values.put("name",s.getName());
        values.put("sex",s.getSex());
        values.put("stuclass",s.getStuclass());
        return databaseAccess.getWritableDatabase().insert("student", null, values);
    }
    public int findStudentById(String id) {
        int count=0;
        Cursor cursor = databaseAccess.getWritableDatabase().rawQuery("select count(id) from  student where id=" + id,null);
        while (cursor.moveToNext()) {
            count=cursor.getInt(0);
        }
        return count;
    }

    public String deleteStudentById(String id) {
        String sql="delete from student where id="+id;
        databaseAccess.getReadableDatabase().execSQL(sql);
        return id;
    }
    public int updateStudent(Student s) {
        ContentValues values = new ContentValues();
        values.put("imageId",s.getImageId());
        values.put("id",s.getId());
        values.put("name",s.getName());
        values.put("sex",s.getSex());
        values.put("stuclass",s.getStuclass());
        values.put("score",0);
        return databaseAccess.getWritableDatabase().update("student", values,
                "id=?", new String[] { s.getId() + "" });
    }
    public String insertCheck(String check,String id){
        String sql="";
        sql="update student set checked='"+check+"' where id='"+id+"'";
        databaseAccess.getReadableDatabase().execSQL(sql);
        return id;
    }
    public String deleteCheck(String id) {
        String sql="";
        sql="update student set checked=null,score=0 where id='"+id+"'";
        databaseAccess.getReadableDatabase().execSQL(sql);
        return id;
    }
    public int insertScore(String id,int i) {
        int score=0;
        Cursor cursor = databaseAccess.getWritableDatabase().rawQuery("select score from  student where id=" + id,null);
        while (cursor.moveToNext()) {
            score=cursor.getInt(0);
        }
        score=score+i;
        String sql="";
        sql="update student set score='"+score+"' where id='"+id+"'";
        databaseAccess.getReadableDatabase().execSQL(sql);
        return score;
    }
    public List<Student> getAllStudents() {
        List<Student> data = new ArrayList<Student>();
        Cursor cursor = databaseAccess.getWritableDatabase().query("student", null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            byte[] imageId=cursor.getBlob(cursor.getColumnIndex("imageId"));
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String stuclass = cursor.getString(cursor.getColumnIndex("stuclass"));
            String check=cursor.getString(cursor.getColumnIndex("checked"));
            int score=cursor.getInt(cursor.getColumnIndex("score"));
            Student st=new Student();
            st.setStudent(imageId,id,name,sex,stuclass,check,score);
            MainActivity.studentList.add(st);
            ArrayList<String> arrayList=new ArrayList<>();
            Student s=new Student();
            s.setStudent(imageId,id,name,sex,stuclass,check,score);
            data.add(s);
        }
        return data;
    }

}

