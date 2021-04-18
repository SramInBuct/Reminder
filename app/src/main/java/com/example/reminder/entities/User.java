package com.example.reminder.entities;

public class User {

    private String name;

    private String uuid;

    private String mail;

    public User() {
    }

    public User(String name, String uuid, String mail) {
        this.name = name;
        this.uuid = uuid;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
