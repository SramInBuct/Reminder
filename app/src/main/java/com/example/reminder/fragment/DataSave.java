package com.example.reminder.fragment;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.reminder.R;

public class DataSave {
    private Context context;
    public DataSave(Context context){
        this.context=context;
    }
    public void save(String key,String value){
        String name=context.getResources().getString(R.string.SavedData);
        SharedPreferences shp=context.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = shp.edit();
        edit.putString(key,value);
        edit.apply();
    }
    public String load(String key){
        String name=context.getResources().getString(R.string.SavedData);
        SharedPreferences shp=context.getSharedPreferences(name,Context.MODE_PRIVATE);
        return shp.getString(key,"error");
    }
}
