package com.learn.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，包含开启事务，提交事务，回滚事务和释放连接几个方法
 */
public class TransactionManager {
    //获取当前程序中的线程
    private ConnectionUtils connUtils;

    public void setConnUtils(ConnectionUtils connUtils) {
        this.connUtils = connUtils;
    }

    //开启事务
    public void startTransaction() {
        try {
            connUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public void commitTransaction() {
        try {
            connUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //回滚事务
    public void rollbackTransaction() {
        try {
            connUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //释放连接
    public void release() {
        try {
            connUtils.getThreadConnection().close();    //将连接还回连接池中
            connUtils.removeConnection();   //连接解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
