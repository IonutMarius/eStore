package ro.estore.ws.rest.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.estore.domain.domain.UserDTO;
import ro.estore.ws.rest.resource.UserResource;

@Service
public class UserResourceConverter implements GenericResourceConverter<UserResource, UserDTO> {

	@Autowired
	private UserProfileResourceConverter userProfileConverter;

	@Override
	public UserResource toResource(UserDTO dto) {
		UserResource resource = new UserResource();
		resource.setUserId(dto.getUserId());
		resource.setUsername(dto.getUsername());
		resource.setPassword(dto.getPassword());
		resource.setUserProfile(userProfileConverter.toResource(dto.getUserProfile()));

		return resource;
	}

	@Override
	public UserDTO toDto(UserResource resource) {
		UserDTO dto = new UserDTO();
		dto.setUserId(resource.getUserId());
		dto.setUsername(resource.getUsername());
		dto.setPassword(resource.getPassword());
		dto.setUserProfile(userProfileConverter.toDto(resource.getUserProfile()));

		return dto;
	}

}
