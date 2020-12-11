package com.learn.ui;

import com.learn.factory.BeanFactory;
import com.learn.service.AccountService;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            AccountService accountService = (AccountService) BeanFactory.getBeans("accountService");
            accountService.saveAccount();
            System.out.println(accountService.toString());
        }
    }
}
