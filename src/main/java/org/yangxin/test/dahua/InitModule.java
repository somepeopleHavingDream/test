package org.yangxin.test.dahua;

import org.yangxin.test.dahua.com.netsdk.lib.NetSDKLib;
import org.yangxin.test.dahua.com.netsdk.lib.ToolKits;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
 * CLIENT_SetNetworkParam  设置登录网络环境
 * CLIENT_Cleanup   		释放NetSDK缓存
 * CLIENT_LogOpen   		打开日志
 * CLIENT_LogClose  		关闭日志
 * CLIENT_LoginWithHighLevelSecurity			登录
 * CLIENT_Logout			登出
 *
 * - 启动/停止监听服务基本流程
 *     - 开始
 *     - SDK初始化 CLIENT_Init
 *     - 开启监听服务 CLIENT_ListenServer
 *         - 登录设备 CLIENT_LoginWithHighLevelSecurity
 *         - 其他业务操作
 *         - 登出设备 CLIENT_Logout
 *     - 结束监听 CLIENT_StopListenServer
 *     - 释放SDK资源
 *     - 结束
 *
 * @author yangxin
 * 2021/4/20 14:04
 */
@SuppressWarnings({"ResultOfMethodCallIgnored", "DuplicatedCode"})
public class InitModule {

    public static NetSDKLib netSdk = NetSDKLib.NETSDK_INSTANCE;

    private static Boolean init = false;
    private static Boolean bLogOpen = false;

    public static Boolean init(NetSDKLib.fDisConnect disConnect, NetSDKLib.fHaveReConnect haveReConnect) {
        init = netSdk.CLIENT_Init(disConnect, null);
        if (!init) {
            // 初始化失败
            System.out.println("初始化失败");
            return false;
        }

        // 打开日志，可选
        NetSDKLib.LOG_SET_PRINT_INFO setLog = new NetSDKLib.LOG_SET_PRINT_INFO();
        File path = new File("./sdklog/");
        if (!path.exists()) {
            path.mkdir();
        }

        String logPath = path.getAbsoluteFile().getParent() + "\\sdklog\\" + ToolKits.getDate() + ".log";
        setLog.nPrintStrategy = 0;
        setLog.bSetFilePath = 1;
        System.arraycopy(logPath.getBytes(StandardCharsets.UTF_8), 0,
                setLog.szLogFilePath, 0, logPath.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(logPath);
        setLog.bSetPrintStrategy = 1;
        bLogOpen = netSdk.CLIENT_LogOpen(setLog);
        if (!bLogOpen) {
            System.err.println("Fail to open NetSDK log");
        }

        netSdk.CLIENT_SetAutoReconnect(haveReConnect, null);
        // 设置更多网络参数，NET_PARAM的nWaittime，nConnectTryNum成员与CLIENT_SetConnectTime
        // 接口设置的登录设备超时时间和尝试次数意义相同，可选
        NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
        // 登录时尝试建立链接的超时时间
        netParam.nConnectTime = 10000;
        // 设置子连接的超时时间
        netParam.nGetConnInfoTime = 3000;
        netSdk.CLIENT_SetNetworkParam(netParam);

        System.out.println("初始化成功");
        return true;
    }

    public static void cleanup() {
        if (bLogOpen) {
            netSdk.CLIENT_LogClose();
        }
        if (init) {
            netSdk.CLIENT_Cleanup();
        }
    }
}
