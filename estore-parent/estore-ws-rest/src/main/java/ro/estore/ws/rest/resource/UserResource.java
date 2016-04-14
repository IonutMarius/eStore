package ro.estore.ws.rest.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value="user", collectionRelation="users")
public class UserResource extends ResourceSupport{
	private Long userId;
	private UserProfileResource userProfile;
	private String username;
	private String password = "";
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public UserProfileResource getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfileResource userProfile) {
		this.userProfile = userProfile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}