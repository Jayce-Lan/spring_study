package com.learn.dao.impl;

import com.learn.dao.AccountMapper;

public class AccountMapperImpl implements AccountMapper {
    /**
     * 账户持久层实现类
     */
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
