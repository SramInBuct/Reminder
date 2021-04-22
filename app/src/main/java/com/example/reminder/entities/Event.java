package com.example.reminder.entities;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Event extends LitePalSupport {
    private Integer id;
    @Column(nullable = false)
    private String Name;
    @Column(defaultValue = "CURRENT_TIMESTAMP")
    private Date BeginTime;
    @Column(defaultValue = "CURRENT_TIMESTAMP")
    private Date BeginDate;
    @Column(defaultValue = "CURRENT_TIMESTAMP")
    private Date EndTime;
    @Column(defaultValue = "CURRENT_TIMESTAMP")
    private Date EndDate;
    private String Describe;
    private String  Frequency;
    @Column(defaultValue ="1")
    private Integer Priority;
    @Column(nullable = false , defaultValue ="2131165309")
    private Integer Icon_id;

    public Event(){
    }

    public Event(Integer id, String name, Date beginTime, Date beginDate, Date lastTime, Date endDate, String describe, String frequency, Integer priority, Integer icon_id) {
        this.id = id;
        Name = name;
        BeginTime = beginTime;
        BeginDate = beginDate;
        EndTime = lastTime;
        EndDate = endDate;
        Describe = describe;
        Frequency = frequency;
        Priority = priority;
        Icon_id = icon_id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
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

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
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
                ", LastTime=" + EndTime +
                ", EndDate=" + EndDate +
                ", Describe=" + Describe +
                ", Frequency='" + Frequency + '\'' +
                ", Priority=" + Priority +
                ", Icon_id=" + Icon_id +
                '}';
    }
}
