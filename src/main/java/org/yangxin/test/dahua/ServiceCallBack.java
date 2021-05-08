package org.yangxin.test.dahua;

import com.sun.jna.Pointer;
import org.yangxin.test.dahua.demo.module.ApiModule;
import org.yangxin.test.dahua.demo.module.LoginModule;
import org.yangxin.test.dahua.lib.NetSDKLib;

import java.io.UnsupportedEncodingException;

/**
 * @author yangxin
 * 2021/4/26 10:46
 */
@SuppressWarnings("ALL")
public class ServiceCallBack implements NetSDKLib.fServiceCallBack {

    private ServiceCallBack() {
        System.out.println("监听回调函数初始化");
    }

    private static class CallBackHolder {

        private static final ServiceCallBack instance = new ServiceCallBack();
    }

    public static ServiceCallBack getInstance() {
        return CallBackHolder.instance;
    }

    @Override
    public int invoke(NetSDKLib.LLong lHandle, String pIp, int wPort, int lCommand, Pointer pParam, int dwParamLen, Pointer dwUserData) {
        System.out.println("进来了");
        System.out.println("服务监听回调：login: " + lHandle);
        System.out.println("ip: " + pIp);
        System.out.println("port: " + wPort);
        System.out.println("lCommand: " + lCommand);

        // 将pParam转化为序列号
        byte[] buffer = new byte[dwParamLen];
        pParam.read(0, buffer, 0, dwParamLen);
        String deviceId = "";

        try {
            deviceId = new String(buffer, "UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.printf("Register Device Info [Device address %s][port %s][DeviceID %s] \n", pIp, wPort, deviceId);

        if (ApiModule.m_hLoginHandle.longValue() == 0) {
            System.out.println("当前线程Id：" + Thread.currentThread().getId());
            System.out.println("当前线程名：" + Thread.currentThread().getName());

            // 登录
            System.out.println("登录操作");
//            new Thread(() -> LoginModule.login(pIp, wPort, ))
        }
        return 0;
    }
}
