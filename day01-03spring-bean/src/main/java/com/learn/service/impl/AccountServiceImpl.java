package com.learn.service.impl;

import com.learn.dao.AccountMapper;
import com.learn.dao.impl.AccountMapperImpl;
import com.learn.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper = new AccountMapperImpl();

    public AccountServiceImpl() {
        System.out.println("对象被创建了");
    }

    public void saveAccount() {
        accountMapper.saveAccount();
    }
}
