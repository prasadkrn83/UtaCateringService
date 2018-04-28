package uta.com.cateringsystem.service.dao;

import java.sql.Date;
import uta.com.cateringsystem.service.beans.AvailableHallList;
import java.util.List;
import java.util.Map;

import uta.com.cateringsystem.service.beans.Event;
import uta.com.cateringsystem.service.beans.Hall;
import uta.com.cateringsystem.service.beans.User;

public interface DbManager {
	//completed
	public User getUser(String userName);
	public boolean requestEvent(Event eventRequest);
	public boolean createEvent(int eventId,int userId);
	public boolean rejectEvent(int eventId,int userId);
	public boolean cancelEvent(int eventId,int userId);
	public boolean assignStaffToEvent(int eventId, List<Integer> staffList, int userId);
	public Map<Integer,String> getDrinkVenues();
	public Map<Integer,String> getFoodVenues();
	public List<Hall> getHalls();
	public Map<Integer,String> getMeals();
	public Map<Integer,String> getUserTypes();
	public Map<Integer,String> getMealFormalities();
	public List<User> searchUser(Map<String,String> filters);
	public boolean deleteProfile(int userId,int adminId);
	public boolean updateProfile(User user,int userId);
	public boolean registerUser(User user);
	public List<User> getRegistrationRequestList();
	public boolean approveRegistrationRequest(int userId,int adminId);
	public boolean rejectRegistrationRequest(int userId,int adminId);

	//todo
	public List<Event> getCatererEventSummaryList(Date startDate,Date endDate);
	public List<Event> getUserEventSummaryList(Date startDate,Date endDate);
	public List<Event> getAssignedEventSummaryList(Date startDate,Date endDate,int userId);
	public List<AvailableHallList> searchAvailableHalls(Date fromDate,Date toDate, String startTime,String endTime );
	

	
	
}
