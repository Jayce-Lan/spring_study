package com.learn.ui;

import com.learn.dao.AccountMapper;
import com.learn.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {
    /**
     * 获取spring中的IoC核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext  可以加载类路径下的配置文件，要求配置文件一定要在类路径下
     *      FileSystemXmlApplicationContext     可以加载磁盘任意路径下的配置文件，但是必须有访问权限
     *      AnnotationConfigApplicationContext      用于读取注解创建容器
     *
     * ApplicationContext：立即加载的策略，读取配置文件后就立即创建对象
     * BeanFactory：采用延迟加载的方式创建对象
     *
     * @param args
     */
    public static void main(String[] args) {
        //获取核心容器对象
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //根据id获取bean对象
        AccountService accountService = (AccountService) context.getBean("accountService");
        AccountMapper accountMapper = context.getBean("accountMapper", AccountMapper.class);

        System.out.println(accountService);
        System.out.println(accountMapper);
    }
}
