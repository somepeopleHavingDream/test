package org.yangxin.test.multipledatasource.configinfile;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2021/3/22 10:08
 */
@Configuration
@Slf4j
public class DynamicDataSourceConfig {

    /*
        创建DataSource Bean
     */

    @Bean
    @ConfigurationProperties("spring.datasource.one")
    public DataSource oneDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.two")
    public DataSource twoDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /*
        将数据源信息载入targetDataSources
     */

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource oneDataSource, DataSource twoDataSource) {
        Map<Object, Object> targetDataSourceMap = new HashMap<>(2);
        targetDataSourceMap.put(DataSourceName.ONE, oneDataSource);
        targetDataSourceMap.put(DataSourceName.TWO, twoDataSource);

        // 如果还有其他数据源，可以按照数据源one和two这种方法去进行配置，然后再targetDataSourceMap中继续添加
        log.info("加载的数据源DataSourceMap: [{}]", targetDataSourceMap);

        // DynamicDataSource（默认数据源，所有数据源）第一个指定默认数据库
        return new DynamicDataSource(oneDataSource, targetDataSourceMap);
    }
}
