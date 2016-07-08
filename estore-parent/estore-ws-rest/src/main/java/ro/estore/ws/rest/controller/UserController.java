package ro.estore.ws.rest.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ro.estore.domain.domainObj.UserDTO;
import ro.estore.domain.service.UserService;
import ro.estore.ws.rest.converter.UserResourceConverter;
import ro.estore.ws.rest.resource.UserResource;

@RestController
@ExposesResourceFor(UserResource.class)
@RequestMapping("/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserResourceConverter userResourceConverter;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserResource> getUser(@PathVariable(value = "id") Long id) {
		UserDTO userDto = userService.findById(id);
		UserResource resource = userResourceConverter.toResource(userDto);

		return ResponseEntity.ok(resource);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<UserResource> createUser(@RequestBody UserResource request) {
		UserDTO userDto = userResourceConverter.toDto(request);
		ResponseEntity<UserResource> resp = null;

		try {
			userDto = userService.create(userDto);
			UserResource resource = userResourceConverter.toResource(userDto);
			URI createdUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(userDto.getId()).toUri();
			resp = ResponseEntity.created(createdUri).body(resource);
		} catch (DataIntegrityViolationException e) {
			String errMsg = "Username already exists";
			LOGGER.error(errMsg, e);
			UserResource resource = new UserResource();
			resp = ResponseEntity.status(HttpStatus.CONFLICT).body(resource);
		}

		return resp;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<UserResource> updateUser(@RequestBody UserResource request) {
		UserDTO userDto = userResourceConverter.toDto(request);
		ResponseEntity<UserResource> resp = null;

		try {
			userDto = userService.update(userDto);
			UserResource resource = userResourceConverter.toResource(userDto);
			resp = ResponseEntity.ok(resource);
		} catch (DataIntegrityViolationException e) {
			LOGGER.error(e.getMessage(), e);
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return resp;
	}
}
