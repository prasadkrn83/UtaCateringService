package uta.com.cateringsystem.service.beans;

public enum UserType {
	USER("User"), 
	CATERER("Caterer"), 
	STAFF("Staff"), 
	ADMIN("Admin");

	private String userType;

	UserType(String userType) {
	        this.userType = userType;
	    }

	public String userType() {
		return userType;
	}
}
	