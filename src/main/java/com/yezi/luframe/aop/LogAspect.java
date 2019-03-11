package com.yezi.luframe.aop;

import com.yezi.luframe.annotation.RequireLog;
import com.yezi.luframe.vo.JsonResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 记录日志切面
 *
 * @author yezi
 * @date 2019/3/4 10:55
 */
@Aspect
@Component
public class LogAspect {

    private static final String POINT = "execution (* com.yezi.luframe.controller..*.*(..))";

    @Pointcut(POINT)
    public void performance() {

    }

//    @Before(value = "performance() && @annotation(requireLog)", argNames = "joinPoint,requireLog")
//    public void before(JoinPoint joinPoint, RequireLog requireLog) {
//        System.out.println("[JAspect] before advise");
//    }
//
//    @Around(value = "performance() && @annotation(requireLog)")
//    public void around(ProceedingJoinPoint pjp, RequireLog requireLog) throws Throwable {
//        System.out.println("[JAspect] around advise 1");
//        pjp.proceed();
//        System.out.println("[JAspect] around advise2");
//    }

    @AfterReturning(value = "performance() && @annotation(requireLog)", argNames = "joinPoint,requireLog,result", returning = "result")
    public void afterReturning(JoinPoint joinPoint, RequireLog requireLog, Object result) {

        JsonResult data = (JsonResult) result;
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println(className + "-------" + methodName);
        System.out.println(data.toString());
        System.out.println("[JAspect] afterReturning advise");
    }

//    @AfterThrowing(value = "performance() && @annotation(requireLog)")
//    public void afterThrowing(JoinPoint joinPoint, RequireLog requireLog) {
//        System.out.println("[JAspect] afterThrowing advise");
//    }

//    @After(value = "performance() && @annotation(requireLog)")
//    public void after(JoinPoint joinPoint, RequireLog requireLog) {
//        System.out.println("[JAspect] after advise");
//    }

}
