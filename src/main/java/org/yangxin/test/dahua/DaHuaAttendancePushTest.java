package org.yangxin.test.dahua;

import org.yangxin.test.dahua.demo.module.ApiModule;
import org.yangxin.test.dahua.demo.module.LoginModule;
import org.yangxin.test.dahua.lib.NetSDKLib;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/4/25 16:36
 */
@SuppressWarnings("ALL")
public class DaHuaAttendancePushTest {

    public static void main(String[] args) {
        // Sdk初始化（入参：断线回调、网络连接恢复回调函数）
        boolean isInit = LoginModule.init(((lLoginID, pchDVRIP, nDVRPort, dwUser) -> {
                    // CLIENT_LoginWithHighLevelSecurity的返回值
                    System.out.println("lLoginId: " + lLoginID);
                    // 断线的设备IP
                    System.out.println("pchDVRIP: " + pchDVRIP);
                    // 断线的设备端口
                    System.out.println("nDVRPort: " + nDVRPort);
                    // 回调函数的用户参数
                    System.out.println("dwUser: " + dwUser);
                }),
                ((lLoginID, pchDVRIP, nDVRPort, dwUser) -> {
                    System.out.println("lLoginId: " + lLoginID);
                    System.out.println("pchDVRIP: " + pchDVRIP);
                    System.out.println("nDVRPort: " + nDVRPort);
                    System.out.println("dwUser: " + dwUser);
                }));
        System.out.println(isInit ? "Sdk初始化成功" : "Sdk初始化失败");

        // 主动注册
        NetSDKLib.LLong lLong = ApiModule.startServer("192.168.5.108");

        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束");
        LoginModule.cleanup();
    }
}
