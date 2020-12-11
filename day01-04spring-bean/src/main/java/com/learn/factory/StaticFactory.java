package com.learn.factory;

import com.learn.service.AccountService;
import com.learn.service.impl.AccountServiceImpl;

public class StaticFactory {
    public static AccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
