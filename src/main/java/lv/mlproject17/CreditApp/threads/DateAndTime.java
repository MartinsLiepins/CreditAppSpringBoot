package lv.mlproject17.CreditApp.threads;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by marko on 2017.12.22..
 */
//@Service
@Component
public class DateAndTime {

	public String getDateAndTimeString(){

		LocalDateTime timeNow = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String dateTime = timeNow.format(formatter);
		return timeNow.format(formatter);
	}

	public int getHourFromString(String dateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
//		int hour = localDateTime.getHour();

		return localDateTime.getHour();
	}
}
