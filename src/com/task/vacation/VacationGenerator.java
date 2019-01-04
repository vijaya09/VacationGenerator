package com.task.vacation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.task.common.VacationUtil;

public class VacationGenerator {
	public static void main(String[] args) {
		String fileName = "src\\resources\\weather.txt";
		Map<Integer, List<WeatherDetails>> map = new HashMap<>();
		try {
			List<WeatherDetails> daysList = new ArrayList<>();
			daysList = Files.lines(Paths.get(fileName)).skip(1).limit(2).map(line -> VacationUtil.mapLineToObject(line))
					.collect(Collectors.toList());
			// Finds the first day for the date
			VacationUtil.findFirstDay(daysList);
			// Generates day map
			VacationUtil.getDayForDate();
			map = Files.lines(Paths.get(fileName)).skip(1).filter(line -> !line.trim().isEmpty())
					.map(line -> VacationUtil.mapLineToObject(line))
					.filter(l -> l.getHighTemperature() <= 85 && l.getLowTemperature() >= 70)
					.collect(Collectors.groupingBy(WeatherDetails::getPrecipitation, Collectors.toList()));

			Optional<Map.Entry<Integer, List<WeatherDetails>>> firstEntry = map.entrySet().stream()
					.sorted(Map.Entry.comparingByKey()).findFirst();
			if (firstEntry.isPresent()) {
				firstEntry.get().getValue()
						.forEach(weathDet -> System.out.println(VacationUtil.dayMap.get(weathDet.getDayOfMonth() % 7) + " the "
								+ weathDet.getDayOfMonth() + "th day of the month is the best day for a picnic."));
			}
		} catch (IOException e) {
			System.out.println("Error Encountered: " + e.getMessage());
		}
	}
}