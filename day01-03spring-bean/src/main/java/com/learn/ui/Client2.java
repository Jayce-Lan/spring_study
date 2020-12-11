package com.learn.ui;

import com.learn.service.AccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Client2 {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        AccountService as = (AccountService) factory.getBean("accountService");
        System.out.println(as);
    }
}
