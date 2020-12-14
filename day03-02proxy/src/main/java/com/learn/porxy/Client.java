package com.learn.porxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：
 *      特点：字节码随用随创建，随用随加载
 *      作用：不修改源码的基础上对方法增强
 *      分类：
 *          基于接口的动态代理
 *          基于子类的动态代理
 */

//消费者
public class Client {
    public static void main(String[] args) {
        final Produer produer = new Produer();

        /*
        基于接口实现代理：
            缺点：如果没有接口，那么将无法生成代理
            ClassLoader 类加载器
            Class[] 字节码数组

         */
        IProuder iProuder = (IProuder) Proxy.newProxyInstance(produer.getClass().getClassLoader(),
                produer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何接口方法都会经过该方法，因此可以在此提供增强代码
                     * @param proxy 代理对象的引用
                     * @param method 当前执行的方法
                     * @param args 当前执行方法所需的参数
                     * @return 和被代理对象方法会有相同的返回值
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强代码
                        Object object = null;
                        Float money = (Float) args[0];
                        if ("saleProduer".equals(method.getName())) {
                            object = method.invoke(produer, money * .8f);
                        }
                        return object;
                    }
                });
        iProuder.saleProduer(10000);
    }
}
