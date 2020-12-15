package com.learn.service;

import com.learn.pojo.Account;

public interface AccountService {
    Account getAccountById(Integer id);

    /**
     * 在业务层完成的转账功能
     * @param sourceId 转账人
     * @param targetId 收款人
     * @param money 金额
     */
    void transfer(Integer sourceId, Integer targetId, Float money);
}
