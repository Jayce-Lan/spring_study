package com.learn.service.impl;

import com.learn.service.AccountService;

public class AccountServiceImpl implements AccountService {

    public AccountServiceImpl() {
        System.out.println("对象被创建了");
    }

    public void saveAccount() {
        System.out.println("Service中的saveAccount方法执行了。。。");
    }

    public void init() {
        System.out.println("对象初始化。。。");
    }

    public void destroy() {
        System.out.println("对象销毁。。。");
    }
}
