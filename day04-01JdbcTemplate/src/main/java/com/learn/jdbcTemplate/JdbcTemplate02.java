package com.learn.jdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

//使用IOC装配jdbctemplate
public class JdbcTemplate02 {
    public static void main(String[] args) {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        String sql = "insert into account (name, money) values ('刘华', 5000)";
        jdbcTemplate.execute(sql);
    }
}
