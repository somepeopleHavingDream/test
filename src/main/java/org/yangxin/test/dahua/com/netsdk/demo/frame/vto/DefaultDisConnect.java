package org.yangxin.test.dahua.com.netsdk.demo.frame.vto;

import com.sun.jna.Pointer;
import org.yangxin.test.dahua.com.netsdk.lib.NetSDKLib;

/**
 * 设备断线回调函数，空实现。 建议回调函数使用单例模式
 * 
 * @author 47081
 *
 */
public class DefaultDisConnect implements NetSDKLib.fDisConnect {
	private static DefaultDisConnect INSTANCE;

	private DefaultDisConnect() {
		// TODO Auto-generated constructor stub
	}

	public static DefaultDisConnect GetInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DefaultDisConnect();
		}
		return INSTANCE;
	}

	@Override
	public void invoke(NetSDKLib.LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser) {
		// TODO Auto-generated method stub
		System.out.printf("Device[%s] Port[%d] DisConnectCallBack!\n", pchDVRIP, nDVRPort);

	}
}
