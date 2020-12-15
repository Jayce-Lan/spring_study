package com.learn.utils;


import org.aspectj.lang.ProceedingJoinPoint;

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

    public Object surround(ProceedingJoinPoint joinPoint) {
        Object object = null;
        try {
            Object[] args = joinPoint.getArgs();    //得到方法运行时的参数

            System.out.println("方法执行前通知。。。");

            object = joinPoint.proceed(args);    //明确业务层方法（切入点方法）的调用

            System.out.println("方法执行后通知。。。");

            return object;
        } catch (Throwable throwable) {
            System.out.println("方法执行异常通知。。。");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("最终通知。。。");
        }
    }
}
