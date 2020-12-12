package com.learn.dao.impl;

import com.learn.dao.AccountMapper;
import org.springframework.stereotype.Repository;

@Repository("accountMapper2")
public class AccountMapperImpl2 implements AccountMapper {
    public void saveAccount() {
        System.out.println("保存了账户2。。。");
    }
}
