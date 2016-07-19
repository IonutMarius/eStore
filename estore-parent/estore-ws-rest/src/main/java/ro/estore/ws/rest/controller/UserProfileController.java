package ro.estore.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.estore.domain.object.UserDTO;
import ro.estore.domain.service.UserService;
import ro.estore.ws.rest.converter.UserProfileResourceConverter;
import ro.estore.ws.rest.resource.UserProfileResource;

@RestController
public class UserProfileController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserProfileResourceConverter userProfileResourceConverter;
	
	@RequestMapping(path = "/users/{id}/profile", method = RequestMethod.GET)
	public ResponseEntity<UserProfileResource> getUserProfile(@PathVariable(value = "id") Long userId){
		ResponseEntity<UserProfileResource> resp = null;
		
		UserDTO user = userService.findById(userId);
		if(user == null){
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else{
			UserProfileResource resource = userProfileResourceConverter.toResource(user.getUserProfile());
			resp = new ResponseEntity<UserProfileResource>(resource, HttpStatus.OK);
		}
		
		return resp;
	}
}
