package club.lazy.common.http.service;

import club.lazy.common.contant.CommonContants;
import club.lazy.common.contant.ErrorCode;
import club.lazy.common.util.req.ExceptionUtils;
import club.lazy.common.util.req.SpringBeanUtils;
import club.lazy.common.http.annotation.MethodArg;
import club.lazy.common.http.annotation.MethodInfo;
import club.lazy.common.http.dao.JsonRpcRequest;
import club.lazy.common.http.dao.JsonRpcResponse;
import com.google.common.collect.Maps;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Service
public class RpcHttpService {

    private static Logger logger = LoggerFactory.getLogger(RpcHttpService.class);

    /**
     * cache all apis
     */
    private Map<String, Method> methodCache;

    {
        methodCache = Maps.newConcurrentMap();
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(CommonContants.PACKAGE))
                        .setScanners(new MethodAnnotationsScanner()));
        Set<Method> apis = reflections.getMethodsAnnotatedWith(MethodInfo.class);
        apis.forEach(m -> methodCache.put(m.getName(), m));
    }

    public JsonRpcResponse<?> rpc(JsonRpcRequest req) {
        if (req == null || req.getMethod() == null || req.getParams() == null) {
            return new JsonRpcResponse<>(ErrorCode.METHOD_NOT_FOUND, CommonContants.METHOD_NULL);
        }
        try {
            String methodName = req.getMethod();
            Method method = methodCache.get(methodName);
            if (Objects.isNull(method)) {
                return new JsonRpcResponse<>(ErrorCode.METHOD_NOT_FOUND, CommonContants.METHOD_NOT_FOUND);
            }
            Map<String, Object> params = req.getParams();
            List<Object> objects = new ArrayList<>();
            for (Parameter parameter : method.getParameters()) {
                if (params.containsKey(parameter.getName())) {
                    objects.add(params.get(parameter.getName()));
                } else {
                    MethodArg[] args = parameter.getDeclaredAnnotationsByType(MethodArg.class);
                    if (args[0].required()) {
                        return new JsonRpcResponse<>(ErrorCode.MISSING_PARAM, CommonContants.MISSING_PARAM + parameter.getName());
                    }
                }
            }
            Object result = method.invoke(SpringBeanUtils.getBean(method.getDeclaringClass()), objects.toArray());
            return new JsonRpcResponse<>(result);

        } catch (Exception e) {
            String msg;
            if (e instanceof InvocationTargetException) {
                msg = ExceptionUtils.getTargetException(e).getMessage();
            } else if (e.getCause() != null) {
                msg = e.getCause().getMessage();
            } else {
                msg = e.getMessage();
            }
            return new JsonRpcResponse<>(ErrorCode.RUNTIME_ERROR, msg);
        }
    }
}
