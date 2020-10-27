package org.yangxin.test.spi;

/**
 * @author yangxin
 * 2020/10/27 17:26
 */
public class ServiceLoader {

    public static void main(String[] args) {
        java.util.ServiceLoader<UploadCDN> uploadCDNServiceLoader = java.util.ServiceLoader.load(UploadCDN.class);
        for (UploadCDN uploadCDN : uploadCDNServiceLoader) {
            uploadCDN.upload("filePath");
        }
    }
}
