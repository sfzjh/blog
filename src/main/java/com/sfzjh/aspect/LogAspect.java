package com.sfzjh.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面类
 * @Author  孙飞
 * @Date  2021年03月09日 11:58
 * @PackageName  com.sfzjh.aspect
 * @Name  LogAspect
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 切点
     * @author  孙飞
     * @date  2021年03月09日 13:17
     * @return  void
     */
    @Pointcut("execution(* com.sfzjh.web.*.*(..))")
    public void log(){

    }
    /**
     * 在方法执行之前记录相关日志
     * @author  孙飞
     * @date  2021年03月09日 13:17
     * @param joinPoint 切入点
     * @return  void
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}",requestLog);
    }
    /**
     * 方法执行之后
     * @author  孙飞
     * @date  2021年03月09日 13:18
     * @return  void
     */
    @After("log()")
    public void doAfter(){
        //logger.info("-----doAfter-------");
    }

    /**
     * 方法
     * @author  孙飞
     * @date  2021年03月09日 13:19
     * @param result 方法执行时
     * @return  void
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Return : {}", result);
    }


    public static class RequestLog{
        private final String url;
        private final String ip;
        private final String classMethod;
        private final Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
