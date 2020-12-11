package com.learn.factory;

import com.learn.service.AccountService;
import com.learn.service.impl.AccountServiceImpl;

/**
 * 模拟一个工厂类，该类可能存在于jar包当中，我们无法通过修改源码方式提供默认构造函数
 */
public class InstanceFactory {

    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }

}
