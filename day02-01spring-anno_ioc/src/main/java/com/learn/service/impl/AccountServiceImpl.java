package com.learn.service.impl;

import com.learn.dao.AccountMapper;
import com.learn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;


/**
 * 业务层实现类
 * 原始的xml配置：
 * <bean id="accountService" class="com.learn.service.impl.AccountServiceImpl" scope="" init-method="" destroy-method="">
 *      <property name="" value=""/ref=""/>
 * </bean>
 *
 * 注解的类型：
 *      用于创建对象的注解(类似于xml中创建的bean标签)
 *          @ Component 把当前对象存入spring容器中
 *                      value：用于指定bean的id，当不写时，默认值为当前类名，首字母小写
 *          @ Controller：表现层
 *          @ Service：业务层
 *          @ Repository：持久层
 *              以上三个注解作用和属性与Component一致，
 *              但是它是spring框架为了我们明确三层架构而使用的
 *
 *      用于注入数据的注解(bean标签中的property标签)
 *          @ Autowired
 *              自动按照数据类型注入，只要容器中有唯一的一个bean对象（被注册的）类型和要注入的变量类型匹配，就可以注入成功
 *              使用注解注入时，set方法就不是必须的了
 *              如果注入的bean对象不唯一，那么将按照变量名来匹配被注册bean的id
 *
 *          @ Qualifier
 *              在按照类中注入的基础之上再按照名称注入，但是在给类成员变量注入时不能单独使用，给方法参数注入时可以单独使用
 *
 *          @ Resource(name = "accountMapper2")
 *              综合了上面两个注解的功能，name直接指向注解的id
 *
 *          以上三个注解只能注入其它bean类型的数据，集合类型的注入只能通过xml实现
 *
 *          基本数据类型和String类型的注入：
 *          @ Value(value = "30")
 *              value：用于注入指定数据的值，可以使用spring中的EL表达式（SpEL）
 *                      SpEL表达式：${表达式}
 *
 *      用于改变作用范围的注解(scope属性)
 *          @ Scope
 *              value：常用取值singleton(默认，单例模式)/prototype(多例模式)
 *
 *      和生命周期相关的注解(bean标签中的init-method和destroy-method)
 *          @ PreDestroy
 *              指定销毁方法
 *          @ PostConstruct
 *              指定初始化方法
 */
@Service(value = "accountService")
@Scope(value = "singleton")
public class AccountServiceImpl implements AccountService {
//    @Autowired
//    @Qualifier("accountMapper2")
    @Resource(name = "accountMapper2")
    private AccountMapper accountMapper;
    @Value(value = "Tom")
    private String name;
    @Value(value = "30")
    private int age;

    public void saveAccount() {
        accountMapper.saveAccount();
        System.out.println(name + " " + age);
    }

    @PostConstruct
    private void init() {
        System.out.println("对象初始化...");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("对象销毁...");
    }
}
