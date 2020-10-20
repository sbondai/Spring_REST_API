package com.sbondai.app.ws.ui.model.model.response;

/**
 * @author Ronald
 * Response object to communicate only necessary info to request
 *
 */
public class UserRest {
	// an alpha numeric public userID to communicate with the API not database
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
