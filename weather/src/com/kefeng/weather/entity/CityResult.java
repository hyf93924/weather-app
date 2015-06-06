package com.kefeng.weather.entity;

import java.util.List;


public class CityResult {
	private String currnetCity;
	private String pm25;
	private List<IndexInfo> index;
	private List<WeatherData> weather_data;

	public String getCurrnetCity() {
		return currnetCity;
	}

	public void setCurrnetCity(String currnetCity) {
		this.currnetCity = currnetCity;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public List<IndexInfo> getIndex() {
		return index;
	}

	public void setIndex(List<IndexInfo> index) {
		this.index = index;
	}

	public List<WeatherData> getWeather_data() {
		return weather_data;
	}

	public void setWeather_data(List<WeatherData> weather_data) {
		this.weather_data = weather_data;
	}

}
