
package com.example.reminder.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DataUtil extends SQLiteOpenHelper {
    /**
     *
     * @param context   上下文
     * @param name      数据库名称
     * @param factory   游标工厂
     * @param version   版本号
     *
     */
    public static final String DATABASE_NAME = "reminder.db";
    public static final int VERSION_CODE=1;
    public static final String TAG = "DataUtil";

    public DataUtil(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Plans" +
                "(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "Name varchar(255),"+
                "BeginTime varchar(255),"+
                "LastTime varchar(255),"+
                "Describe varchar(255),"+
                "Frequency varchar(255),"+
                "Priority int,"+
                "Icon_id int"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        values.put("Name","What");
        values.put("BeginTime","2021-4-12");
        db.insert("Plans",null,values);
        Log.d(TAG,"Name:"+"What");

    }
}
