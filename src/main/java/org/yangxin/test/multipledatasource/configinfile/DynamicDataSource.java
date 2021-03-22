package org.yangxin.test.multipledatasource.configinfile;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author yangxin
 * 2021/3/22 9:54
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSourceMap) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSourceMap);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
