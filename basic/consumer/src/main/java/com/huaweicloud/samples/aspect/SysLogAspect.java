package com.huaweicloud.samples.aspect;


import com.alibaba.fastjson.JSONObject;
import com.huaweicloud.samples.config.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Resource
    private ServerConfig serverConfig;


    @Pointcut(value = "execution(* com.huaweicloud.samples.service..*.*(..))")
    public void logPointCut() {
    }

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        try {
            long beginTime = System.currentTimeMillis();
            result = point.proceed();
            long time = System.currentTimeMillis() - beginTime;
            StringBuffer sb = new StringBuffer("");
            sb.append("url=").append(serverConfig.getUrl());
            sb.append(",serviceName=").append(serverConfig.getServiceName());
            sb.append(",param=" + JSONObject.toJSONString(point.getArgs()));
            sb.append(",rsp=" + JSONObject.toJSONString(result));
            sb.append(",costs=").append(time).append("ms");
            log.info(sb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
