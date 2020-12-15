package com.learn.dao;

import com.learn.pojo.Account;

import java.util.List;

public interface AccountMapper {
    Account getAccountById(Integer id);
    List<Account> getAccount();
    void updateAccount(Account account);
}
