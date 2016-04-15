package ro.estore.ws.rest.converter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ro.estore.domain.domain.UserDTO;
import ro.estore.ws.rest.controller.UserController;
import ro.estore.ws.rest.resource.UserResource;

@Service
public class UserResourceConverter extends ResourceAssemblerSupport<UserDTO, UserResource> implements GenericResourceConverter<UserDTO, UserResource>{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserResourceConverter.class);
	
	public UserResourceConverter() {
		super(UserController.class, UserResource.class);
	}

	@Autowired
	private UserProfileResourceConverter userProfileConverter;

	@Override
	public UserResource toResource(UserDTO dto) {
		UserResource resource = new UserResource();
		resource.setUserId(dto.getUserId());
		resource.setUsername(dto.getUsername());
		resource.setUserProfile(userProfileConverter.toResource(dto.getUserProfile()));
		
		// link to self
		try {
			Method getUserMethod = UserController.class.getMethod("getUser", Long.class);
			Link self = linkTo(getUserMethod, dto.getUserId()).withSelfRel();
			resource.add(self);
		} catch (NoSuchMethodException | SecurityException e) {
			LOGGER.error("Could not create link to self from method", e);
		}
		

		return resource;
	}

	public UserDTO toDto(UserResource resource) {
		UserDTO dto = new UserDTO();
		dto.setUserId(resource.getUserId());
		dto.setUsername(resource.getUsername());
		dto.setPassword(resource.getPassword());
		dto.setUserProfile(userProfileConverter.toDto(resource.getUserProfile()));

		return dto;
	}

}
