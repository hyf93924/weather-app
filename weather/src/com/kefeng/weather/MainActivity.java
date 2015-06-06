package com.kefeng.weather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


//继承自FragmentActivity
public class MainActivity extends FragmentActivity {
	
	ViewPager  pager;
	String[]  citys={"湘潭","芮城","北京","上海","广州","杭州"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pager=(ViewPager)findViewById(R.id.pager1);
		MyWeatherPageAdapter  adapter=new MyWeatherPageAdapter(this.getSupportFragmentManager());
		//给pager添加Adapter适配器   适配器是用来给viewpager提供要显示的内容
		pager.setAdapter(adapter);
	}
	
	private  class MyWeatherPageAdapter  extends FragmentPagerAdapter
	{

		public MyWeatherPageAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		
		//getItem方法返回每一页要显示的内容   在我们这个案例中 每一页就是一个fragment(碎片)
		@Override
		public Fragment getItem(int index) {
			
			Fragment fragment=new MyWeatherFragment(citys[index]);
			return fragment;
		}

		//这个方法返回page的数量  ViewPager会自动调用这个方法确定要显示的Page的数量
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return citys.length;
		}
		
	}
	
}
