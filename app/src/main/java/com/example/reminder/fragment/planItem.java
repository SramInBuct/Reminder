package com.example.draft2;

import java.sql.Time;

public class planItem {
    private String planText;
//    private Time time;
    private int ItemId;
    public planItem(String planText,int itemId){
        this.planText=planText;
//        this.time=time;
        this.ItemId=itemId;
    }

    public String getPlanText() {
        return planText;
    }

    public void setPlanText(String planText) {
        this.planText = planText;
    }

//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }
}
