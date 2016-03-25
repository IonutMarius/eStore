package ro.estore.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.estore.domain.domain.AddressDTO;
import ro.estore.domain.domain.UserProfileDTO;
import ro.estore.model.entitiy.Address;
import ro.estore.model.entitiy.UserProfile;

@Component
public class UserProfileConverter implements GenericEntityConverter<UserProfileDTO, UserProfile> {

	@Autowired
	private AddressConverter addressConverter;
	
	@Override
	public UserProfileDTO toDto(UserProfile entity) {
		UserProfileDTO dto = null;
		if (entity != null) {
			dto = new UserProfileDTO();
			for(Address address : entity.getAddresses()){
				dto.getAddresses().add(addressConverter.toDto(address));
			}
			dto.setEmailAddress(entity.getEmailAddress());
			dto.setName(entity.getName());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setSurname(entity.getSurname());
			dto.setUserProfileId(entity.getUserProfileId());
		}
		return dto;
	}

	@Override
	public UserProfile toEntity(UserProfileDTO dto) {
		UserProfile entity = null;
		if (dto != null) {
			entity = new UserProfile();
			for(AddressDTO address : dto.getAddresses()){
				Address addrEnt = addressConverter.toEntity(address);
				addrEnt.setUserProfile(entity);
				entity.getAddresses().add(addrEnt);
			}
			entity.setEmailAddress(dto.getEmailAddress());
			entity.setName(dto.getName());
			entity.setPhoneNumber(dto.getPhoneNumber());
			entity.setSurname(dto.getSurname());
			entity.setUserProfileId(dto.getUserProfileId());
		}
		return entity;
	}

}
