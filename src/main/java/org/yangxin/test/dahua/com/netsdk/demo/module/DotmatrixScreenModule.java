package org.yangxin.test.dahua.com.netsdk.demo.module;

import com.netsdk.lib.NetSDKLib.NET_CTRL_SET_PARK_INFO;

public class DotmatrixScreenModule {
	
	
	public static boolean setDotmatrixScreen(int emType, NET_CTRL_SET_PARK_INFO msg) {
		
		boolean ret = LoginModule.netsdk.CLIENT_ControlDevice(LoginModule.m_hLoginHandle, emType, msg.getPointer(), 3000);
		
		return ret;	
	}
}
