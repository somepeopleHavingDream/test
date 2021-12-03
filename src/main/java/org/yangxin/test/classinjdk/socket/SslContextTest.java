package org.yangxin.test.classinjdk.socket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * @author yangxin
 * 2021/12/3 10:40
 */
public class SslContextTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        test1();
    }

    private static void test1() throws NoSuchAlgorithmException, KeyManagementException {
        // X509信任管理器
        X509TrustManager manager = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        // 获取一个SSLContext实例
        SSLContext context = SSLContext.getInstance("SSL");
        // 初始化SSLContext实例
        context.init(null, new TrustManager[]{manager}, new SecureRandom());
        // 获取SSLContext实例相关的SSLEngine
        SSLEngine engine = context.createSSLEngine();

        // 打印SSLContext实例使用的协议
        System.out.println("缺省安全套接字使用的协议：" + context.getProtocol());
        System.out.println("支持的协议：" + Arrays.toString(engine.getSupportedProtocols()));
        System.out.println("启用的协议：" + Arrays.toString(engine.getEnabledProtocols()));
        System.out.println("支持的加密套件：" + Arrays.toString(engine.getSupportedCipherSuites()));
        System.out.println("启用的加密套件：" + Arrays.toString(engine.getEnabledCipherSuites()));
    }
}
