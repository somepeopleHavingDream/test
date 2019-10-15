package org.yangxin.test.modeltransfer;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 领域模型转换测试类
 *
 * @author yangxin
 * 2019/10/15 10:29
 */
public class ModelTransferTest {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        // 构建orderedProductList
        List<ProductDTO> orderedProductList = Lists.newArrayList(new ProductDTO(BigDecimal.valueOf(799.990000000000009094947017729282379150390625), 1L, "吉他", 1),
                new ProductDTO(BigDecimal.valueOf(30), 2L, "变调夹", 1));

        // 构建orderDTO
        OrderDTO orderDTO = OrderDTO.builder()
                .orderDate(new Date())
                .orderId(201909090001L)
                .orderStatus(OrderStatus.CREATED)
                .orderedProductList(orderedProductList)
                .paymentType(PaymentType.CASH)
                .shopInfo(ShopDTO.builder()
                        .shopId(20000101L)
                        .shopName("慕课商铺")
                        .build())
                .totalMoney(BigDecimal.valueOf(829.990000000000009094947017729282379150390625))
                .userInfo(UserDTO.builder()
                        .userId(20100001L)
                        .userLevel(2147483647L)
                        .userName("张小喜")
                        .build())
                .build();

        String result;

        // 第一种方法，直接set、get，这里不演示
        // 第二种方法，gson，完美转换
        result = gson.toJson(orderDTO);

        // 第三种：Apache工具包PropertyUtils工具类：属性类型不一样时报错、不能部分属性复制、得到的目标对象部门属性成功
        // 第四种：Apache工具包BeanUtils工具类：日期不符合要求、属性名不一样时不复制、目标对象中的商品集合变成了DTO对象，这是因为List的泛型
        // 被擦除了，而且是浅拷贝，所以造成这种现象
        // 第五种：Spring封装BeanUtils工具类：可以忽略部分属性、属性类型不同不能转换，属性名称不同不能转换
        // apache的BeanUtils和Spring的BeanUtils中拷贝方法的原理都是先用jdk中java.beans.Introspector类的getBeanInfo()方法获取对象
        // 的属性信息及属性get/set方法，接着使用反射（Method的invoke(Object obj, Object... args)方法进行赋值
        // 第六种：cglib工具包BeanCopier，比较繁琐复杂

        // 打印转换结果，下面为目标
//        {
//            "orderDate":"2019-10-09 15:49:24.619",
//                "orderStatus":"CREATED",
//                "orderedProducts":[
//            {
//                "productName":"吉他",
//                    "quantity":1
//            },
//            {
//                "productName":"变调夹",
//                    "quantity":1
//            }
//	],
//            "paymentType":"CASH",
//                "shopName":"慕课商铺",
//                "totalMoney":"829.99",
//                "userName":"张小喜"
//        }
        System.out.println(result);
    }
}
