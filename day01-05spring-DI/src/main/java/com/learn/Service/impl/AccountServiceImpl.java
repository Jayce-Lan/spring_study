package com.learn.Service.impl;

import com.learn.Service.AccountService;

import java.util.Date;

public class AccountServiceImpl implements AccountService {
    //如果是经常变化的数据，并不适合注入方式
    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("保存了一名用户。。。");
        System.out.println(this.name + " " + this.age + " " + this.birthday);
    }
}
