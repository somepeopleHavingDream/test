package org.yangxin.test.modeltransfer;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单数据转换对象（服务层->Web层、开放接口层）
 *
 * @author yangxin
 * 2019/10/15 10:03
 */
@Builder
class OrderDTO {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 下单日期
     */
    private Date orderDate;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

    /**
     * 支付方式
     */
    private PaymentType paymentType;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 商铺信息
     */
    private ShopDTO shopInfo;

    /**
     * 用户信息
     */
    private UserDTO userInfo;

    /**
     * 订单商品明细集合
     */
    private List<ProductDTO> orderedProductList;
}
