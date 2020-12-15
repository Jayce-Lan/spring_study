package com.learn.service;

//账户业务层接口
public interface AccountService {
    //模拟保存账户
    void saveAccount();
    //模拟更新账户
    void update(int num);
    //模拟删除账户
    int deleteAccount();
}
