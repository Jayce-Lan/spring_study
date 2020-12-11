package com.learn.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 模拟创建bean工厂模式
 * bean：在计算机中，有可重用组件的含义
 *
 * JavaBean：用Java编写的可重用组件
 *      JavaBean > 实体类
 *      它负责创建service和dao层的对象
 *
 *      1.需要配置文件配置service和dao（配置文件的内容：key-value属性的文件）
 *      2.通过读取配置文件中的配置内容，反射创建bean对象
 *      3.配置文件可以是xml，也可以为properties
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties properties;

    //单例对象，定义一个map容器存放创建出来的对象
    private static Map<String, Object> beans;

    //用静态代码块为Properties对象赋值
    static {
        try {
            //实例化对象
            properties = new Properties();
            //获取Properties文件的流对象
            InputStream inputStream = BeanFactory.class.getClassLoader()
                    .getResourceAsStream("bean.properties");    //这里使用类加载器获取而不使用绝对路径
            properties.load(inputStream);

            //实例化容器
            beans = new HashMap<String, Object>();
            Enumeration keys = properties.keys();
            //遍历枚举
            while (keys.hasMoreElements()) {
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = properties.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key, value);
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError("初始化bean.properites失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例对象
     * 根据bean的名称获取对象
     * @param beanName
     * @return
     */
    public static Object getBeans(String beanName) {
        return beans.get(beanName);
    }

    /**
     * 多例对象方法
     * 根据bean文件内的名称（key）获取bean对象（value）
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = properties.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();   //每次调用都会创建对象
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
