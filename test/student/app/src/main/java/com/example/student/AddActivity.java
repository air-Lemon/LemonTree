package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner studentclass;
    private ImageView photo;
    private static int result=1;//camera结果码
    private EditText id;
    private EditText name;
    private RadioButton male;
    private RadioButton female;
    private String stuclass="";
    private Student s_edit;
    private int position;
    private Button save;
    private ArrayAdapter<String> arrayAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去掉标题栏 第一种方法
        setContentView(R.layout.add_student);
     //   photo=(ImageView)findViewById(R.id.photo);
        studentclass=(Spinner)findViewById(R.id.studentclass);
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        save=(Button)findViewById(R.id.save);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        save.setOnClickListener(this);
        initSpinner();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            s_edit= (Student)bundle.get("user");
            position=(Integer)bundle.get("position");
            initData(s_edit);
        }


    }
    private  void initData(Student s){//信息回显
        id.setText(s.getId()+"");
        name.setText(s.getName());
        if(s.getSex().equals("男")){
            male.setChecked(true);
            female.setChecked(false);
        }else{
            male.setChecked(false);
            female.setChecked(true);
        }
//        for(int i=0;i<arrayAdapter.getCount();i++){//学院信息回显
//            if (TextUtils.equals(s.getStuclass(), arrayAdapter.getItem(i).toString())) {
//                studentclass.setSelection(i,true);
//                break;
//            }
     //   }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                int imageid;
                String sid = id.getText().toString().trim();
                String sname = name.getText().toString().trim();
                String ssex;
                String sclass=stuclass;
                if (male.isChecked()) {
                    ssex = "男";
                    imageid = R.drawable.avatarmale;
                } else {
                    ssex = "女";
                    imageid = R.drawable.avatarfemale;
                }
                if (TextUtils.isEmpty(sid)) {
                    id.setError("信息不能为空");
                    return;
                }
                if (TextUtils.isEmpty(sname)) {
                    name.setError("信息不能为空");
                    return;
                }
                if (!isDigital(sid)) {
                    id.setError("请输入数字");
                    return;
                }
                Student s = new Student();
                s.setStudent(imageid, sid, sname, ssex, sclass);
                if (s_edit != null) {
                    if (!s_edit.getSex().equals(ssex)) {
                    } else {
                        s.setImageId(s_edit.getImageId());
                        AddManage.studentList.set(position, s);
                    }
                    MainActivity.dao.updateStudent(s);
                } else {
                    MainActivity.dao.addStudent(s);
                    AddManage.studentList.add(s);
                }
                Intent intent = new Intent();
                intent.setClassName(AddActivity.this,"com.example.student.MainActivity");
                startActivity(intent);

                break;
            case R.id.male:
                male.setChecked(true);
                female.setChecked(false);
                break;
            case R.id.female:
                male.setChecked(false);
                female.setChecked(true);
                break;
            case R.id.photo:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, result);
        }
    }
//判断输入是否是数字
    private boolean isDigital(String num) {
        return num.matches("[0-9]{1,}");
    }

    public void initSpinner(){
        String[] arr={"曙光一班","计科一班"};
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
        studentclass.setAdapter(arrayAdapter);
        studentclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stuclass=(String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == result) {
                Uri uri = data.getData();
                if (uri == null) {
                    Toast.makeText(getApplicationContext(), "未选择图片", Toast.LENGTH_LONG).show();
                } else {
                    String path = null;
                    String[] proj = {MediaStore.Images.Media.DATA};
                    //通过URi获取到具体的路径
                    Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        path = cursor.getString(cursor.getColumnIndex(proj[0]));
                        Log.e("sydlog", "path=" + path);
                        Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
                        try {
                            FileInputStream fis = new FileInputStream(path);
                            Bitmap bitmap = BitmapFactory.decodeStream(fis);
                            photo.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
