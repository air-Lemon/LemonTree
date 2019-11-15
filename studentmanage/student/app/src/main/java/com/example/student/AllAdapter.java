package com.example.student;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AllAdapter extends BaseAdapter implements View.OnClickListener// 实现内部监听接口
{
    private Context context;  //上下文
    private List<Student> studentList;   //学生集合
    private InnerItemOnClickListener innerItemOnClickListener;      //自定义内部点击事件的监听接口

    public AllAdapter(Context context, List<Student> list) {
        this.context = context;
        this.studentList = list;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.item1,null);
        ImageView imageView = v.findViewById(R.id.img_logo);
        TextView id = v.findViewById(R.id.tx_id);
        TextView name = v.findViewById(R.id.tx_name);
        TextView sex=v.findViewById(R.id.tx_sex);
        TextView check=v.findViewById(R.id.tx_check);
        TextView score=v.findViewById(R.id.tx_score);
        final Student s  =  studentList.get(i);
        Bitmap imagebitmap = BitmapFactory.decodeByteArray(s.getImageId(), 0, s.getImageId().length);
        imageView.setImageBitmap(imagebitmap);
        id.setText(s.getId());
        name.setText(s.getName());
        sex.setText(s.getSex());
        check.setText(s.getCheck());
        String sss=String.valueOf(s.getScore());
        score.setText(sss);
        return v;
    }



    @Override
    public void onClick(View view) {
        innerItemOnClickListener.itemClick(view);//实现接口  当点击事件发生时 调用子类重写的itemclick方法
    }

    interface InnerItemOnClickListener{//定义接口  将listview中内部控件点击传给itemClick处理函数
        public abstract void itemClick(View view);
    }
    public void setOnInnerItemOnClickListener(InnerItemOnClickListener listener){//实现此接口的类对象除传入
        this.innerItemOnClickListener=listener;
    }
}
