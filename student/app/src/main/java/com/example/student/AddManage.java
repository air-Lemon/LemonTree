package com.example.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddManage extends AppCompatActivity implements MyAdapter.InnerItemOnClickListener {
    ListView listView;
    public static DatabaseDao dao;
    private Context context;
    private MyAdapter adapter;
    public static List<Student> studentList = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去掉标题栏 第一种方法
        setContentView(R.layout.add_manage);
        listView = (ListView) findViewById(R.id.stuList);
        dao=new DatabaseDao(new DatabaseAccess(this));
        studentList=dao.getAllStudents();
        adapter = new MyAdapter(this,studentList);
        listView.setAdapter(adapter);
        adapter.setOnInnerItemOnClickListener(this);;
//        listView.setAdapter(new BaseAdapter()  {
//
//            @Override
//            public int getCount() {
//                return studentList.size();
//            }
//
//            @Override
//            public Object getItem(int i) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int i) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                Student s = studentList.get(position);
//
//                System.out.println(s.toString());
//                TextView textView = new TextView(AddManage.this);
//                textView.setText(s.toString());
//                textView.setTextSize(18);
//                return textView;
//            }
//
//        });
    }
    public void itemClick(View view) {
        int position = (Integer)view.getTag();
        switch (view.getId()){
            case R.id.bt_alter:
                Intent intent = new Intent();
                intent.setClass(this,AddActivity.class);
                intent.putExtra("user", (Serializable) studentList.get(position));
                intent.putExtra("position",position);
                startActivity(intent);
                break;
            case R.id.bt_delete:
                studentList.remove(position);
                dao.deleteStudentById(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(this,"已删除第"+(position+1)+"条数据",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


