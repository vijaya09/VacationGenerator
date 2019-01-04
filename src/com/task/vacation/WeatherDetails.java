package com.task.vacation;

public class WeatherDetails {
	private int dayOfMonth;
	private String dayOfWeek;
	private double highTemperature;
	private double lowTemperature;
	private int precipitation;
	
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public double getHighTemperature() {
		return highTemperature;
	}
	public void setHighTemperature(double highTemperature) {
		this.highTemperature = highTemperature;
	}
	public double getLowTemperature() {
		return lowTemperature;
	}
	public void setLowTemperature(double lowTemperature) {
		this.lowTemperature = lowTemperature;
	}
	public int getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(int precipitation) {
		this.precipitation = precipitation;
	}
	@Override
	public String toString() {
		return "WeatherDetails [dayOfMonth=" + dayOfMonth + ", dayOfWeek=" + dayOfWeek + ", highTemperature="
				+ highTemperature + ", lowTemperature=" + lowTemperature + ", precipitation=" + precipitation + "]";
	}
	
}
