package com.learn.dao.impl;

import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountMapperImpl2 implements AccountMapper {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account getAccountById(Integer id) {
        String sql = "select * from account where id = ?";
        List<Account> account = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class), id);
        return account.isEmpty() ? null : account.get(0);
    }

    public List<Account> getAccount() {
        String sql = "select * from account";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
    }


    public void updateAccount(Account account) {
        String sql = "update account set name = ?, money = ? where id = ?";
        jdbcTemplate.update(sql, account.getName(), account.getMoney(), account.getId());
    }
}
