package ro.estore.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.estore.domain.domain.UserDTO;
import ro.estore.domain.service.UserService;
import ro.estore.ws.rest.converter.UserResourceAssembler;
import ro.estore.ws.rest.resource.UserResource;

@RestController
@ExposesResourceFor(UserResource.class)
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserResourceAssembler userResourceAssembler;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserResource> getUser(@PathVariable(value = "id") Long id) {
		UserDTO userDto = userService.findById(id);
		UserResource resource = userResourceAssembler.toResource(userDto);
		
		return ResponseEntity.ok(resource);
	}
}
