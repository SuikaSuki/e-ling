package com.eling.elcms.video.util;

import java.awt.geom.Point2D;

public class CoordinateUtil {
		  
	    private static double EARTH_RADIUS = 6378.137;  
	  
	    private static double rad(double d) {  
	        return d * Math.PI / 180.0;  
	    }  
	  
	   /* public static double getDistance(double lat1, double lng1, double lat2,  
	            double lng2) {  
	        double radLat1 = rad(lat1);  
	        double radLat2 = rad(lat2);  
	        double a = radLat1 - radLat2;  
	        double b = rad(lng1) - rad(lng2);  
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
	                + Math.cos(radLat1) * Math.cos(radLat2)  
	                * Math.pow(Math.sin(b / 2), 2)));  
	        s = s * EARTH_RADIUS;  
	        s = Math.round(s * 10000) / 10000;  
	        return s;  
	    }  */

	/**
	 * 计算两经纬度点之间的距离（单位：米）
	 * 
	 * @param lng1经度
	 * @param lat1纬度
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double a = radLat1 - radLat2;
		double b = Math.toRadians(lng1) - Math.toRadians(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		//s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	public static void main(String args[]){
		
		System.out.println(Point2D.distance(415,535,445,605));
	}
}
