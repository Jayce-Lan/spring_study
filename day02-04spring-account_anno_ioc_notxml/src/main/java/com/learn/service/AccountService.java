package com.learn.service;

import com.learn.pojo.Account;

import java.util.List;

/**
 * 账户业务层接口
 */
public interface AccountService {
    List<Account> getAccountList();
    Account getAccountById(Integer id);
    void saveAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Integer id);
}
