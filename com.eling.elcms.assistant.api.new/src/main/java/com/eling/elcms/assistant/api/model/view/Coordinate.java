package com.eling.elcms.assistant.api.model.view;

import java.util.Date;

public class Coordinate {

	//经度
	private String longitude;
	
	//纬度
	private String latitude;
	
	//定位时间
	private Date time;
	
	//坐标半径
	private int radius;

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	
}
