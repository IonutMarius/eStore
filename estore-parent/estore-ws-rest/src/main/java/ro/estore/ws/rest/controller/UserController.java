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

import ro.estore.domain.exception.IncorrectAddressException;
import ro.estore.domain.exception.ProductOutOfStockException;
import ro.estore.domain.object.OrderDTO;
import ro.estore.domain.object.UserDTO;
import ro.estore.domain.service.UserService;
import ro.estore.ws.rest.converter.OrderResourceConverter;
import ro.estore.ws.rest.converter.UserResourceConverter;
import ro.estore.ws.rest.resource.OrderResource;
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

	@Autowired
	private OrderResourceConverter orderResourceConverter;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserResource> getUser(@PathVariable(value = "id") Long id) {
		UserDTO userDto = userService.findById(id);
		UserResource resource = userResourceConverter.toResource(userDto);

		return ResponseEntity.ok(resource);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<UserResource> createUser(@RequestBody UserResource request) {
		UserDTO userDto = userResourceConverter.toDto(request);
		ResponseEntity<UserResource> resp;

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
			resource.setMessage(errMsg);
			resp = new ResponseEntity<>(resource, HttpStatus.CONFLICT);
		}

		return resp;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<UserResource> updateUser(@RequestBody UserResource request) {
		UserDTO userDto = userResourceConverter.toDto(request);
		ResponseEntity<UserResource> resp;

		try {
			userDto = userService.update(userDto);
			UserResource resource = userResourceConverter.toResource(userDto);
			resp = ResponseEntity.ok(resource);
		} catch (DataIntegrityViolationException e) {
			LOGGER.error(e.getMessage(), e);
			UserResource resource = new UserResource();
			resource.setMessage(e.getMessage());
			resp = new ResponseEntity<>(resource, HttpStatus.BAD_REQUEST);
		}

		return resp;
	}

	@RequestMapping(value = "/{userId}/orders", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<OrderResource> checkoutOrder(@RequestBody OrderResource order, @PathVariable Long userId) {
		ResponseEntity<OrderResource> resp;

		OrderDTO orderDto = orderResourceConverter.toDto(order);
		try {
			orderDto = userService.addOrder(userId, orderDto);
			OrderResource resource = orderResourceConverter.toResource(orderDto);
			resource.setMessage("Success");
			resp = new ResponseEntity<>(resource, HttpStatus.CREATED);
		} catch (IncorrectAddressException e) {
			String errorMsg = "The address provided is incorrect";
			LOGGER.error(errorMsg, e);
			OrderResource resource = new OrderResource();
			resource.setMessage(errorMsg);
			resp = new ResponseEntity<>(resource, HttpStatus.BAD_REQUEST);
		} catch (ProductOutOfStockException e) {
			String errorMsg = "Insufficient stock for " + e.getProduct().getName();
			LOGGER.error(errorMsg, e);
			OrderResource resource = new OrderResource();
			resource.setMessage(errorMsg);
			resp = new ResponseEntity<>(resource, HttpStatus.BAD_REQUEST);
		}

		return resp;
	}
}
