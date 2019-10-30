package com.example.student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//        String cs="";
//        for(String ssss:s.getCourses())
//        {
//            cs+=ssss+",";
//        }
//        values.put("courses",cs);


        return databaseAccess.getWritableDatabase().insert("student", null, values);
    }
    public int deleteStudentById(int id) {
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
//        String cs="";
//        for(String ssss:s.getCourses())
//        {
//            cs+=ssss+",";
//        }
//        values.put("courses",cs);
        return databaseAccess.getWritableDatabase().update("student", values,
                "id=?", new String[] { s.getId() + "" });
    }
    public List<Student> getAllStudents() {
        //date desc
        List<Student> data = new ArrayList<Student>();
        Cursor cursor = databaseAccess.getWritableDatabase().query("student", null, null, null, null, null, null);
        while(cursor.moveToNext()) {

//            Map<String, Object> map = new HashMap<String, Object>();
            int imageId=cursor.getInt(cursor.getColumnIndex("imageId"));
//            map.put("imageId",imageId);
            String id = cursor.getString(cursor.getColumnIndex("id"));
//            map.put("id", id);
            String name = cursor.getString(cursor.getColumnIndex("name"));
//            map.put("name", name);
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
//            map.put("sex", sex);
            String stuclass = cursor.getString(cursor.getColumnIndex("stuclass"));
//            map.put("stuclass",stuclass);

            Student st=new Student();
            st.setStudent(imageId,id,name,sex,stuclass);
            AddManage.studentList.add(st);
            ArrayList<String> arrayList=new ArrayList<>();

            Student s=new Student();
            s.setStudent(imageId,id,name,sex,stuclass);
            data.add(s);
        }
        return data;
    }

}

