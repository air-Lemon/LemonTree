package com.example.student;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import java.util.ArrayList;
public class DatabaseAccess extends SQLiteOpenHelper{
    private static final String TAG = "StudentDBHelper";
    public static final String DB_NAME = "studentManage.db";
    public static final int VERSION = 1;

    //构造方法
    public DatabaseAccess(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseAccess(Context context) {

        this(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table student(\n"+
                "   imageId integer,\n"+
                "   id integer primary key autoincrement,\n"+
                "   name text,\n"+
                "   sex text,\n"+
                "   stuclass text\n"+
                "   )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        Log.v(TAG, "onUpgrade");
    }
}
