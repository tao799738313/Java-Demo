package com.pdt.ssm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by taotao on 2019/7/31.
 */

@Component
@Aspect
public class aop {
    /**
     * Pointcut定义切点函数
     */
    @Pointcut("execution(* com.pdt.ssm.service.*.*(..))")
    private void myPointcut() { }

    @Around("myPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("--->BindingResultAop start...");
        String className = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        System.out.println("before " + className + "." + methodName + "() invoking!");
        // 执行目标方法
        return jp.proceed();
    }
}
