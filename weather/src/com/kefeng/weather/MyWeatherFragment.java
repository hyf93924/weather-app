package com.kefeng.weather;

import com.google.gson.Gson;
import com.kefeng.weather.entity.CityResult;
import com.kefeng.weather.entity.WeatherData;
import com.kefeng.weather.entity.WeatherResult;
import com.kefeng.weather.http.HttpUtil;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyWeatherFragment extends Fragment{

	String city=null;
	
	TextView cityTxt;
	
	ImageView   weatherImg;
	TextView    currentTxt;
	TextView    tempTxt;
	TextView    weatherTxt;
	TextView    windTxt;
	
	
	ImageView[]  imgIcons=new ImageView[3];
	TextView[]   dayTxts=new TextView[3];
	TextView[]   tempTxts=new TextView[3];
	TextView[]   weatherTxts=new TextView[3];
	TextView[]   windTxts=new TextView[3];
	
	String[] weather={"晴","多云","阴","阵雨","雷阵雨","雷阵雨伴有冰雹","雨夹雪","小雨","中雨","大雨","暴雨","大暴雨","特大暴雨","阵雪","小雪","中雪","大雪","暴雪","雾",
	        "冻雨","沙尘暴","小雨转中雨","中雨转大雨","大雨转暴雨","暴雨转大暴雨","大暴雨转特大暴雨","小雪转中雪","中雪转大雪","大雪转暴雪","浮尘","扬沙",
	        "强沙尘暴","霾","多云转晴","晴转多云","多云转小雨","小雨转多云"};
int[]weatherIcons={R.drawable.day00,R.drawable.day01,R.drawable.day02,R.drawable.day03,
	           R.drawable.day04,R.drawable.day04,R.drawable.day06,R.drawable.day08,
	           R.drawable.day09,R.drawable.day11,R.drawable.day08,
	           R.drawable.day09,R.drawable.day11,R.drawable.day13,
	           R.drawable.day14,R.drawable.day15,R.drawable.day17,R.drawable.day17,
	           R.drawable.day18,R.drawable.day06,R.drawable.day20,
	           R.drawable.day08,R.drawable.day09,R.drawable.day11,
	           R.drawable.day11,R.drawable.day11,R.drawable.day14,R.drawable.day15,R.drawable.day17,
	           R.drawable.day20,R.drawable.day20,R.drawable.day20,R.drawable.day20
	           ,R.drawable.day01,R.drawable.day01,R.drawable.day03,R.drawable.day03
               };	
	
   public MyWeatherFragment(String  city)
   {
	   this.city=city;
   }
	
   
   
   //创建这个Fragment要显示的视图
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
       //inflater是布局文件的解析器，可以使用它去加载布局文件 并生成View对象
	 View view=  inflater.inflate(R.layout.weather_fragment, null, true);
	 init(view);
	return view;
   }
	
   public void init(View view)
   {
	   cityTxt=(TextView)view.findViewById(R.id.cityTxt);
	   cityTxt.setText(city);
	   weatherImg=(ImageView)view.findViewById(R.id.weatherImg);
	   currentTxt=(TextView)view.findViewById(R.id.currentTxt);
	   tempTxt=(TextView)view.findViewById(R.id.tempTxt);
	   weatherTxt=(TextView)view.findViewById(R.id.weatherTxt);
	   windTxt=(TextView)view.findViewById(R.id.windTxt);

	   imgIcons[0]=(ImageView)view.findViewById(R.id.imgIcon1);
	   dayTxts[0]=(TextView)view.findViewById(R.id.dayTxt1);
	   tempTxts[0]=(TextView)view.findViewById(R.id.tempTxt1);
	   weatherTxts[0]=(TextView)view.findViewById(R.id.weatherTxt1);
	   windTxts[0]=(TextView)view.findViewById(R.id.windTxt1);

	   imgIcons[1]=(ImageView)view.findViewById(R.id.imgIcon2);
	   dayTxts[1]=(TextView)view.findViewById(R.id.dayTxt2);
	   tempTxts[1]=(TextView)view.findViewById(R.id.tempTxt2);
	   weatherTxts[1]=(TextView)view.findViewById(R.id.weatherTxt2);
	   windTxts[1]=(TextView)view.findViewById(R.id.windTxt2);	   
	   
	   imgIcons[2]=(ImageView)view.findViewById(R.id.imgIcon3);
	   dayTxts[2]=(TextView)view.findViewById(R.id.dayTxt3);
	   tempTxts[2]=(TextView)view.findViewById(R.id.tempTxt3);
	   weatherTxts[2]=(TextView)view.findViewById(R.id.weatherTxt3);
	   windTxts[2]=(TextView)view.findViewById(R.id.windTxt3);
	   //使用异步任务类获得天气预报的数据
	   new MyWeatherTask().execute(city);
	   
   }
   
   
   
   public  class MyWeatherTask  extends AsyncTask<String, Void, String>
   {

	@Override
	protected String doInBackground(String... params) {
		HttpUtil  http=new HttpUtil();
		String result=http.getWeather(params[0]);
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		Log.i("test", "result="+result);
		Gson gson=new Gson();
		//解析json字符串  生成javabean
		WeatherResult  wr=gson.fromJson(result, WeatherResult.class);
		if(wr!=null && wr.getError()==0)
		{
			//解析成功
			initView(wr);
		}else{
			Toast.makeText(getActivity(), city+"天气数据解析失败，请稍后再试", Toast.LENGTH_LONG).show();
		}
		super.onPostExecute(result);
	}
	   
   }
	
   public void  initView(WeatherResult  wr)
   {
	      CityResult  cr=wr.getResults().get(0);
	      WeatherData  currentWeatherData=  cr.getWeather_data().get(0);
	  	//   weatherImg;
	      
	      String str=currentWeatherData.getDate();
	      int begin=str.indexOf("：");
	      int end=str.indexOf(")");
	      str=str.substring(begin+1, end);
		    currentTxt.setText(str);
		    int resId=findImgIdByWeather(currentWeatherData.getWeather());
		    weatherImg.setImageResource(resId+1);
		    
		    tempTxt.setText(currentWeatherData.getTemperature());
		    weatherTxt.setText(currentWeatherData.getWeather());
		    windTxt.setText(currentWeatherData.getWind());
		    
		    for(int i=1;i<=3;i++)
		    {
		    	WeatherData  w=cr.getWeather_data().get(i);
			    int id=findImgIdByWeather(w.getWeather());
			    imgIcons[i-1].setImageResource(id);
		    	dayTxts[i-1].setText(w.getDate());
			    tempTxts[i-1].setText(w.getTemperature());
			    weatherTxts[i-1].setText(w.getWeather());
			    windTxts[i-1].setText(w.getWind());
		    }
   }
   
   
   public   int  findImgIdByWeather(String w)
   {
	      for(int i=0;i<weather.length;i++)
	      {
	    	    if(weather[i].equals(w))
	    	    {
	    	    	return weatherIcons[i];
	    	    }
	      }
	      return R.drawable.day00;
   }

}
