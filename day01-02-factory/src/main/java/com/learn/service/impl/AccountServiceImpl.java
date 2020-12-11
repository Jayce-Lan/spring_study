package com.learn.service.impl;

import com.learn.dao.AccountMapper;
import com.learn.dao.impl.AccountMapperImpl;
import com.learn.factory.BeanFactory;
import com.learn.service.AccountService;

/**
 * 账户业务层实现类
 * 业务层负责调用持久层
 */
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper = (AccountMapper) BeanFactory.getBean("accountMapper");

    public void saveAccount() {
        accountMapper.saveAccount();
    }
}
