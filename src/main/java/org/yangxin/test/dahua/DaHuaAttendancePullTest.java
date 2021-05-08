package org.yangxin.test.dahua;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.test.aboutclass.innerclass.A;
import org.yangxin.test.dahua.demo.module.GateModule;
import org.yangxin.test.dahua.demo.module.LoginModule;
import org.yangxin.test.dahua.lib.NetSDKLib;
import org.yangxin.test.dahua.lib.ToolKits;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangxin
 * 2021/4/25 11:58
 */
@SuppressWarnings("ALL")
public class DaHuaAttendancePullTest {

    private static final String IP = "192.168.17.156";
    private static final Integer PORT = 37777;
    private static final String ACCOUNT = "admin";
    private static final String PASSWORD = "nhzx12345";

    public static NetSDKLib.LLong m_hAttachHandle = new NetSDKLib.LLong(0);
    private static Boolean isAttach = false;

    private static final AnalyzerDataCB analyzerCallback = new AnalyzerDataCB();

    private static final Lock LOCK = new ReentrantLock();

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

        // 设备登录
        boolean isLogin = LoginModule.login(IP, PORT, ACCOUNT, PASSWORD);
        System.out.println(isLogin ? "设备登录成功" : "设备登录失败");

        // 业务操作
        if (isLogin) {
            // 检索结束时间：当前时间
            LocalDateTime now = LocalDateTime.now();
            NetSDKLib.NET_TIME endTime = new NetSDKLib.NET_TIME();
            endTime.setTime(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                    now.getHour(), now.getMinute(), now.getSecond());

            // 获取检索开始时间：当前时间
            NetSDKLib.NET_TIME startTime = new NetSDKLib.NET_TIME();
            startTime.setTime(2021, 4, 20, 0, 0, 0);

            NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[] cardRecords = findRecords(startTime, endTime);

            for (NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC cardRecord : cardRecords) {
                // 卡号
                String cardNo = new String(cardRecord.szCardNo).trim();


                // 如果刷卡状态不正确，或者按键开门，就不录入数据
                if (cardRecord.bStatus != 1 || "00000000".equals(cardNo)) {
                    continue;
                }

                // 刷卡时间 转换为北京时间+8小时
                System.out.println("cardNo: " + cardNo);
                System.out.println("stuTime: " + cardRecord.stuTime.toStringTime());
            }
        }

//        // 业务操作
//        Runnable runnable = () -> {
//            // 不释放锁，让主线程一直运行
//            LOCK.lock();
//            if (isLogin) {
//                System.out.println("byChanNum: " + LoginModule.m_stDeviceInfo.byChanNum);
//                GateModule.realLoadPic(0, analyzerCallback);
//                if (m_hAttachHandle.longValue() != 0) {
//                    isAttach = true;
//                }
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//            LOCK.lock();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            LOCK.unlock();
//        }

        // 设备登出
        LoginModule.logout();

        // Sdk清理
        LoginModule.cleanup();
    }

    /**
     * @author yangxin
     * 2021/04/25 16:38
     */
    private static class AnalyzerDataCB implements NetSDKLib.fAnalyzerDataCallBack {

        private BufferedImage gateBufferedImage = null;

        @Override
        public int invoke(NetSDKLib.LLong lAnalyzerHandle, int dwAlarmType, Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize, Pointer dwUser, int nSequence, Pointer reserved) {
            System.out.println("AnalyzerDataCB.invoke");
            if (lAnalyzerHandle.longValue() == 0 || pAlarmInfo == null) {
                return -1;
            }

            File path = new File("./GateSnapPicture/");
            if (!path.exists()) {
                path.mkdir();
            }

            // 门禁事件
            if (dwAlarmType == NetSDKLib.EVENT_IVS_ACCESS_CTL) {
//            if (dwAlarmType == NetSDKLib.EVENT_IVS_ACCESS_CTL) {
                NetSDKLib.DEV_EVENT_ACCESS_CTL_INFO msg = new NetSDKLib.DEV_EVENT_ACCESS_CTL_INFO();
                ToolKits.GetPointerData(pAlarmInfo, msg);

                try {
                    System.out.println(new String(msg.szName, "GBK"));
                    System.out.println(new String(msg.szCardNo, "GBK"));
                    System.out.println(new String(msg.szUserID, "GBK"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                // 保存图片，获取图片缓存
                // 保存图片地址
                String snapPicPath = path + File.separator + System.currentTimeMillis() + "GateSnapPicture.jpg";
                byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
                ByteArrayInputStream byteArrInputGlobal = new ByteArrayInputStream(buffer);

                try {
                    gateBufferedImage = ImageIO.read(byteArrInputGlobal);
                    if (gateBufferedImage != null) {
                        ImageIO.write(gateBufferedImage, "jpg", new File(snapPicPath));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 图片以及门禁信息界面显示
            }

            return 0;
        }
    }

    /**
     * 查询刷卡记录，获取查询句柄
     *
     * @param startTime 查询起始时间
     * @param endTime   查询结束时间
     * @return 刷卡记录集
     */
    public static NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[] findRecords(NetSDKLib.NET_TIME startTime,
                                                                           NetSDKLib.NET_TIME endTime) {
        // 接口入参
        NetSDKLib.FIND_RECORD_ACCESSCTLCARDREC_CONDITION_EX findCondition
                = new NetSDKLib.FIND_RECORD_ACCESSCTLCARDREC_CONDITION_EX();
        findCondition.bCardNoEnable = 0;
        findCondition.stStartTime = startTime;
        findCondition.stEndTime = endTime;

        // CLIENT_FindRecord 接口入参
        NetSDKLib.NET_IN_FIND_RECORD_PARAM stIn = new NetSDKLib.NET_IN_FIND_RECORD_PARAM();
        stIn.emType = NetSDKLib.EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARDREC_EX;
        stIn.pQueryCondition = findCondition.getPointer();

        // CLIENT_FindRecord 接口出参
        NetSDKLib.NET_OUT_FIND_RECORD_PARAM stOut = new NetSDKLib.NET_OUT_FIND_RECORD_PARAM();
        findCondition.write();

        NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[] pstRecordEx = new NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[0];
        // 获取查询句柄
        if (LoginModule.netsdk.CLIENT_FindRecord(LoginModule.m_hLoginHandle, stIn, stOut, 5000)) {
            findCondition.read();

            // 用于申请内存，假定2000次刷卡记录
            int nFindCount = 2000;
            NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[] pstRecord
                    = new NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[nFindCount];
            for (int i = 0; i < nFindCount; i++) {
                pstRecord[i] = new NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC();
            }

            NetSDKLib.NET_IN_FIND_NEXT_RECORD_PARAM stNextIn = new NetSDKLib.NET_IN_FIND_NEXT_RECORD_PARAM();
            stNextIn.lFindeHandle = stOut.lFindeHandle;
            stNextIn.nFileCount = nFindCount;

            NetSDKLib.NET_OUT_FIND_NEXT_RECORD_PARAM stNextOut = new NetSDKLib.NET_OUT_FIND_NEXT_RECORD_PARAM();
            stNextOut.nMaxRecordNum = nFindCount;
            // 申请内存
            stNextOut.pRecordList = new Memory(pstRecord[0].dwSize * nFindCount);
            stNextOut.pRecordList.clear(pstRecord[0].dwSize * nFindCount);

            // 将数组内存拷贝给指针
            ToolKits.SetStructArrToPointerData(pstRecord, stNextOut.pRecordList);

            if (LoginModule.netsdk.CLIENT_FindNextRecord(stNextIn, stNextOut, 5000)) {
                if (stNextOut.nRetRecordNum == 0) {
                    return pstRecordEx;
                }

                // 获取卡信息
                ToolKits.GetPointerDataToStructArr(stNextOut.pRecordList, pstRecord);

                // 获取有用的信息
                pstRecordEx = new NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[stNextOut.nRetRecordNum];
                for (int i = 0; i < stNextOut.nRetRecordNum; i++) {
                    pstRecordEx[i] = new NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC();
                    pstRecordEx[i] = pstRecord[i];
                }
            }

            LoginModule.netsdk.CLIENT_FindRecordClose(stOut.lFindeHandle);
        }

        return pstRecordEx;
    }
}
