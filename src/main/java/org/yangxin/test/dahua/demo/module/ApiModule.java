package org.yangxin.test.dahua.demo.module;

import org.yangxin.test.dahua.ServiceCallBack;
import org.yangxin.test.dahua.lib.NetSDKLib;
import org.yangxin.test.dahua.lib.ToolKits;

/**
 * @author yangxin
 * 2021/4/26 10:44
 */
public class ApiModule {

    public static NetSDKLib netSDKLib = NetSDKLib.NETSDK_INSTANCE;

    /**
     * 设备信息
     */
    public static NetSDKLib.NET_DEVICEINFO_Ex m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();

    /**
     * 登录句柄
     */
    public static NetSDKLib.LLong m_hLoginHandle = new NetSDKLib.LLong(0);
    private static NetSDKLib.LLong mServerHandler = new NetSDKLib.LLong(0);

    /**
     * 开启监听
     */
    public static NetSDKLib.LLong startServer(String ip) {
        mServerHandler = netSDKLib.CLIENT_ListenServer(ip, 9500, 1000, ServiceCallBack.getInstance(), null);
        if (0 == mServerHandler.longValue()) {
            System.err.println("Failed to start server." + ToolKits.getErrorCodePrint());
        }else {
            System.out.printf("Start server, [Server address %s][Server port %d]\n", ip, 9500);
        }
        return mServerHandler;
    }
}
