package com.example.reminder.text;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reminder.R;
import com.example.reminder.entities.WeatherDays;
import com.example.reminder.util.DataUtil;
import com.example.reminder.util.NetworkUtil;

import org.litepal.LitePal;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
//        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                switch (msg.what){
//                    case 2:
//                        WeatherDays t1 = (WeatherDays) msg.obj;
//                        TextView viewById = findViewById(R.id.msgShow);
//                        viewById.setText(t1.toString());
//                        break;
//                }
//            }
//        };
//
//        NetworkUtil.GetWeatherDays(handler);

        //创建数据库
//        DataUtil dataUtil = new DataUtil(this);
//        dataUtil.getWritableDatabase();
//        SQLiteDatabase db = dataUtil.getWritableDatabase();
//        dataUtil.insert(db);
        LitePal.initialize(this);
        SQLiteDatabase db = LitePal.getDatabase();
    }

}