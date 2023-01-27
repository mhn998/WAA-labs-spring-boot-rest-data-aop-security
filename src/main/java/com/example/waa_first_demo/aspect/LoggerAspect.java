package com.example.waa_first_demo.aspect;


import com.example.waa_first_demo.aspect.model.Exception;
import com.example.waa_first_demo.aspect.model.Logger;
import com.example.waa_first_demo.repo.ExceptionRepo;
import com.example.waa_first_demo.repo.LoggerRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component
@AllArgsConstructor
public class LoggerAspect {

    LoggerRepo loggerRepo;
    ExceptionRepo exceptionRepo;

    private final String apiPointCut = "execution(* com.example.waa_first_demo.controllers.*.*(..))";

    @Pointcut(apiPointCut)
    public void loggerAspect() {}

//    @Pointcut(apiPointCut)
//    public void exceptionAspect() {}

    @Around(value = "loggerAspect()")
    public Object persistLogger(ProceedingJoinPoint joinPoint) throws Throwable {

        Map<String, Object> requestAttributes = getRequestAttributes(joinPoint);

        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long totalTime = System.currentTimeMillis()-startTime;

        Logger logger = new Logger(LocalDateTime.now(), totalTime, (long)requestAttributes.get("userId"), (String)requestAttributes.get("methodName"));

        loggerRepo.save(logger);

        return returnValue;
    }


    @AfterThrowing(value = "loggerAspect()")
    public void persistException(JoinPoint joinPoint) {
        Map<String, Object> requestAttributes = getRequestAttributes(joinPoint);

        Exception exception = new Exception(LocalDate.now(), LocalTime.now(), (long)requestAttributes.get("userId"), (String)requestAttributes.get("methodName"));

        exceptionRepo.save(exception);

    }


    private static Map<String, Object> getRequestAttributes(JoinPoint joinPoint) {

        HashMap<String, Object> map = new HashMap<>();
        long userId = 0;

        final Pattern p = Pattern.compile("(?<=(id|userId)=)\\d+");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        String attr = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE).toString();

        Matcher m = p.matcher(attr);
        if (m.find()) {
            userId = Long.parseLong(m.group(0));
        }

        map.put("userId", userId);
        map.put("methodName", joinPoint.getSignature().getName());

        return map;
    }


}
