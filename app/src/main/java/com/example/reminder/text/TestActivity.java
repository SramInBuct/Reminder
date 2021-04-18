package com.example.reminder.text;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reminder.R;
import com.example.reminder.entities.Event;
import com.example.reminder.entities.User;
import com.example.reminder.entities.WeatherDays;
import com.example.reminder.util.EventDao;
import com.example.reminder.util.NetworkUtil;

import org.json.JSONException;
import org.litepal.LitePal;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 2:
                        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                        login.edit().putString("token",(String)msg.obj).commit();
                        break;
                }
            }
        };
//        NetworkUtil.GetWeatherDays(handler);
//        NetworkUtil.GetWeatherIndices(handler);

        try {
            User user = new User("十三","835619974@qq.com","","123456789");
            NetworkUtil.postRegister(handler,user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("SharedPreferences","token->"+getSharedPreferences("login", Context.MODE_PRIVATE).getString("token","null"));
//
//        //创建数据库
////        DataUtil dataUtil = new DataUtil(this);
////        dataUtil.getWritableDatabase();
////        SQLiteDatabase db = dataUtil.getWritableDatabase();
////        dataUtil.insert(db);
//
//        SQLiteDatabase db = LitePal.getDatabase();
//        Event event = new Event();
//        event.setName("2580");
//        EventDao.insert(event);
//        EventDao.find(1);
//        EventDao.delete(1);
//        Event event1 = new Event();
//        event.setPriority(2);
//        EventDao.update(event,2);
    }

}
