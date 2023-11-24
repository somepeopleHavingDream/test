package org.yangxin.test.objconvert.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author yangxin
 * 2022/3/7 14:33
 */
@SuppressWarnings({"AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "unused"})
@Mapper(componentModel = "spring")
public interface UserConvertBasic {

    UserConvertBasic INSTANCE = Mappers.getMapper(UserConvertBasic.class);

    /**
     * 字段类型数量相同，利用工具BeanUtils也可以实现类似效果
     *
     * @param source 源
     * @return 目标实例
     */
    UserVO1 toConvertVo1(User source);

    User fromConvertEntity1(UserVO1 userVo1);

    UserVO2 toConvertVo2(User source);

    @Mappings({@Mapping(target = "createTime",
            expression = "java(org.yangxin.test.mapstruct.DateTransform.strToDate(source.getCreateTime()))")})
    UserVO3 toConvertVo3(User source);

    @Mappings({@Mapping(source = "id", target = "userId"),
            @Mapping(source = "name", target = "userName")})
    UserVO4 toConvertVo4(User source);

    User fromConvertEntity(UserVO4 userVo4);

    @Mapping(source = "userTypeEnum", target = "type")
    UserVO5 toConvertVo5(UserEnum source);

    UserEnum fromConvertEntity5(UserVO5 userVo5);
}
