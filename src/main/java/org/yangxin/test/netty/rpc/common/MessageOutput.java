package org.yangxin.test.netty.rpc.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2021/8/10 17:11
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageOutput {

    private String requestId;
    private String type;
    private Object payload;
}
