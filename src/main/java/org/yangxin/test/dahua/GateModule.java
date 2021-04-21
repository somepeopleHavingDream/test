package org.yangxin.test.dahua;


import com.sun.jna.Memory;
import org.yangxin.test.dahua.com.netsdk.demo.module.LoginModule;
import org.yangxin.test.dahua.com.netsdk.lib.NetSDKLib;
import org.yangxin.test.dahua.com.netsdk.lib.ToolKits;

import java.time.LocalDateTime;

/**
 * Sdk里面有例子，尽量使用大华封装好的东西，获取门禁卡记录用到了如下java类：
 * - com/netsdk/lib
 *  - NetSDKLib.java
 *  - ToolKits.java
 *  - Utils.java
 * - com/netsdk/demo/module
 *  - LoginModule.java
 *
 * com.netsdk.demo.module.GateModule.java里面没有现成的获取门禁刷卡记录的实现方法。
 *
 * @author yangxin
 * 2021/4/20 10:01
 */
public class GateModule {

    /**
     * 查询刷卡记录，获取查询句柄
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 对应的查询句柄
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

    public void invoke() {
        boolean flag = LoginModule.login("", 37777, "admin", "123456");
        if (flag) {
            // 检索结束时间：当前时间
            LocalDateTime endDateTime = LocalDateTime.now();
            NetSDKLib.NET_TIME endTime = new NetSDKLib.NET_TIME();
            endTime.setTime(endDateTime.getYear(),
                    endDateTime.getMonthValue(), endDateTime.getDayOfMonth(), endDateTime.getHour(),
                    endDateTime.getMinute(), endDateTime.getSecond());

            // 获取检索开始时间：当前时间 - 5分钟
            LocalDateTime startDateTime = endDateTime.minusMinutes(5);
            NetSDKLib.NET_TIME startTime = new NetSDKLib.NET_TIME();
            startTime.setTime(startDateTime.getYear(), startDateTime.getMonthValue(), startDateTime.getDayOfMonth(),
                    startDateTime.getHour(), startDateTime.getMinute(), startDateTime.getSecond());

            NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC[] cardRecords = GateModule.findRecords(startTime, endTime);

            for (NetSDKLib.NET_RECORDSET_ACCESS_CTL_CARDREC cardRecord : cardRecords) {
                // 卡号
                String cardNo = new String(cardRecord.szCardNo).trim();

                // 如果刷卡状态不正确，或者按键开门，就不录入数据
                if (cardRecord.bStatus != 1 || "00000000".equals(cardNo)) {
                    continue;
                }

                // 刷卡时间 转换为北京时间 +8小时
            }
        }
    }
}
