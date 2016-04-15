package ro.estore.ws.rest.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.estore.domain.domain.AddressDTO;
import ro.estore.domain.domain.UserProfileDTO;
import ro.estore.ws.rest.resource.AddressResource;
import ro.estore.ws.rest.resource.UserProfileResource;

@Service
public class UserProfileResourceConverter implements GenericResourceConverter<UserProfileDTO, UserProfileResource> {

	@Autowired
	private AddressResourceConverter addressResourceConverter;
	
	@Override
	public UserProfileResource toResource(UserProfileDTO dto) {
		UserProfileResource resource = new UserProfileResource();
		resource.setEmailAddress(dto.getEmailAddress());
		resource.setName(dto.getName());
		resource.setPhoneNumber(dto.getPhoneNumber());
		resource.setSurname(dto.getSurname());
		for(AddressDTO addrDto : dto.getAddresses()){
			AddressResource addrRes = addressResourceConverter.toResource(addrDto);
			resource.getAddresses().add(addrRes);
		}
		
		return resource;
	}

	@Override
	public UserProfileDTO toDto(UserProfileResource resource) {
		UserProfileDTO dto = new UserProfileDTO();
		dto.setEmailAddress(resource.getEmailAddress());
		dto.setName(resource.getName());
		dto.setPhoneNumber(resource.getPhoneNumber());
		dto.setSurname(resource.getSurname());
		for(AddressResource addrRes : resource.getAddresses()){
			AddressDTO addrDto = addressResourceConverter.toDto(addrRes);
			dto.getAddresses().add(addrDto);
		}
		
		return dto;
	}
}
