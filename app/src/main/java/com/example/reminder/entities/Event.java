package com.example.reminder.entities;

import com.example.reminder.R;

import org.litepal.LitePalApplication;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Calendar;
import java.util.Date;

public class Event extends LitePalSupport {
    @Column(nullable = false)
    private String Name;
    @Column(defaultValue = "CURRENT_TIMESTAMP")
    private Date BeginTime;
    @Column(defaultValue = "CURRENT_TIMESTAMP")
    private Date BeginDate;
    private Date LastTime;
    @Column(defaultValue = "CURRENT_TIMESTAMP")
    private Date EndDate;
    private Integer Describe;
    private String  Frequency;
    @Column(defaultValue ="1")
    private Integer Priority;
    @Column(nullable = false , defaultValue ="700060")
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

    public Date getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(Date beginDate) {
        BeginDate = beginDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Name='" + Name + '\'' +
                ", BeginTime=" + BeginTime +
                ", BeginDate=" + BeginDate +
                ", LastTime=" + LastTime +
                ", EndDate=" + EndDate +
                ", Describe=" + Describe +
                ", Frequency='" + Frequency + '\'' +
                ", Priority=" + Priority +
                ", Icon_id=" + Icon_id +
                '}';
    }
}
