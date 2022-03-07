package org.yangxin.test.mapstruct;

import java.time.LocalDateTime;

/**
 * @author yangxin
 * 2022/3/7 14:44
 */
public class MapStructTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        User user = User.builder()
                .id(1)
                .name("张三")
                .createTime("2020-04-01 11:05:07")
                .updateTime(LocalDateTime.now())
                .build();

        // 使用mapstruct
        UserVO1 userVo1 = UserConvertBasic.INSTANCE.toConvertVo1(user);

        System.out.println("userVo1: " + UserConvertBasic.INSTANCE.toConvertVo1(user));
        System.out.println("userVo1转换回实体类user: " + UserConvertBasic.INSTANCE.fromConvertEntity1(userVo1));
        System.out.println("userVo2: " + UserConvertBasic.INSTANCE.toConvertVo2(user));
    }
}
