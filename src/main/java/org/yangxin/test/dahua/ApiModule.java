package org.yangxin.test.dahua;

import com.sun.jna.Pointer;
import org.yangxin.test.dahua.com.netsdk.lib.NetSDKLib;
import org.yangxin.test.dahua.com.netsdk.lib.ToolKits;

import java.nio.charset.StandardCharsets;

/**
 * 开启监听
 *
 * 1 这里只是个Demo，设置的固定端口
 * 2 需要说明的是，这里开启监听的ip一定要是内网，不能是公网ip，也不能是127.0.0.1或localhost
 * 3 CLIENT_ListenServer设置的回调函数，有设备上线时，会回调到那个函数里
 *
 * pstInParam.emSpecCap = 2
 * pstInParam.pCapParam = deviceId
 *
 * 这辆参数至关重要，我也在这里卡了很久，尤其是deviceId，我也是设置了这个才解决了上面那个登录网络异常问题的。
 *
 * @author yangxin
 * 2021/4/20 14:40
 */
public class ApiModule {

    public static NetSDKLib netSdk = NetSDKLib.NETSDK_INSTANCE;

    /**
     * 设备信息
     */
    public static NetSDKLib.NET_DEVICEINFO_Ex mStDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();

    /**
     * 登录句柄
     */
    public static NetSDKLib.LLong mHLoginHandle = new NetSDKLib.LLong(0);
    private static NetSDKLib.LLong mServerHandler = new NetSDKLib.LLong(0);

    /**
     * 开启监听
     */
    public static NetSDKLib.LLong startServer(String ip) {
        mServerHandler = netSdk.CLIENT_ListenServer(ip, 9500, 1000, ServiceCallBack.getInstance(), null);
        if (0 == mServerHandler.longValue()) {
            System.err.println("Failed to start server." + ToolKits.getErrorCodePrint());
        } else {
            System.out.printf("Start server, [Server address %s][Server port %d]\n", ip, 9500);
        }
        return mServerHandler;
    }

    public static Boolean login(String mStrIp, int mNPort, String mStrUser, String mStrPassword, String deviceIds) {
        Pointer deviceId = ToolKits.GetGBKStringToPointer(deviceIds);

        // 入参
        NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY pstInParam = new NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstInParam.nPort = mNPort;
        pstInParam.szIP = mStrIp.getBytes(StandardCharsets.UTF_8);
        pstInParam.szPassword = mStrPassword.getBytes(StandardCharsets.UTF_8);
        pstInParam.szUserName = mStrUser.getBytes(StandardCharsets.UTF_8);

        // 这一步，这一步至关重要
        pstInParam.emSpecCap = 2;
        pstInParam.pCapParam = deviceId;

        // 出参
        NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam = new NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstOutParam.stuDeviceInfo = mStDeviceInfo;
        mHLoginHandle = netSdk.CLIENT_LoginWithHighLevelSecurity(pstInParam, pstOutParam);
        if (mHLoginHandle.longValue() == 0) {
            System.err.printf("登录设备[%s] 端口[%d]失败。 %s\n", mStrIp, mNPort, ToolKits.getErrorCodePrint());
        } else {
            System.out.println("登录成功[ " + mStrIp + " ]");
        }

        return mHLoginHandle.longValue() != 0;
    }
}
