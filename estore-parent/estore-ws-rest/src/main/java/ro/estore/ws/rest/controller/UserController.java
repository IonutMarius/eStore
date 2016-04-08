package ro.estore.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.estore.domain.domain.UserDTO;
import ro.estore.domain.service.UserService;
import ro.estore.ws.rest.converter.UserResourceConverter;
import ro.estore.ws.rest.resource.UserResource;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserResourceConverter userResourceConverter;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public UserResource getUser(@PathVariable(value = "id") Long id) {
		UserDTO userDto = userService.findById(id);
		return userResourceConverter.toResource(userDto);
	}
}
