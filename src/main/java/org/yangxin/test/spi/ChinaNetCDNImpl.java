package org.yangxin.test.spi;

/**
 * 上传网宿cdn
 *
 * @author yangxin
 * 2020/10/27 17:23
 */
public class ChinaNetCDNImpl implements UploadCDN {

    @Override
    public void upload(String url) {
        System.out.println("upload to chinaNet cdn");
    }
}
