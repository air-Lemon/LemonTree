package com.example.student;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter implements View.OnClickListener// 实现内部监听接口
{
    private Context context;  //上下文
    private List<Student> studentList;   //学生集合
    private InnerItemOnClickListener innerItemOnClickListener;      //自定义内部点击事件的监听接口

    public MyAdapter(Context context,List<Student> list) {
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
        View v = View.inflate(context,R.layout.item,null);
        ImageView imageView = v.findViewById(R.id.img_logo);
        TextView id = v.findViewById(R.id.tx_id);
        TextView name = v.findViewById(R.id.tx_name);
        TextView sex=v.findViewById(R.id.tx_sex);
        ImageButton imageButtonAl = v.findViewById(R.id.bt_alter);
        ImageButton imageButtonDe = v.findViewById(R.id.bt_delete);
        final Student s  =  studentList.get(i);
        imageView.setImageResource(s.getImageId());
        id.setText(s.getId());
        name.setText(s.getName());
        sex.setText(s.getSex());
        imageButtonAl.setImageResource(R.drawable.edit);
        imageButtonDe.setImageResource(R.drawable.delete);
        imageButtonAl.setOnClickListener(this);
        imageButtonDe.setOnClickListener(this);
        imageButtonAl.setTag(i);//删除和编辑设置一个标志 使用item的i来定位 相当于一个id
        imageButtonDe.setTag(i);
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
