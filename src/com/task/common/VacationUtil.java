package com.task.common;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.vacation.WeatherDetails;

public class VacationUtil {
	public static Map<Integer, String> dayMap = new HashMap<>();
	
	/*
	 * finds first day based on the first character of the day
	 */
	public static void findFirstDay(List<WeatherDetails> daysList) {
		if ("m".equalsIgnoreCase(daysList.get(0).getDayOfWeek())) {
			dayMap.put(daysList.get(0).getDayOfMonth(), "Monday");
		} else if ("w".equalsIgnoreCase(daysList.get(0).getDayOfWeek())) {
			dayMap.put(daysList.get(0).getDayOfMonth(), "Wednesday");
		} else if ("f".equalsIgnoreCase(daysList.get(0).getDayOfWeek())) {
			dayMap.put(daysList.get(0).getDayOfMonth(), "Friday");
		} else if ("t".equalsIgnoreCase(daysList.get(0).getDayOfWeek())) {
			if ("w".equalsIgnoreCase(daysList.get(1).getDayOfWeek())) {
				dayMap.put(daysList.get(0).getDayOfMonth(), "Tuesday");
			} else {
				dayMap.put(daysList.get(0).getDayOfMonth(), "Thursday");
			}
		} else if ("s".equalsIgnoreCase(daysList.get(0).getDayOfWeek())) {
			if ("s".equalsIgnoreCase(daysList.get(1).getDayOfWeek())) {
				dayMap.put(daysList.get(0).getDayOfMonth(), "Saturday");
			} else {
				dayMap.put(daysList.get(0).getDayOfMonth(), "Sunday");
			}
		}
	}
	
	/*
	 * converts each line to an object
	 */
	public static WeatherDetails mapLineToObject(String line) {
		String[] details = line.split("[\t, ]+");
		WeatherDetails weatherDetails = new WeatherDetails();
		weatherDetails.setDayOfMonth(Integer.parseInt(details[0].trim()));
		weatherDetails.setDayOfWeek(details[1]);
		weatherDetails.setHighTemperature(convertCtoF(Integer.parseInt(details[2].trim())));
		weatherDetails.setLowTemperature(convertCtoF(Integer.parseInt(details[3].trim())));
		weatherDetails.setPrecipitation(Integer.parseInt(details[4].trim()));
		return weatherDetails;
	}
	
	/*
	 * converts temperature from Celcius to Farenheit
	 */
	public static double convertCtoF(Integer value) {
		return ((value * 9 / 5.0) + 32);
	}
	
	/*
	 * getting day of the week based on date
	 */
	public static void getDayForDate() {
		Map<Integer, Integer> map = new HashMap<>();
		int dayOfweek = DayOfWeek.valueOf(dayMap.get(1).toUpperCase()).getValue();
		for (int i = 0; i < 7; i++) {
			int day = (dayOfweek + i) % 7 == 0 ? 7 : (dayOfweek + i) % 7;
			dayMap.put(new Integer(i + 1), DayOfWeek.of(day).name());
		}
		dayMap.put(0, dayMap.get(7));
	}
}
