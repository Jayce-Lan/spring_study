package com.learn.dao.impl;

import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户持久层接口的实现类
 */
@Repository("accountMapper")
public class AccountMapperImpl implements AccountMapper {
    @Autowired
    private QueryRunner runner;

    public List<Account> getAccountList() {
        try {
            String sql = "select * from account";
            return runner.query(sql, new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccountById(Integer id) {
        try {
            String sql = "select * from account where id = ?";
            return runner.query(sql, new BeanHandler<Account>(Account.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            String sql = "insert into account(name, money) values(?, ?)";
            runner.update(sql, account.getName(), account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            String sql = "update account set name = ?, money = ? where id = ?";
            runner.update(sql, account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(Integer id) {
        try {
            String sql = "delete from account where id = ?";
            runner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
