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


//�̳���FragmentActivity
public class MainActivity extends FragmentActivity {
	
	ViewPager  pager;
	String[]  citys={"��̶","�ǳ�","����","�Ϻ�","����","����"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pager=(ViewPager)findViewById(R.id.pager1);
		MyWeatherPageAdapter  adapter=new MyWeatherPageAdapter(this.getSupportFragmentManager());
		//��pager���Adapter������   ��������������viewpager�ṩҪ��ʾ������
		pager.setAdapter(adapter);
	}
	
	private  class MyWeatherPageAdapter  extends FragmentPagerAdapter
	{

		public MyWeatherPageAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		
		//getItem��������ÿһҳҪ��ʾ������   ��������������� ÿһҳ����һ��fragment(��Ƭ)
		@Override
		public Fragment getItem(int index) {
			
			Fragment fragment=new MyWeatherFragment(citys[index]);
			return fragment;
		}

		//�����������page������  ViewPager���Զ������������ȷ��Ҫ��ʾ��Page������
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return citys.length;
		}
		
	}
	
}
