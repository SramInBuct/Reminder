
package com.example.reminder.util;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.reminder.entities.Event;

import org.litepal.LitePal;

public class EventDao<T> {

    public static final String TAG = "EventDao";

    public EventDao() {
    }


    public static void find(){
        LitePal.findAll(Event.class);
    }

    public static Event find(Integer id){
        Log.d(TAG, "find: "+LitePal.find(Event.class,id));
        return LitePal.find(Event.class,id);
    }

    public static void insert(Event event){
        event.save();
        Log.d(TAG, "insert: "+event.toString());
    }
    
    public static void delete(Integer id){
        Log.d(TAG, "delete: "+LitePal.find(Event.class,id));
        LitePal.delete(Event.class,id);

    }
    public static void update(Event event,Integer id){
        Log.d(TAG, "update: "+LitePal.find(Event.class,id));
        event.updateAll("id = ? ",id.toString());
        Log.d(TAG, "to: "+LitePal.find(Event.class,id));
    }

}
