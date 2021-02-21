package org.yangxin.test.spi;

/**
 * spi的简单demo
 *
 * @author yangxin
 * 2020/10/27 17:21
 */
public interface UploadCdn {

    /**
     * 上传
     *
     * @param url url
     */
    void upload(String url);
}
