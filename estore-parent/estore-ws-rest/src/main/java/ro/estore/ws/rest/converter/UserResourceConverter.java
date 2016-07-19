package ro.estore.ws.rest.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ro.estore.domain.object.UserDTO;
import ro.estore.ws.rest.controller.UserController;
import ro.estore.ws.rest.resource.OrderResource;
import ro.estore.ws.rest.resource.UserResource;

@Service
public class UserResourceConverter extends ResourceAssemblerSupport<UserDTO, UserResource>
		implements GenericResourceConverter<UserDTO, UserResource> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(UserResourceConverter.class);

	@Autowired
	private UserProfileResourceConverter userProfileConverter;
	@Autowired
	private OrderResourceConverter orderResourceConverter;

	public UserResourceConverter() {
		super(UserController.class, UserResource.class);
	}

	@Override
	public UserResource toResource(UserDTO dto) {
		UserResource resource = new UserResource();
		resource.setUserId(dto.getId());
		resource.setUsername(dto.getUsername());
		resource.setUserProfile(userProfileConverter.toResource(dto.getUserProfile()));
		dto.getOrders().forEach(order -> {
			OrderResource or = orderResourceConverter.toResource(order);
			resource.getOrders().add(or);
		});

		return resource;
	}

	public UserDTO toDto(UserResource resource) {
		UserDTO dto = new UserDTO();
		dto.setId(resource.getUserId());
		dto.setUsername(resource.getUsername());
		dto.setPassword(resource.getPassword());
		dto.setUserProfile(userProfileConverter.toDto(resource.getUserProfile()));

		return dto;
	}

}
