package uta.com.cateringsystem.service.beans;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Event {

	
	private int eventId;
	private String eventName;
	private Date eventDate;
	private String startTime;
	private String endTime;
	private int hallId;
	private int eventCapacity;
	private int userId;
	private int catererId;
	private Date createdDate;
	private int mealTypeId;
	private int mealFormalityId;
	private int modifiedBy;
	private Date modifiedDate;
	private int eventStatus;
	private int foodVenueId;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public int getEventCapacity() {
		return eventCapacity;
	}
	public void setEventCapacity(int eventCapacity) {
		this.eventCapacity = eventCapacity;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCatererId() {
		return catererId;
	}
	public void setCatererId(int catererId) {
		this.catererId = catererId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getMealTypeId() {
		return mealTypeId;
	}
	public void setMealTypeId(int mealTypeId) {
		this.mealTypeId = mealTypeId;
	}
	public int getMealFormalityId() {
		return mealFormalityId;
	}
	public void setMealFormalityId(int mealFormalityId) {
		this.mealFormalityId = mealFormalityId;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public int getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}
	public int getFoodVenueId() {
		return foodVenueId;
	}
	public void setFoodVenueId(int foodVenueId) {
		this.foodVenueId = foodVenueId;
	}
	
	
	
}
