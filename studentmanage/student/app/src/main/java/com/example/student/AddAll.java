package com.example.student;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AddAll extends AppCompatActivity {
    ListView listView;
    private Context context;
    private AllAdapter adapter;
    public Button clear;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去掉标题栏 第一种方法
        setContentView(R.layout.add_all);
        clear=(Button)findViewById(R.id.clear);
        listView = (ListView) findViewById(R.id.stuList);
        MainActivity.studentList = MainActivity.dao.getAllStudents();
        adapter = new AllAdapter(this, MainActivity.studentList);
        listView.setAdapter(adapter);
    }
    public void clear(View view){
        int n=MainActivity.studentList.size();
        for(int i=0;i<n;i++){
            final Student s = MainActivity.studentList.get(i);
            String sid = s.getId();
            MainActivity.dao.deleteCheck(sid);
        }
        MainActivity.studentList = MainActivity.dao.getAllStudents();
        adapter = new AllAdapter(this, MainActivity.studentList);
        listView.setAdapter(adapter);
    }
}
