package com.eling.elcms.assistant.api.util;

import java.util.List;

import com.eling.elcms.assistant.api.minigps.JsonUtil;
import com.eling.elcms.assistant.api.minigps.Lbs;
import com.eling.elcms.assistant.api.minigps.LocRadiusPoiResponse;
import com.eling.elcms.assistant.api.minigps.RemoteUtil;
import com.eling.elcms.assistant.api.minigps.Wifi;
import com.eling.elcms.assistant.api.minigps.WifiList;

/**
 * minigps工具包 
 * @author swq
 *
 */
public class MiniGpsUtil {

	public static LocRadiusPoiResponse getLocation(List<Lbs> lbs,List<Wifi> wifi){
		//经纬度纠偏类型：默认 mt=1 0：表示使用标准经纬度 1：根据mcc所在的国家自动 2：Google地图的经纬度
		int mt = 0; //get the std(wgs84) lat and lon
		LocRadiusPoiResponse loc = getPOIFromMinigps(lbs, wifi, mt);
		return loc;
	}
	//	http://minigps.net/cw?x=1cc-0-6212-2F8C-AC-52EC-28AD-96-6212-2F8B-96-6212-FAD-94-6212-3FB5-93-6212-FAB-93
	//	{"ws":[{"s":"xo","r":81,"m":804380873802619826},{"s":"terry","r":69,"m":2018924576320342756},{"s":"TP-LINK_3225EE","r":53,"m":674173120793097686},{"s":"loushangshengyinxiaodian","r":49,"m":44590646795096412}]}
	static public LocRadiusPoiResponse getPOIFromMinigps(List<Lbs> cells, List<Wifi> ws, int mt)
	{
		LocRadiusPoiResponse loc = null;
		String result = null;
		String url = "http://minigps.net/cw?p=1&needaddress=0&mt=" + mt;
		String x = getXParameter(cells);
		String w = getWParameter(ws);
		if(x != null)
		{
			url = url + "&x=" + x;
		}
		if(w != null)
		{
			result = RemoteUtil.request(url, "POST", "application/json;charset=utf-8", w);
		}
		else
		{
			result = RemoteUtil.request(url, "GET", "application/json;charset=utf-8", null);
		}
		if(result != null)
		{
			System.out.print(result);
			loc = (LocRadiusPoiResponse) JsonUtil.fromJson(LocRadiusPoiResponse.class, result);
		}
		return loc;
	}
	static private String getXParameter(List<Lbs> cells)
	{
		String x = null;
		if(cells != null && cells.size() > 0)
		{
			//mcc-mnc
			x = "%x-%x";
			Lbs c = cells.get(0);
		    x = String.format(x, c.getMcc(), c.getMnc());
		    for(int i = 0; i < cells.size(); ++i)
		    {
			    c = cells.get(i);
			    x += "-%x-%x-%x";
			    x = String.format(x, c.getLac(), c.getCellid(), c.getSdb());
		    }
		}
		return x;
	}

	static private String getWParameter(List<Wifi> ws)
	{
		String w = null;
		if(ws != null && ws.size() > 0)
		{
			WifiList wlist = new WifiList();
			wlist.setWs(ws);
			w = JsonUtil.toJson(wlist);
		}
		return w;
	}
}
