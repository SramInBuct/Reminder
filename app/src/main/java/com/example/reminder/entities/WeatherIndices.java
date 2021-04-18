package com.example.reminder.entities;

public class WeatherIndices {
    private String date;
    private Integer type;
    private String name;
    private Integer level;
    private String category;
    private String text;

    @Override
    public String toString() {
        return "WeatherIndices{" +
                "date='" + date + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", category='" + category + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public WeatherIndices() {
    }

    public WeatherIndices(String date, Integer type, String name, Integer level, String category, String text) {
        this.date = date;
        this.type = type;
        this.name = name;
        this.level = level;
        this.category = category;
        this.text = text;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
