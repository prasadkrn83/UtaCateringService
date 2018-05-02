package uta.com.cateringsystem.service.util;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import uta.com.cateringsystem.service.beans.AvailableHall;
import uta.com.cateringsystem.service.beans.Hall;

public class UtaCateringServiceUtil {

	public static List<AvailableHall> computeAvailableHalls(List<Hall> halls,Map<Integer,AvailableHall> currentScheduleList,
			Date startDate,Date endDate,String startTimeStr,String endTimeStr){
		List<AvailableHall> availableHalls = new ArrayList<AvailableHall>();
		LocalTime startTime = LocalTime.parse( startTimeStr) ;
		LocalTime endTime = LocalTime.parse( endTimeStr) ;

		Calendar calStartDate= Calendar.getInstance();
		Calendar calEndDate= Calendar.getInstance();
		
		calStartDate.setTime(startDate);
		calEndDate.setTime(endDate);
		
		for (Hall hallEntry: halls) {
			if(currentScheduleList.containsKey(hallEntry.getId())) {
				AvailableHall hall = new AvailableHall();
				hall.setStartDate(startDate);
				hall.setEndDate(endDate);
				hall.setHallId(hallEntry.getId());
				hall.setHallName(hallEntry.getName());

				AvailableHall currentBooking = currentScheduleList.get(hallEntry.getId());
				if(startTime.isAfter(LocalTime.parse(currentBooking.getEndTime()))) {
					hall.setStartTime(startTimeStr);
					hall.setEndTime(endTimeStr);
				}else if(startTime.isBefore(LocalTime.parse(currentBooking.getEndTime()))
						&& endTime.isAfter(LocalTime.parse(currentBooking.getEndTime()))){
					hall.setStartTime(currentBooking.getEndTime());
					hall.setEndTime(endTimeStr);
				}else {
					continue;
				}
				availableHalls.add(hall);

			}else {
				AvailableHall hall = new AvailableHall();
					hall.setStartDate(startDate);
					hall.setEndDate(endDate);
					hall.setStartTime(startTimeStr);
					hall.setEndTime(endTimeStr);
					hall.setHallId(hallEntry.getId());
					hall.setHallName(hallEntry.getName());
					availableHalls.add(hall);
				}
			}
	
		
		return availableHalls;
		
	}
	
	public static boolean isDateWeekend(Calendar c1) {
		if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
			    c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}else {
			return false;
		}
	}

	public static long getDateValue(String frmDateStr) {
		// TODO Auto-generated method stub
		return Calendar.getInstance().getTimeInMillis();
	}

}
