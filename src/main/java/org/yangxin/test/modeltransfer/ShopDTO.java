package org.yangxin.test.modeltransfer;

import lombok.Builder;

/**
 * 商店数据转换对象
 *
 * @author yangxin
 * 2019/10/15 10:06
 */
@Builder
class ShopDTO {
    /**
     * 商店id
     */
    private Long shopId;

    /**
     * 商店名称
     */
    private String shopName;
}
