package org.yangxin.test.pay.cornupay.wechatpay;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"DuplicatedCode", "CommentedOutCode", "unused"})
public class HttpUtil
{

//    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String postJson(String url, String request)
    {
//        logger.info(LoggerUtil.log("HTTP request=" + "url=" + url + ",request=" + request));

        Date requestTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//        logger.info(LoggerUtil.log("HTTP request time=" + sdf.format(requestTime)));

        OutputStream oos = null;
        InputStream iis = null;
        String response = null;
        try
        {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.addRequestProperty("Accept", "application/json, text/html");
            httpURLConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setAllowUserInteraction(true);

            oos = httpURLConnection.getOutputStream();
            oos.write(request.getBytes(StandardCharsets.UTF_8));
            oos.flush();

            iis = httpURLConnection.getInputStream();
            response = readInputStream(iis);
        }
        catch (Exception e)
        {
//            logger.error(LoggerUtil.log("HTTP request error=" + e.getMessage()));

            e.printStackTrace();
        }
        finally
        {
            if (oos != null)
            {
                try
                {
                    oos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } // 关闭OutputStream[END]
            if (iis != null)
            {
                try
                {
                    iis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } // 关闭InputStream[END]
        }

        Date responseTime = new Date();
        long interval = responseTime.getTime() - requestTime.getTime();
//        logger.info(LoggerUtil.log("HTTP response time=" + sdf.format(responseTime), "HTTP time=" + interval + "毫秒"));

//        logger.info(LoggerUtil.log("HTTP response=" + response));
        return response;
    }

    public static String post(String url, String request)
    {
//        logger.info(LoggerUtil.log("HTTP request=" + "url=" + url + ",request=" + request));

        Date requestTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//        logger.info(LoggerUtil.log("HTTP request time=" + sdf.format(requestTime)));

        OutputStream oos = null;
        InputStream iis = null;
        String response = null;
        try
        {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.addRequestProperty("Accept", "application/json, text/html");
            httpURLConnection.addRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setAllowUserInteraction(true);

            oos = httpURLConnection.getOutputStream();
            oos.write(request.getBytes(StandardCharsets.UTF_8));
            oos.flush();

            iis = httpURLConnection.getInputStream();
            response = readInputStream(iis);
        }
        catch (Exception e)
        {
//            logger.error(LoggerUtil.log("HTTP request error=" + e.getMessage()));

            e.printStackTrace();
        }
        finally
        {
            if (oos != null)
            {
                try
                {
                    oos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } // 关闭OutputStream[END]
            if (iis != null)
            {
                try
                {
                    iis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } // 关闭InputStream[END]
        }

        Date responseTime = new Date();
        long interval = responseTime.getTime() - requestTime.getTime();
//        logger.info(LoggerUtil.log("HTTP response time=" + sdf.format(responseTime), "HTTP time=" + interval + "毫秒"));

//        logger.info(LoggerUtil.log("HTTP response=" + response));
        return response;
    }

    public static String get(String url, String request)
    {
//        logger.info(LoggerUtil.log("HTTP request=" + "url=" + url + ",request=" + request));

        InputStream iis = null;
        String response = null;
        try
        {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url + "?" + request).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setAllowUserInteraction(true);

            iis = httpURLConnection.getInputStream();
            response = readInputStream(iis);
        }
        catch (Exception e)
        {
//            logger.error(LoggerUtil.log("HTTP request error=" + e.getLocalizedMessage()));

            e.printStackTrace();
        }
        finally
        {
            if (iis != null)
            {
                try
                {
                    iis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            } // 关闭InputStream[END]
        }

//        logger.info(LoggerUtil.log("HTTP response=" + response));
        return response;
    }

    private static String readInputStream(InputStream inputStream)
            throws IOException
    {
        if (inputStream == null)
        {
            return null;
        }

        String str;
        int length;
        byte[] bytes = new byte[1024];
        StringBuilder buffer = new StringBuilder();
        while ((length = inputStream.read(bytes, 0, bytes.length)) != -1)
        {
            String read = new String(bytes, 0, length, "ISO_8859_1");
            buffer.append(read);
        }
        byte[] allBytes = buffer.toString().getBytes("ISO_8859_1");
        str = new String(allBytes, StandardCharsets.UTF_8);
        return str;
    }

}
