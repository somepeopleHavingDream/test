package org.yangxin.test.classinjdk.object;

import java.util.Objects;

/**
 * @author yangxin
 * 2021/4/19 15:51
 */
public class ObjectTest {

    private static final long serialVersionUID = -5444343719597483696L;

    /**
     * 打卡设备Sn码
     */
    private String deviceSn;

    /**
     * 打卡时间戳
     */
    private String clockTimestamp;

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + deviceSn.hashCode();
        result = result * 31 + clockTimestamp.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectTest)) {
            return false;
        }

        ObjectTest objectTest = (ObjectTest) obj;
        if (this == objectTest) {
            return true;
        }

        // 如果（打卡设备Sn码，打卡时间戳）相同，我们认为这是同一条考勤记录
        return Objects.equals(deviceSn, objectTest.deviceSn)
                && Objects.equals(clockTimestamp, objectTest.clockTimestamp);
    }
}
