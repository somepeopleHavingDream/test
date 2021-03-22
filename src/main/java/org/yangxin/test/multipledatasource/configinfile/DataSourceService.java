package org.yangxin.test.multipledatasource.configinfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangxin
 * 2021/3/22 10:22
 */
@Service
public class DataSourceService {

    private final DataSourceMapper dataSourceMapper;

    @Autowired
    public DataSourceService(DataSourceMapper dataSourceMapper) {
        this.dataSourceMapper = dataSourceMapper;
    }

    public Object getMaster(Integer id) {
        return dataSourceMapper.selectByPrimaryKey(id);
    }

    @DataSource(DataSourceName.TWO)
    public Object testCluster(Integer id) {
        return dataSourceMapper.selectByPrimaryKey(id);
    }
}
