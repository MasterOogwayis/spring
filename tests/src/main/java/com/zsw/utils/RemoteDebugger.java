package com.zsw.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.Setter;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 调试工具，可调用项目中的任意方法。
 * <p>
 * 注意：方法重载的参数只提供Json级别的识别。
 *
 * @author zsw
 * @since 2019年6月13日16:36:43
 */
@Setter
@Component
@ManagedResource(description = "mbean 远程调用")
public class RemoteDebugger implements ApplicationContextAware {

    private final Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Autowired
    private ApplicationContext applicationContext;

    private static final Pattern BEAN_METHOD = Pattern.compile("([a-zA-Z0-9_$.]+)\\.([a-zA-Z0-9_$]+)\\((.*)\\)", Pattern.DOTALL);

    /**
     * 根据Bean名称或类全名调用方法, 例如：userServiceImpl.findUserWithRole({limit:2})"
     * 或：zsw.common.utils.TimeUtils.getDaysOfMonth(2018,5) 注意：无法区分重载且参数的Json相互兼容的方法
     *
     * @param expression
     * @return
     */
    @ManagedOperation(description = "方法调用")
    public Object methodExecute(@RequestBody String expression) {
        Assert.hasText(expression, "表达式不能为空！");
        Matcher matcher = BEAN_METHOD.matcher(expression);
        Assert.isTrue(matcher.find(), "表达式格式不正确！");

        // 根据名称获取Bean
        String classOrBeanName = matcher.group(1);
        Object bean = getBean(classOrBeanName);
        Class clazz;
        try {
            clazz = bean == null ? Class.forName(classOrBeanName) : AopProxyUtils.ultimateTargetClass(bean);
        } catch (Exception e) {
            throw new RuntimeException("初始化类失败！", e);
        }
        Assert.notNull(clazz, "调用Class不能为空！");

        // 根据名称获取方法列表
        List<Method> mayMethods = getMethods(clazz, matcher.group(2));

        // 转换方法参数
        JsonArray params = getJsonArray("[" + matcher.group(3) + "]");
        return executeFoundMethod(clazz, bean, mayMethods, params);
    }

    private Object executeFoundMethod(Class<?> clazz, Object bean, List<Method> mayMethods, JsonArray params) {
        // 根据参数锁定方法
        List<Object> args = new ArrayList<>();
        Method foundMethod = null;
        for (Method method : mayMethods) {
            if (!args.isEmpty()) {
                args.clear();
            }
            Type[] types = method.getGenericParameterTypes();
            if (types.length != params.size()) {
                continue;
            }
            // 参数转换，无异常表示匹配
            Iterator<JsonElement> paramIterator = params.iterator();
            try {
                for (Type type : types) {
                    Object arg = this.gson.fromJson(paramIterator.next(), type);
                    args.add(arg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Assert.isNull(foundMethod, "方法调用重复：" + foundMethod + " 和 " + method);
            foundMethod = method;
        }

        // 调用方法并返回
        Assert.notNull(foundMethod, "未找到满足参数的方法！");
        try {
            foundMethod.setAccessible(true);
            if (Modifier.isStatic(foundMethod.getModifiers())) {
                return foundMethod.invoke(clazz, args.toArray());
            } else {
                Object target = bean;
                if (Objects.isNull(target)) {
                    // 尝试从容器中获取 bean
                    target = this.getBean(clazz);
                }
                if (Objects.isNull(target)) {
                    // 自行实例化一个
                    target = clazz.getDeclaredConstructor().newInstance();
                }
                return foundMethod.invoke(target, args.toArray());
            }
        } catch (Exception e) {
            throw new RuntimeException("调用方法失败！", e);
        }
    }

    private JsonArray getJsonArray(String jsonParams) {
        try {
            return this.gson.fromJson(jsonParams, JsonArray.class);
        } catch (Exception e) {
            throw new RuntimeException("参数格式不正确！");
        }
    }

    private List<Method> getMethods(Class<?> clazz, String methodName) {
        List<Method> mayMethods = Stream.of(clazz.getDeclaredMethods())
                .filter(m -> methodName.equals(m.getName()))
                .collect(Collectors.toList());
        Assert.notEmpty(mayMethods, "未找到方法：" + methodName);
        return mayMethods;
    }

    private Object getBean(String classOrBeanName) {
        try {
            return applicationContext.getBean(classOrBeanName);
        } catch (Exception e) {
            return null;
        }
    }

    private Object getBean(Class<?> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            return null;
        }
    }


    @ExceptionHandler(RuntimeException.class)
    public Object exceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return Collections.singletonMap("error", e.getMessage());
    }

}
