package com.shu.db.plugins;

import com.shu.db.model.Pojo;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Date;
import java.util.Properties;
/**
 * Created by admin on 2017/1/8.
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = {
        MappedStatement.class, Object.class }) })
public class BasePlugins implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation
                .getArgs()[0];

        String sqlId = mappedStatement.getId();
        System.out.println(sqlId);
        String sqlMethod = sqlId.substring(sqlId.lastIndexOf(".") + 1,
                sqlId.length());
        System.out.println(sqlMethod);
        String methodName = invocation.getMethod().getName();
        System.out.println(methodName);

        if ("update".equals(methodName)) {
            if (invocation.getArgs().length > 1) {
                Object parameter = invocation.getArgs()[1];

                if (parameter instanceof Pojo) {
                    Pojo pojo = (Pojo) parameter;
                    if (sqlMethod.startsWith("insert")) {
                        pojo.setCreatetime(new Date());
                        // pojo.setIsdelete(0);
                        pojo.setUpdatetime(new Date());
                    } else if (sqlMethod.startsWith("update")) {
                        pojo.setUpdatetime(new Date());
                    }
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object obj) {
        // Class c = obj.getClass();
        // Field[] fieldArray = c.getDeclaredFields();
        // for (Field field : fieldArray) {
        // if ("updateTime".equals(field.getName())) {
        // try {
        // field.set(obj, new Date());
        // } catch (IllegalArgumentException e) {
        // e.printStackTrace();
        // } catch (IllegalAccessException e) {
        // e.printStackTrace();
        // }
        // }
        // }
        return Plugin.wrap(obj, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub

    }

}
