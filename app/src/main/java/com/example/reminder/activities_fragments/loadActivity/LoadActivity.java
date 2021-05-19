package com.example.reminder.activities_fragments.loadActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.reminder.MainActivity;
import com.example.reminder.R;

public class LoadActivity extends AppCompatActivity {
    private static int sleepTime=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long costTime = System.currentTimeMillis() - start;
                long sec=sleepTime-costTime;
                if (sec>0){
                    try {
                        Thread.sleep(sec);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(LoadActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }
}