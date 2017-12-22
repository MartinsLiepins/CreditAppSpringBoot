package lv.mlproject17.CreditApp.api;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by marko on 2017.12.22..
 */
@Service
public class DateAndTime {

	public String getDateAndTimeString(){

		LocalDateTime timeNow = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateTime = timeNow.format(formatter);
		return dateTime;
	}

	public int getHourFromString(String dateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
		int hour = localDateTime.getHour();

		return hour;
	}




}
