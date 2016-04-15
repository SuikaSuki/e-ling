package com.eling.elcms.assistant.api.minigps;


public class Wifi {
	// ssid(wifi名 非必须)
	String s;
	// mac地址
	long m;
	// 信号强度
	int r;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		if (s != null) {
			if (s.length() > 32) {
				this.s = s.substring(0, 31);
			} else {
				this.s = s;
			}
		} else {
			this.s = s;
		}

	}

	public long getM() {
		return m;
	}

	public void setM(long m) {
		this.m = m;
	}
	public void setMac(String mac){
		this.m = WifiAddressConverter.Str2Long(mac);
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
}
