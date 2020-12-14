package com.learn.service.impl;

import com.learn.service.AccountService;

//业务层实现类
public class AccountServiceImpl implements AccountService {
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    public void update(int num) {
        System.out.println("执行了更新，并传入参数 " + num);
    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
