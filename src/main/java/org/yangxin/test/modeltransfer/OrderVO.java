package org.yangxin.test.modeltransfer;

import lombok.Data;

import java.util.List;

/**
 * 订单视图类（控制层->页面）
 *
 * @author yangxin
 * 2019/10/15 09:57
 */
@Data
class OrderVO {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 下单日期
     */
    private String orderDate;

    /**
     * 总金额
     */
    private String totalMoney;

    /**
     * 支付方式
     */
    private String paymentType;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 商铺名称
     */
    private String shopName;

    /**
     * 用户信息
     */
    private String userName;

    /**
     * 订单商品明细集合
     */
    private List<ProductVO> orderedProductList;
}
