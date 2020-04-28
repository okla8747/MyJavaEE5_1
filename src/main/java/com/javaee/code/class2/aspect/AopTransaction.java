package com.javaee.code.class2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AopTransaction {

    @Pointcut("execution(* com.javaee.code.class2.jdbc.*JDBC.*(..))")
    public void service (){}

    @Before("service()")
    public void beforeService(){
        System.out.println("before");
    }
    @Around("service()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint){
        Object result= null;
        try {
            System.out.println("操作开始");
            result = proceedingJoinPoint.proceed();
            System.out.println("操作已完成");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
    @After("service()")
    public void afterService(){
        System.out.println("after");
    }
//    @AfterThrowing("service()")
//    public void afterThrowService(){
//        try {
//            databasePool.getHikariDataSourse().getConnection().rollback();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
