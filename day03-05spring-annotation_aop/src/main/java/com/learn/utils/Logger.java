package com.learn.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//用于记录日志的工具类，提供了公共方法
@Component("logger")
@Aspect //表示当前类是一个切面类
public class Logger {

    //切入点
    @Pointcut("execution(* com.learn.service.impl.*.*(..))")
    private void pt1() {}

    /**
     * 打印日志：让其在切入点之前执行（切入点方法即为业务层方法）
     */
//    @Before("pt1()")
    public void beforPrintLog() {
        System.out.println("Logger类中的beforPrintLog方法开始记录日志");
    }

    /**
     * 后置通知
     */
//    @AfterReturning("pt1()")
    public void afterPrintLog() {
        System.out.println("Logger类中的afterPrintLog方法开始记录日志");
    }

//    @AfterThrowing("pt1()")
    public void throwLog() {
        System.out.println("异常通知");
    }

//    @After("pt1()")
    public void endAdvice() {
        System.out.println("最终通知");
    }

    @Around("pt1()")
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
