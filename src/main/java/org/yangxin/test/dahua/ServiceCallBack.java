package org.yangxin.test.dahua;

import com.alibaba.fastjson.JSONObject;
import com.sun.jna.Pointer;
import org.yangxin.test.dahua.com.netsdk.lib.NetSDKLib;

/**
 * 监听回调
 *
 * 1 设备上线，服务端接受回调，需要在这个回调里登录设备
 * 2 我这里使用的是DelayQueue延时队列，异步地去处理登录，之所以使用异步，是因为我直接登录的时候会报网络异常，连接失败。
 * 这是因为不能在一个线程里调用多个Sdk，其实在文档里也是这样建议的，虽然我做的是异步，但是还是会报错，具体怎么解决的，我放到下一说明里。
 * 不建议使用这个延时队列来做异步，有时候会失效，并不能消费队列，目前我还没有找到问题所在，建议还是使用消息中间件来做队列吧。
 *
 * @author yangxin
 * 2021/4/20 15:00
 */
public class ServiceCallBack implements NetSDKLib.fServiceCallBack {

    private ServiceCallBack() {
        System.out.println("监听回调函数初始化");
    }

    private static class CallBackHolder {

        private static ServiceCallBack instance = new ServiceCallBack();
    }

    public static ServiceCallBack getInstance() {
        return CallBackHolder.instance;
    }

    @Override
    public int invoke(NetSDKLib.LLong lHandle, String pIp, int wPort, int lCommand, Pointer pParam, int dwParamLen, Pointer dwUserData) {
        System.out.println("进来了");
        System.out.println("服务监听回调：login=" + lHandle + "，ip=" + pIp + "，port=" + wPort);

        // 将pParam转化为序列号
        byte[] buffer = new byte[dwParamLen];
        pParam.read(0, buffer, 0, dwParamLen);
        String deviceId = "";

        try {
            deviceId = new String(buffer, "GBK").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printf("Register Device Info [Device address %s][port %s][DeviceID %s] \n", pIp, wPort, deviceId);
        if (ApiModule.mHLoginHandle.longValue() == 0) {
            System.out.println("当前线程Id: " + Thread.currentThread().getId());

            // 登录
            JSONObject object = new JSONObject();
            object.put("pIp", pIp);
            object.put("wPort", wPort);
            object.put("deviceId", deviceId);
        }
        return 0;
    }
}
