package com.learn.dao.impl;

import com.learn.dao.AccountMapper;
import org.springframework.stereotype.Repository;

@Repository(value = "accountMapper")
public class AccountMapperImpl implements AccountMapper {
    public void saveAccount() {
        System.out.println("保存一名用户。。。");
    }
}
