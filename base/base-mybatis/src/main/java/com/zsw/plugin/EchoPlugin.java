package com.zsw.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.Statement;

/**
 * @author Shaowei Zhang on 2022/3/11 22:32
 */
@Component
@Slf4j
@Intercepts(
        {
                @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class EchoPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long timer = System.currentTimeMillis();
        Object obj = invocation.proceed();
        log.info("call method: {}, cost {}ms", invocation.getMethod().getName(), (System.currentTimeMillis() - timer));
        return obj;
    }
}
