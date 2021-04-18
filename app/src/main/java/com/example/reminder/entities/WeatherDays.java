package com.example.reminder.entities;

public class WeatherDays {

    private Integer tempMax;
    private Integer tempMin;
    private String textDay;
    private String textNight;
    private String wind360Day;
    private Integer wind360Night;
    private String windScaleDay;
    private String windScaleNight;
    private String windDirDay;
    private String windDirNight;
    private Integer uvIndex;
    private Integer humidity;
    private String date;

    public WeatherDays() {
    }

    @Override
    public String toString() {
        return "WeatherDays{" +
                "tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", textDay='" + textDay + '\'' +
                ", textNight='" + textNight + '\'' +
                ", wind360Day='" + wind360Day + '\'' +
                ", wind360Night=" + wind360Night +
                ", windScaleDay='" + windScaleDay + '\'' +
                ", windScaleNight='" + windScaleNight + '\'' +
                ", windDirDay='" + windDirDay + '\'' +
                ", windDirNight='" + windDirNight + '\'' +
                ", uvIndex=" + uvIndex +
                ", humidity=" + humidity +
                ", date='" + date + '\'' +
                '}';
    }

    public WeatherDays(Integer tempMax, Integer tempMin, String textDay, String textNight, String wind360Day, Integer wind360Night, String windScaleDay, String windScaleNight, String windDirDay, String windDirNight, Integer uvIndex, Integer humidity, String date) {
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.textDay = textDay;
        this.textNight = textNight;
        this.wind360Day = wind360Day;
        this.wind360Night = wind360Night;
        this.windScaleDay = windScaleDay;
        this.windScaleNight = windScaleNight;
        this.windDirDay = windDirDay;
        this.windDirNight = windDirNight;
        this.uvIndex = uvIndex;
        this.humidity = humidity;
        this.date = date;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public String getWind360Day() {
        return wind360Day;
    }

    public void setWind360Day(String wind360Day) {
        this.wind360Day = wind360Day;
    }

    public Integer getWind360Night() {
        return wind360Night;
    }

    public void setWind360Night(Integer wind360Night) {
        this.wind360Night = wind360Night;
    }

    public String getWindScaleDay() {
        return windScaleDay;
    }

    public void setWindScaleDay(String windScaleDay) {
        this.windScaleDay = windScaleDay;
    }

    public String getWindScaleNight() {
        return windScaleNight;
    }

    public void setWindScaleNight(String windScaleNight) {
        this.windScaleNight = windScaleNight;
    }

    public String getWindDirDay() {
        return windDirDay;
    }

    public void setWindDirDay(String windDirDay) {
        this.windDirDay = windDirDay;
    }

    public String getWindDirNight() {
        return windDirNight;
    }

    public void setWindDirNight(String windDirNight) {
        this.windDirNight = windDirNight;
    }

    public Integer getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
