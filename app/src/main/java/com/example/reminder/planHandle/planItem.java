package com.example.reminder.planHandle;

import java.sql.Time;
import java.time.LocalDate;

public class planItem {
    private String planText;
    private String planDetails;
    private String date;
    private int ItemId;
    public planItem(String planText,String planDetails,String date,int itemId){
        this.planText=planText;
        this.planDetails="详细内容："+planDetails;
        this.date="时间："+date;
        this.ItemId=itemId;
    }

    public String getPlanText() {
        return planText;
    }

    public void setPlanText(String planText) {
        this.planText = planText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(String planDetails) {
        this.planDetails = planDetails;
    }


    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }
}
