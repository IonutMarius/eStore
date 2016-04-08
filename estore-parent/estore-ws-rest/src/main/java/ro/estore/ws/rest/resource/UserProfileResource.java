package ro.estore.ws.rest.resource;

import java.util.ArrayList;
import java.util.List;

public class UserProfileResource {
	private String name;
	private String surname;
	private String phoneNumber;
	private String emailAddress;
	private List<AddressResource> addresses = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<AddressResource> getAddresses() {
		return addresses;
	}
}