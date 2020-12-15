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

    /**
     * 转账功能
     * @param sourceId 转出账户id
     * @param targetId 转入账户id
     * @param money 转账金额
     */
    void transfer(Integer sourceId, Integer targetId, Float money);
}
