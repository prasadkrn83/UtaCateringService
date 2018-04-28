package uta.com.cateringsystem.service.beans;

public enum UserStatus {
	REQUESTED("1"), 
	APPROVED("2"), 
	DELETED("5");

	private String userStatus;

	UserStatus(String userStatus) {
	        this.userStatus = userStatus;
	    }

	public String userStatus() {
		return userStatus;
	}
}
