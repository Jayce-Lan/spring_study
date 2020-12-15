package com.learn.service.impl;

import com.learn.dao.AccountMapper;
import com.learn.pojo.Account;
import com.learn.service.AccountService;
import com.learn.utils.TransactionManager;

import java.util.List;

/**
 * 业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;
    private TransactionManager transactionManager;

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public AccountMapper getAccountMapper() {
        return accountMapper;
    }

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public List<Account> getAccountList() {
        try {
            //开启事务
            transactionManager.startTransaction();
            //执行操作
            List<Account> accountList = accountMapper.getAccountList();
            //提交事务
            transactionManager.commitTransaction();
            //返回结果
            return accountList;
        } catch (Exception e) {
            //事务回滚
            transactionManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            transactionManager.release();
        }
    }

    public Account getAccountById(Integer id) {
        try {
            transactionManager.startTransaction();
            Account account = accountMapper.getAccountById(id);
            transactionManager.commitTransaction();
            return account;
        } catch (Exception e) {
            transactionManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            transactionManager.startTransaction();
            accountMapper.saveAccount(account);
            transactionManager.commitTransaction();
        } catch (Exception e) {
            transactionManager.rollbackTransaction();
        } finally {
            transactionManager.release();
        }
    }

    public void updateAccount(Account account) {
        try {
            transactionManager.startTransaction();
            accountMapper.updateAccount(account);
            transactionManager.commitTransaction();
        } catch (Exception e) {
            transactionManager.rollbackTransaction();
        } finally {
            transactionManager.release();
        }
    }

    public void deleteAccount(Integer id) {
        try {
            transactionManager.startTransaction();
            accountMapper.deleteAccount(id);
            transactionManager.commitTransaction();
        } catch (Exception e) {
            transactionManager.rollbackTransaction();
        } finally {
            transactionManager.release();
        }
    }

    /**
     * 转账功能
     * 有关线程的提交问题，都应该出现在业务层中
     *
     * 业务流程：
     *  1.根据id查询转出账户
     *  2.根据id查询转入账户
     *  3.转出账户金额减少
     *  4.转入账户金额增加
     *  5.更新转出账户
     *  6.更新转入账户
     *
     * @param sourceId 转出账户id
     * @param targetId 转入账户id
     * @param money 转账金额
     */
    public void transfer(Integer sourceId, Integer targetId, Float money) {
        try {
            transactionManager.startTransaction();

            Account source = getAccountById(sourceId);
            Account target = getAccountById(targetId);

            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);

            updateAccount(source);
            updateAccount(target);

            transactionManager.commitTransaction();
        } catch (Exception e) {
            transactionManager.rollbackTransaction();
        } finally {
            transactionManager.release();
        }
    }
}
