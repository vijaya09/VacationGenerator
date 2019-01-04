package com.task.vacation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.task.common.VacationUtil;

public class VacationRangeGenerator {

	public static void main(String[] args) {
		String fileName = "src\\resources\\weather.txt";
		Map<Integer, List<WeatherDetails>> map = new HashMap<>();
		try {
			// Get total number of days in file
			long count = Files.lines(Paths.get(fileName)).skip(1).count();

			Scanner scanner = new Scanner(System.in);
			int noOfDays;
			do {
				System.out.println("Enter number of days:");
				while (!scanner.hasNextInt()) {
					System.out.println("Please Enter a Valid Number:");
					scanner.next();
				}
				noOfDays = scanner.nextInt();
			} while (noOfDays <= 0 || noOfDays >= count);

			List<WeatherDetails> daysList = new ArrayList<>();
			daysList = Files.lines(Paths.get(fileName)).skip(1).filter(line -> !line.trim().isEmpty())
					.map(line -> VacationUtil.mapLineToObject(line))
					.filter(l -> l.getHighTemperature() <= 85 && l.getLowTemperature() >= 70)
					.collect(Collectors.toList());

			List<WeatherDetails> daysList2 = new ArrayList<>();
			daysList2 = Files.lines(Paths.get(fileName)).skip(1).limit(2).map(line -> VacationUtil.mapLineToObject(line))
					.collect(Collectors.toList());
			// Finds the first day for the date
			VacationUtil.findFirstDay(daysList2);
			// Generates day map
			VacationUtil.getDayForDate();
			if (daysList.size() < noOfDays) {
				System.out.println("No Continuous Vacation Days exist for your input of: " + count + " days");
			}

			TreeMap<Integer, Integer> precipMap = new TreeMap<>();

			for (int i = 0; i < daysList.size() - noOfDays; i++) {
				if (daysList.get(i + noOfDays - 1).getDayOfMonth() - daysList.get(i).getDayOfMonth() == noOfDays - 1) {
					precipMap.put(getAveragePrecipitation(daysList, i, noOfDays), i);
				}
			}

			if (precipMap.isEmpty()) {
				System.out.println("No Continuous Vacation Days exist for your input of: " + noOfDays + " days");
				return;
			}

			int index = precipMap.firstEntry().getValue();

			for (int i = 0; i < noOfDays; i++) {
				System.out.println(VacationUtil.dayMap.get(daysList.get(index + i).getDayOfMonth() % 7) + " the "
						+ daysList.get(index + i).getDayOfMonth()
						+ "th day of the month is the best day for a picnic.");
			}

		} catch (IOException e) {
			System.out.println("Error Encountered: " + e.getMessage());
		}

	}
	
	/*
	 * returns sum of precipitation for sequence of days
	 */
	private static int getAveragePrecipitation(List<WeatherDetails> daysList, int i, int noOfDays) {
		int sumPrecip = 0;
		for (int j = 0; j < noOfDays; j++) {
			sumPrecip += daysList.get(i + j).getPrecipitation();
		}
		return sumPrecip;
	}
}
