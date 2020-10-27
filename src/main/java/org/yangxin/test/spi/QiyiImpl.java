package org.yangxin.test.spi;

/**
 * 上传爱奇艺cdn
 *
 * @author yangxin
 * 2020/10/27 17:22
 */
public class QiyiImpl implements UploadCDN {

    @Override
    public void upload(String url) {
        System.out.println("upload to qiyi cdn");
    }
}
