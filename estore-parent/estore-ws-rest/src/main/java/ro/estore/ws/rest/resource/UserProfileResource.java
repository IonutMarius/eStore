package ro.estore.ws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "product", collectionRelation = "products")
public class UserProfileResource extends ResourceSupport{
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
