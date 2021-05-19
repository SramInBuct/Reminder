package com.example.reminder.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.reminder.entities.Event;
import com.example.reminder.service.MasterService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class AlertUtil {

    //缓存机制
    private static HashMap<Context,AlertUtil> cache = new HashMap<>();

    private static final String REMINDER_MESSAGE_INTENT = "com.example.reminder.broadcast.MESSAGE";

    private static HashMap<Integer,PendingIntent> pendingIntentHashMap = new HashMap<>();

    private AlarmManager manager;

    private ArrayList<Integer> notifications = new ArrayList<>();

    private Context context;

    public static AlertUtil Build(Context context) {
        if (cache.containsKey(context)) {
            return cache.get(context);
        }
        cache.put(context,new AlertUtil(context));
        return cache.get(context);
    }

    private AlertUtil(Context context) {
        this.context = context;
        manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    /**
     * 设置定时推送
     */
    public boolean setAlarm(Event event) {
        Date startDay = event.getBeginDate();
        Date endDay = event.getEndDate();
        Date today = new Date();
        Date beginTime = event.getBeginTime();
        Date endTime = event.getEndTime();
        if (startDay.before(endDay)&&(startDay.after(today) ||endDay.before(today))) {
            return false;
        }
        String name = event.getName();
        String content = event.getDescribe();
        Integer icon_id = event.getIcon_id();

        Date todayBegin = new Date();
        todayBegin.setHours(beginTime.getHours());
        todayBegin.setMinutes(beginTime.getMinutes());
        if (today.after(todayBegin)) {
            return false;
        }

        Integer priority = event.getPriority();

        Intent intent = new Intent(REMINDER_MESSAGE_INTENT);
        intent.setClass(context, MasterService.class);
        intent.putExtra("name",name);
        intent.putExtra("content",content);
        intent.putExtra("icon",icon_id);
        intent.putExtra("priority",priority);
        intent.putExtra("startOrOpen",true);
        intent.putExtra("notificationId",event.getId());
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentHashMap.put(event.getId(), pendingIntent);
        manager.set(AlarmManager.RTC, todayBegin.getTime(), pendingIntent);
//        if (endTime.after(beginTime)) {
//            Date todayEnd = new Date();
//            todayEnd.setHours(endTime.getHours());
//            todayEnd.setMinutes(endTime.getMinutes());
//            Intent endIntent = new Intent(REMINDER_MESSAGE_INTENT);
//            endIntent.setClass(context, MasterService.class);
//            endIntent.putExtra("startOrOpen",false);
//            endIntent.putExtra("notificationId",event.getId());
//            PendingIntent endPendingIntent = PendingIntent.getService(context, 0, endIntent, PendingIntent.FLAG_UPDATE_CURRENT);
////            pendingIntentHashMap.put(event.getId(), endPendingIntent);
//            manager.set(AlarmManager.RTC, todayEnd.getTime(), endPendingIntent);
//        }
        return true;
    }

    public void cancelAlarm(Event event) {
        if (pendingIntentHashMap.containsKey(event.getId())) {
            manager.cancel(pendingIntentHashMap.get(event.getId()));
        }
    }

    public void cancelAlarm(int id) {
        if (pendingIntentHashMap.containsKey(id)) {
            manager.cancel(pendingIntentHashMap.get(id));
        }
    }

}
