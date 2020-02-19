package org.yangxin.test.web;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * 写http请求方法
 *
 * @author yangxin
 * 2020/02/19 18:32
 */
public class HTTPTest {
    /**
     * 处理http请求
     *
     * @param requestURL 请求地址
     * @param requestMethod 请求方式，值为“GET”或”POST“
     * @param output 打印字符串
     *
     * @return 返回结果
     */
    private static String httpRequest(String requestURL, String requestMethod, String output) {
        StringBuffer stringBuffer;

        try {
            URL url = new URL(requestURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.connect();

            // 往服务器端写内容，也就是发起http请求需要带的参数
            if (output != null) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(output.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            }

            // 读取服务器端返回的内容
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            stringBuffer = new StringBuffer();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 处理https GET/POST请求
     */
    private static String httpsRequest(String requestURL, String requestMethod, String output) {
        StringBuffer stringBuffer;
        try {
            // 创建SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] trustManagers = {new MyX509TrustManager()};

            // 初始化
            sslContext.init(null, trustManagers, new java.security.SecureRandom());

            // 获取SSLSocketFactory对象
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            URL url = new URL(requestURL);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setRequestMethod(requestMethod);

            // 设置当前实例使用的SSLSocketFactory
            httpsURLConnection.setSSLSocketFactory(socketFactory);
            httpsURLConnection.connect();

            // 往服务器端写内容，也就是发起http请求需要带的参数
            if (output != null) {
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(output.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            }

            // 读取服务器端返回的内容
            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            stringBuffer = new StringBuffer();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        // http测试
//        String result1 = httpRequest("www.qq.com", "GET", null);
//        String result1 = httpRequest("https://www.qq.com", "GET", null);
//        String result1 = httpRequest("http://www.qq.com", "GET", null);
//        System.out.println(result1);

        // https测试
        String result2 = httpRequest("https://kyfw.12306.cn/", "GET", null);
        System.out.println(result2);
    }
}
