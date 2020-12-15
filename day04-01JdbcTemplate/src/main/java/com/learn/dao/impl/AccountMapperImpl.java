package com.learn.dao.impl;

import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;    //防止注入多个JdbcTemplate

import java.util.List;

public class AccountMapperImpl extends JdbcDaoSupport implements AccountMapper {

    public Account getAccountById(Integer id) {
        String sql = "select * from account where id = ?";
        List<Account> account = super.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Account>(Account.class), id);
        return account.isEmpty() ? null : account.get(0);
    }

    public List<Account> getAccount() {
        String sql = "select * from account";
        return super.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Account>(Account.class));
    }


    public void updateAccount(Account account) {
        String sql = "update account set name = ?, money = ? where id = ?";
        super.getJdbcTemplate().update(sql, account.getName(), account.getMoney(), account.getId());
    }
}
