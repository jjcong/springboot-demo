package com.jincong.springboot.config;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * 自定义Mybatis插件（拦截器）
 *  type-需要拦截的对象class
 *  method-拦截的对象中的方法
 *  args-方法的参数类型
 *
 * @author  j_cong
 * @date    2020/06/13
 * @version V1.0
 */

@Intercepts(
        @Signature(type = StatementHandler.class, method = "prepare", args = {
                Connection.class, Integer.class
        })
)
@Component
public class MybatisInterceptor implements Interceptor {

    /**
     * SQL执行时间阈值
     */
    private long time;
    private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        long startTime = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long endTime = System.currentTimeMillis();

        if ((endTime - startTime) > time) {
            //logger.info("慢查询，SQl为： {}", sql);
        }
        logger.info("本次数据库查询操作，SQl为： {}", sql);
        logger.info("本次数据库查询操作，共消耗【{}】ms",endTime- startTime);

        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("设置拦截器的属性");
        this.time = Long.parseLong(properties.getProperty("time"));
    }
}
