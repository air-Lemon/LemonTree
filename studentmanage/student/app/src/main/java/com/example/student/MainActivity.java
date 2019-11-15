package com.example.student;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button add;
    Button manage;
    Button check;
    Button all;
    public static DatabaseDao dao;
    public static List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        add = (Button) findViewById(R.id.add);
        manage = (Button) findViewById(R.id.manage);
        check = (Button) findViewById(R.id.check);
        all = (Button) findViewById(R.id.all);
        dao=new DatabaseDao(new DatabaseAccess(this));
        studentList=dao.getAllStudents();
    }
    public void addStudent(View v) {
        Intent intent=new Intent(MainActivity.this,AddActivity.class);
        startActivity(intent);
    }

    public void addManage(View v) {
        Intent intent=new Intent(MainActivity.this,AddManage.class);
        startActivity(intent);
    }

    public void addCheck(View v) {
        Intent intent=new Intent(MainActivity.this,AddCheck.class);
        startActivity(intent);
    }
    public void addAll(View v){
        Intent intent=new Intent(MainActivity.this,AddAll.class);
        startActivity(intent);
    }
}
