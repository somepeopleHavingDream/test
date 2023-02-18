package org.yangxin.diy.redission;

import lombok.Data;

import java.util.Objects;

/**
 * @author yangxin
 * 2023/2/18 1:37
 */
@Data
public class Config {

    private SingleServerConfig singleServerConfig;

    public SingleServerConfig useSingleServer() {
        return useSingleServer(new SingleServerConfig());
    }

    public SingleServerConfig useSingleServer(SingleServerConfig singleServerConfig) {
        if (Objects.isNull(this.singleServerConfig)) {
            this.singleServerConfig = singleServerConfig;
        }
        return this.singleServerConfig;
    }
}
