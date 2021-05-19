package com.example.reminder.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.reminder.MainActivity;
import com.example.reminder.R;


public class MasterService extends Service {
    public static final String CHANNEL = "com.example.reminder";

    private static final String TAG = "MasterService";

    private NotificationManager notificationManager;

    public MasterService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannel notificationChannel = new NotificationChannel("com.example.reminder", "Master", NotificationManager.IMPORTANCE_HIGH);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        Log.d(TAG,"onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d( TAG, "onStartCommand executed");
        new Thread(()->{
            boolean startOrOpen = intent.getBooleanExtra("startOrOpen",true);
            if (startOrOpen) {
                int id = intent.getIntExtra("notificationId",0);
                String name = intent.getStringExtra("name");
                String content = intent.getStringExtra("content");
                int icon = intent.getIntExtra("icon",R.drawable.note);


                int priority = intent.getIntExtra("priority",0);
                sendInformation(id,name,content,icon,false,priority);
            } else {
                int id = intent.getIntExtra("notificationId",0);
                removeInformation(id);
            }

        }).start();
        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void sendInformation(int id,String title,String content,int icon,boolean cancelAble,int flag){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,flag);
        Notification notification = new NotificationCompat.Builder(this,CHANNEL)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),icon))
                .setContentIntent(pi)
                .setAutoCancel(cancelAble)
                .setWhen(System.currentTimeMillis())
                .build();
        notificationManager.notify(id,notification);
    }

    public void removeInformation(int notificationId) {
        notificationManager.cancel(notificationId);
    }


}