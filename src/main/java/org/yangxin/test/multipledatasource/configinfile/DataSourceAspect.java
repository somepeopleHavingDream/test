package org.yangxin.test.multipledatasource.configinfile;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yangxin
 * 2021/3/22 9:40
 */
@Aspect
@Component
@Slf4j
public class DataSourceAspect implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * 切点：所有配置DataSource注解的方法
     */
    @Pointcut("@annotation(org.yangxin.test.multipledatasource.configinfile.DataSource)")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);

        // 通过判断DataSourceAnn中的值来判断当前方法应用哪个数据源
        DynamicDataSource.setDataSource(dataSource.value());
        log.info("Aop切换数据源成功，数据源为：[{}]", dataSource.value());

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            log.info("clean datasource");
        }
    }
}
