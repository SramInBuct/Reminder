package com.example.reminder.entities;

import org.litepal.LitePalApplication;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Event extends LitePalSupport {
    //public Integer Id;
    private String Name;
    private Date BeginTime;
    private Date LastTime;
    private Integer Describe;
    private String  Frequency;
    private Integer Priority;
    private Integer Icon_id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(Date beginTime) {
        BeginTime = beginTime;
    }

    public Date getLastTime() {
        return LastTime;
    }

    public void setLastTime(Date lastTime) {
        LastTime = lastTime;
    }

    public Integer getDescribe() {
        return Describe;
    }

    public void setDescribe(Integer describe) {
        Describe = describe;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    public Integer getIcon_id() {
        return Icon_id;
    }

    public void setIcon_id(Integer icon_id) {
        Icon_id = icon_id;
    }
}
