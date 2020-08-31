package com.daopro.vo;

import java.util.Date;

public class Member {
    private String mid;
    private String name;
    private Integer age;
    private String email;
    private String sex;
    private Date birthday;
    private String note;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMid() {
        return mid;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getNote() {
        return note;
    }
}
