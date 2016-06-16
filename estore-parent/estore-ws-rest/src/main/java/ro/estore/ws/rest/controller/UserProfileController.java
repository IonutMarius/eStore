package ro.estore.ws.rest.controller;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ro.estore.ws.rest.resource.UserProfileResource;

@RestController
public class UserProfileController {

	// TODO - implement
	public ResponseEntity<UserProfileResource> getUserProfile(Long id){
		throw new NotImplementedException("To be implemented");
	}
}
