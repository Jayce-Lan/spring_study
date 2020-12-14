package com.learn.utils;

//用于记录日志的工具类，提供了公共方法
public class Logger {
    /**
     * 打印日志：让其在切入点之前执行（切入点方法即为业务层方法）
     */
    public void printLog() {
        System.out.println("Logger类中的printLog方法开始记录日志");
    }
}
