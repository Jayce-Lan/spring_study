package com.learn.service.impl;

import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import com.learn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional      //开启事务支持
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public Account getAccountById(Integer id) {
        return accountMapper.getAccountById(id);
    }

    public void transfer(Integer sourceId, Integer targetId, Float money) {
        Account source = accountMapper.getAccountById(sourceId);
        Account target = accountMapper.getAccountById(targetId);

        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);

        accountMapper.updateAccount(source);
//        int num = 1 / 0;
        accountMapper.updateAccount(target);
    }
}
