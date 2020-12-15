package com.learn.service.impl;

import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import com.learn.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public Account getAccountById(Integer id) {
        return accountMapper.getAccountById(id);
    }

    public void transfer(Integer sourceId, Integer targetId, Float money) {
        Account source = accountMapper.getAccountById(sourceId);
        Account target = accountMapper.getAccountById(targetId);

        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);

        accountMapper.updateAccount(source);
        int num = 1 / 0;
        accountMapper.updateAccount(target);
    }
}
