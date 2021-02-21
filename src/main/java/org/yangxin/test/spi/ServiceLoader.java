package org.yangxin.test.spi;

/**
 * @author yangxin
 * 2020/10/27 17:26
 */
public class ServiceLoader {

    public static void main(String[] args) {
        java.util.ServiceLoader<UploadCdn> uploadCdnServiceLoader = java.util.ServiceLoader.load(UploadCdn.class);
        for (UploadCdn uploadCdn : uploadCdnServiceLoader) {
            uploadCdn.upload("filePath");
        }
    }
}
