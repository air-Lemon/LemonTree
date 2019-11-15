package com.example.student;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddManage extends AppCompatActivity implements MyAdapter.InnerItemOnClickListener {
    ListView listView;
    private Context context;
    private MyAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去掉标题栏 第一种方法
        setContentView(R.layout.add_manage);
        listView = (ListView) findViewById(R.id.stuList);
        MainActivity.studentList=MainActivity.dao.getAllStudents();
        adapter = new MyAdapter(this,MainActivity.studentList);
        listView.setAdapter(adapter);
        adapter.setOnInnerItemOnClickListener(this);
    }
    public void itemClick(View view) {
            final int position = (Integer)view.getTag();
            Student st  =  MainActivity.studentList.get(position);
            final String id=st.getId();
        switch (view.getId()){
            case R.id.bt_alter:
                Intent intent = new Intent();
                intent.setClass(this,AddActivity.class);
                Student s=new Student();
                s=MainActivity.studentList.get(position);
                intent.putExtra("user",s);
                intent.putExtra("position",position);
                startActivity(intent);
                break;
            case R.id.bt_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确认是否删除！");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.studentList.remove(position);
                    MainActivity.dao.deleteStudentById(id);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AddManage.this,"已删除第"+(position+1)+"条数据",Toast.LENGTH_SHORT).show();
                }
            }).show();
                break;
        }
    }
}


