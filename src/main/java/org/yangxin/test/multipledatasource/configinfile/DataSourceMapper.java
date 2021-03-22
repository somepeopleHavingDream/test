package org.yangxin.test.multipledatasource.configinfile;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangxin
 * 2021/3/22 10:24
 */
@Mapper
public interface DataSourceMapper {

    /**
     * 通过主键从数据库中获取一条记录
     *
     * @param id 主键Id
     * @return 该主键唯一标识的一条记录
     */
    Object selectByPrimaryKey(Integer id);
}
