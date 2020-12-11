package com.learn.ui;

import com.learn.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        //这里使用ClassPathXmlApplicationContext而不使用父类对象是方便销毁对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService = (AccountService) context.getBean("accountService");
        accountService.saveAccount();
        //手动关闭容器
        context.close();
    }
}
