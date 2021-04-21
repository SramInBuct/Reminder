package com.example.reminder.entities;

public class User {


    private String name;

    private String uuid;

    private String mail;

    private String password;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public User(String name, String mail, String uuid, String password) {
        this.name = name;
        this.uuid = uuid;
        this.mail = mail;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", mail='" + mail + '\'' +
                ", token='" + password + '\'' +
                '}';
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
