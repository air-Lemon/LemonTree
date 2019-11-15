package com.example.student;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCheck  extends AppCompatActivity implements View.OnClickListener{
    public ImageView photo;
    public TextView id;
    public TextView name;
    public TextView sex;
    public TextView stuclass;
    private RadioButton chidao;
    private RadioButton queqing;
    private RadioButton qingjia;
    private RadioButton qiandao;
    public Button save;
    public Button next;
    public int i=0;
    int n=MainActivity.studentList.size();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_check);
        MainActivity.studentList=MainActivity.dao.getAllStudents();
        photo=(ImageView)findViewById(R.id.photo);
        id=(TextView)findViewById(R.id.id);
        name=(TextView)findViewById(R.id.name);
        sex=(TextView)findViewById(R.id.sex);
        stuclass=(TextView)findViewById(R.id.stuclass);
        chidao=(RadioButton)findViewById(R.id.chidao);
        queqing=(RadioButton)findViewById(R.id.queqing);
        qingjia=(RadioButton)findViewById(R.id.qingjia);
        qiandao=(RadioButton)findViewById(R.id.qiandao);
        save=(Button)findViewById(R.id.save);
        next=(Button)findViewById(R.id.next);
        chidao.setOnClickListener(this);
        queqing.setOnClickListener(this);
        qingjia.setOnClickListener(this);
        qiandao.setOnClickListener(this);
        save.setOnClickListener(this);
        next.setOnClickListener(this);
        getSupportActionBar().hide();//去掉标题栏 第一种方法
        final Student s = MainActivity.studentList.get(i);
        Bitmap imagebitmap = BitmapFactory.decodeByteArray(s.getImageId(), 0, s.getImageId().length);
        photo.setImageBitmap(imagebitmap);
        id.setText(s.getId());
        name.setText(s.getName());
        sex.setText(s.getSex());
        stuclass.setText(s.getStuclass());
        i++;

    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                String check="";
                int score=0;
                String sid = id.getText().toString().trim();
                if (chidao.isChecked()) {
                    check = "迟到";
                    score=-5;
                }
                else if(queqing.isChecked()){
                    check = "缺勤";
                    score=-10;
                }
                else if(qingjia.isChecked()){
                    check = "请假";
                    score=0;
                }
                else if(qiandao.isChecked()){
                    check = "签到";
                    score=5;
                }
                MainActivity.dao.insertCheck(check,sid);
                MainActivity.dao.insertScore(sid,score);
                Toast.makeText(AddCheck.this,"已保存考勤状态为:"+check+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.next:
                if(i<n) {
                    final Student s = MainActivity.studentList.get(i);
                    Bitmap imagebitmap = BitmapFactory.decodeByteArray(s.getImageId(), 0, s.getImageId().length);
                    photo.setImageBitmap(imagebitmap);
                    id.setText(s.getId());
                    name.setText(s.getName());
                    sex.setText(s.getSex());
                    stuclass.setText(s.getStuclass());
                    i++;
                }
                else{
                    Intent intent = new Intent();
                    intent.setClassName(AddCheck.this,"com.example.student.AddAll");
                    startActivity(intent);
                }
                break;
        }
    }
}
