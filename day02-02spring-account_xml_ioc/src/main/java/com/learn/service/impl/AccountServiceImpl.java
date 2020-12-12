package com.learn.service.impl;

import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import com.learn.service.AccountService;

import java.util.List;

/**
 * 业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;

    public AccountMapper getAccountMapper() {
        return accountMapper;
    }

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public List<Account> getAccountList() {
        return accountMapper.getAccountList();
    }

    public Account getAccountById(Integer id) {
        return accountMapper.getAccountById(id);
    }

    public void saveAccount(Account account) {
        accountMapper.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }

    public void deleteAccount(Integer id) {
        accountMapper.deleteAccount(id);
    }
}
