package org.yangxin.test.modeltransfer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商铺DTO
 *
 * @author yangxin
 * 2019/10/15 10:08
 */
@AllArgsConstructor
class ProductDTO {
    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品数量
     */
    private Integer quantity;
}
