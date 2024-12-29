package top.heyqing.heyaudio.aspect;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import top.heyqing.heyaudio.annotation.OptLog;
import top.heyqing.heyaudio.util.IpUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * ClassName:OperationLogAspect
 * Package:top.heyqing.heyaudio.aspect
 * Description:
 * OptLog日志打印
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class OperationLogAspect {

    private final ApplicationContext applicationContext;
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";

    @Pointcut("@annotation(top.heyqing.heyaudio.annotation.OptLog)")
    public void operationLogPointCut() {
    }

    //    @AfterReturning(value = "operationLogPointCut()", returning = "keys")
//    @SuppressWarnings("unchecked")
//    public void saveOperationLog(JoinPoint joinPoint, Object keys) {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        OperationLog operationLog = new OperationLog();
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
//        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
//        OptLog optLog = method.getAnnotation(OptLog.class);
//        operationLog.setOptModule(api.tags()[0]);
//        operationLog.setOptType(optLog.optType());
//        operationLog.setOptDesc(apiOperation.value());
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = method.getName();
//        methodName = className + "." + methodName;
//        operationLog.setRequestMethod(Objects.requireNonNull(request).getMethod());
//        operationLog.setOptMethod(methodName);
//        if (joinPoint.getArgs().length > 0) {
//            if (joinPoint.getArgs()[0] instanceof MultipartFile) {
//                operationLog.setRequestParam("file");
//            } else {
//                operationLog.setRequestParam(JSON.toJSONString(joinPoint.getArgs()));
//            }
//        }
//        operationLog.setResponseData(JSON.toJSONString(keys));
//        String ipAddress = IpUtil.getIpAddress(request);
//        operationLog.setIpAddress(ipAddress);
//        operationLog.setIpSource(IpUtil.getIpSource(ipAddress));
//        operationLog.setOptUri(request.getRequestURI());
//        applicationContext.publishEvent(new OperationLogEvent(operationLog));
//    }
    @AfterReturning(value = "operationLogPointCut()", returning = "keys")
    @SuppressWarnings("unchecked")
    public void saveOperationLog(JoinPoint joinPoint, Object keys) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        // 获取请求信息
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);

        // 获取方法签名和注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        OptLog optLog = method.getAnnotation(OptLog.class);

        // 构建日志内容
        String optModule = api.tags()[0];
        String optType = optLog.optType();
        String optDesc = apiOperation.value();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        methodName = className + "." + methodName;
        String requestMethod = Objects.requireNonNull(request).getMethod();
        String requestParam = (joinPoint.getArgs().length > 0) ?
                (joinPoint.getArgs()[0] instanceof MultipartFile ? "file" : JSON.toJSONString(joinPoint.getArgs())) : "";
        String responseData = JSON.toJSONString(keys);
        String ipAddress = IpUtil.getIpAddress(request);
        String ipSource = IpUtil.getIpSource(ipAddress);
        String optUri = request.getRequestURI();

        // 打印日志
        logger.info(BLUE + "操作模块: {}" + RESET, optModule);
        logger.info(BLUE + "操作类型: {}" + RESET, optType);
        logger.info(BLUE + "操作描述: {}" + RESET, optDesc);
        logger.info(BLUE + "请求方法: {}" + RESET, requestMethod);
        logger.info(BLUE + "请求URI: {}" + RESET, optUri);
        logger.info(BLUE + "类名.方法名: {}" + RESET, methodName);
        logger.info(BLUE + "请求参数: {}" + RESET, requestParam);
        logger.info(BLUE + "响应数据: {}" + RESET, responseData);
        logger.info(BLUE + "IP地址: {}" + RESET, ipAddress);
        logger.info(BLUE + "IP来源: {}" + RESET, ipSource);
    }

}

