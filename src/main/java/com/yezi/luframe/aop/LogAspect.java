package com.yezi.luframe.aop;

import com.alibaba.fastjson.JSONObject;
import com.yezi.luframe.annotation.RequireLog;
import com.yezi.luframe.constant.Constants;
import com.yezi.luframe.mongodb.AdminUserOperateLog;
import com.yezi.luframe.mongodb.service.AdminUserOperateLogService;
import com.yezi.luframe.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

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

    @Autowired
    AdminUserOperateLogService logService;

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
        //todo  接入mongodb，日志存入mongodb
        AdminUserOperateLog operateLog = new AdminUserOperateLog();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        operateLog.setResult(result.toString());
        operateLog.setRequestUri(httpServletRequest.getRequestURI());
        operateLog.setRequestMethod(httpServletRequest.getMethod());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        //获取方法入参
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        String params = "";
        if (args != null && args.size() > 0) {
            //1.处理request中入参，但获取不到注解的bean
            Enumeration enumeration = httpServletRequest.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String name = (String) enumeration.nextElement();
                String value = httpServletRequest.getParameter(name);
                params += name + "=" + value + ",";
            }
            params += ";";
            //2.处理注解的bean
            for (int i = 0; i < args.size(); i++) {
                params += JSONObject.toJSONString(args.get(i) + ";");
            }
        }
        operateLog.setParams(params);
        operateLog.setLoginIp(IpUtil.getIpAddr(httpServletRequest));
        Long userId = (Long) httpServletRequest.getAttribute(Constants.ADMIN_USER_ID);
        operateLog.setUserId(userId);
        logService.addAdminUserOperateLog(operateLog);
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
