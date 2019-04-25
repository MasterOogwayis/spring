package com.zsw.mybatis.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @author ZhangShaowei on 2019/4/25 17:17
 **/
@Slf4j
@Intercepts({@Signature(
        type= Executor.class,
        method = "get",
        args = {MappedStatement.class,Object.class})})
public class CustomerInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("invocation={}", invocation);
        return invocation;
    }

    @Override
    public Object plugin(Object target) {
        log.debug("target={}", target);
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        log.debug("properties={}", properties);
    }
}
