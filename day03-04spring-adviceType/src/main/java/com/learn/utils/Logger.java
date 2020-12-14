package com.learn.utils;

import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

//用于记录日志的工具类，提供了公共方法
public class Logger {
    /**
     * 打印日志：让其在切入点之前执行（切入点方法即为业务层方法）
     */
    public void beforPrintLog() {
        System.out.println("Logger类中的beforPrintLog方法开始记录日志");
    }

    /**
     * 后置通知
     */
    public void afterPrintLog() {
        System.out.println("Logger类中的afterPrintLog方法开始记录日志");
    }

    public void throwLog() {
        System.out.println("异常通知");
    }

    public void endAdvice() {
        System.out.println("最终通知");
    }

    public Object surround(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();  //获取方法所需参数

            System.out.println("前置通知");

            rtValue = pjp.proceed(args);  //调用业务层方法

            System.out.println("后置通知");

            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("错误通知");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("最终通知");
        }
    }
}
