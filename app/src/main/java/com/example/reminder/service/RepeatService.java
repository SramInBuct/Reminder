package com.example.reminder.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

import com.example.reminder.entities.Event;
import com.example.reminder.util.AlertUtil;
import com.example.reminder.util.EventDao;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class RepeatService extends IntentService {

    private final static long time = 24*60*60*60*1000;

    private static final String TAG = "RepeatService";

    public RepeatService() {
        super("RepeatService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ArrayList<Event> events = EventDao.findAll();
        for (Event event : events) {
            boolean setAlarm = AlertUtil.Build(getApplicationContext()).setAlarm(event);
            if (setAlarm) {
                //@TODO 事件已过时间
//                EventDao.delete(event.getId());
            }
        }

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent in = new Intent();
        in.setClass(getApplicationContext(),RepeatService.class);
        PendingIntent pi = PendingIntent.getService(getApplicationContext(),0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        manager.set(AlarmManager.RTC,System.currentTimeMillis()+time,pi);
    }
}