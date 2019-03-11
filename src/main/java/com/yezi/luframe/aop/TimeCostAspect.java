package com.yezi.luframe.aop;

import com.yezi.luframe.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口耗时计算切面
 *
 * @author yezi
 * @date 2019/3/4 10:55
 */
@Slf4j
@Aspect
@Component
public class TimeCostAspect {

    private static final String POINT = "execution (* com.yezi.luframe.controller..*.*(..))";

    @Pointcut(POINT)
    public void performance() {
    }

    @Around("performance()")
    public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        obj = joinPoint.proceed(args);
        if (!(obj instanceof JsonResult)) {
            return obj;
        }
        long endTime = System.currentTimeMillis();
        JsonResult jsonResult = (JsonResult) obj;
        long cost = endTime - startTime;
        jsonResult.setCost(cost);
        if (cost >= 100L) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            log.warn("Request:{} cost:{}ms is too long!", request.getRequestURI(), cost);
        }
        return jsonResult;
    }
}
