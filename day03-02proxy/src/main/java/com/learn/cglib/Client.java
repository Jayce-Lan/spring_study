package com.learn.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//消费者(基于子类代理)
public class Client {
    public static void main(final String[] args) {
        final Produer produer = new Produer();
        Produer cglib = (Produer) Enhancer.create(produer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的方法都会经过该方法
             * @param proxy 代理对象的引用
             * @param method 当前执行的方法
             * @param args 代理对象的参数
             * @param methodProxy 当前执行方法的代理对像
             * @return
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //提供增强代码
                Object object = null;
                Float money = (Float) args[0];
                if ("saleProduer".equals(method.getName())) {
                    object = method.invoke(produer, money * .8f);
                }
                return object;
            }
        });
        cglib.saleProduer(10000f);
    }
}
