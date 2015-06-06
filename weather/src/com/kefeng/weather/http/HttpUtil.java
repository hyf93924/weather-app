package com.kefeng.weather.http;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	String url = "http://api.map.baidu.com/telematics/v3/weather?" +
			"output=json&ak=ZH8c4MbTKB9h8hulr04QXitj" +
			"&mcode=94:B6:76:F3:C7:83:27:48:2F:70:F2:9A:9F:9B:09:44:C3:65:75:67;com.kefeng.weather" +
			"&location=";
	
	public String    getWeather(String city){
		String result="";
		try {
			url = url+URLEncoder.encode(city,"utf-8");
			HttpGet get = new HttpGet(url);
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200){
				//获得响应内容
				result = EntityUtils.toString(response.getEntity());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
		
	}
}
