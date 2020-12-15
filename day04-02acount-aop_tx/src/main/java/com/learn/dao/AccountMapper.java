package com.learn.dao;

import com.learn.pojo.Account;

import java.util.List;

/**
 * 账户持久层接口
 */
public interface AccountMapper {
    List<Account> getAccountList();
    Account getAccountById(Integer id);
    void saveAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Integer id);
}
