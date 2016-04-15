package com.eling.elcms.assistant.api.minigps;

public class Lbs {
	// 460为中国
	int mcc;
	// 0为移动
	int mnc;
	// LAC
	int lac;
	// CID
	int cellid;
	// 信号强度
	int sdb;

	public int getSdb() {
		return sdb;
	}

	public void setSdb(int sdb) {
		this.sdb = sdb;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}

	public int getCellid() {
		return cellid;
	}

	public void setCellid(int cellid) {
		this.cellid = cellid;
	}
}
