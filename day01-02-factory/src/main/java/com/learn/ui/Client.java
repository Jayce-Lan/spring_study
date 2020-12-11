package com.learn.ui;

import com.learn.service.AccountService;
import com.learn.service.impl.AccountServiceImpl;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
        accountService.saveAccount();
    }
}
