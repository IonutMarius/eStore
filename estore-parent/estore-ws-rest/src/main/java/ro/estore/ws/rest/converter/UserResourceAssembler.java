package ro.estore.ws.rest.converter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ro.estore.domain.domain.UserDTO;
import ro.estore.ws.rest.controller.UserController;
import ro.estore.ws.rest.resource.UserResource;

@Service
public class UserResourceAssembler extends ResourceAssemblerSupport<UserDTO, UserResource> {

	public UserResourceAssembler() {
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
		
		Link self = linkTo(UserController.class).slash(dto.getUserId()).withSelfRel();
		resource.add(self);

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
