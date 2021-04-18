package com.example.reminder.util;

import android.util.Log;

import com.example.reminder.entities.User;

import org.litepal.LitePal;

public class UserDao {

    public static final String TAG = "UserDao";

    public static void find(){
        LitePal.findAll(User.class);
    }

    public static User find(Integer id){
        Log.d(TAG, "find: "+LitePal.find(User.class,id));
        return LitePal.find(User.class,id);
    }

    public static void insert(User user){
        user.save();
        Log.d(TAG, "insert: "+user.toString());
    }

    public static void delete(Integer id){
        Log.d(TAG, "delete: "+LitePal.find(User.class,id));
        LitePal.delete(User.class,id);

    }
    public static void update(User user,Integer id){
        Log.d(TAG, "update: "+LitePal.find(User.class,id));
        user.updateAll("id = ? ",id.toString());
        Log.d(TAG, "to: "+LitePal.find(User.class,id));
    }
}
