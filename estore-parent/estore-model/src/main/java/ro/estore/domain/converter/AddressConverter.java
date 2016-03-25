package ro.estore.domain.converter;

import org.springframework.stereotype.Component;

import ro.estore.domain.domain.AddressDTO;
import ro.estore.model.entitiy.Address;

@Component
public class AddressConverter implements GenericEntityConverter<AddressDTO, Address> {

	@Override
	public AddressDTO toDto(Address entity) {
		AddressDTO dto = null;
		if (entity != null) {
			dto = new AddressDTO();
			dto.setAddressId(entity.getAddressId());
			dto.setAddressLine1(entity.getAddressLine1());
			dto.setAddressLine2(entity.getAddressLine2());
			dto.setAddressName(entity.getAddressName());
			dto.setCity(entity.getCity());
			dto.setPostcode(entity.getPostcode());
		}
		return dto;
	}

	@Override
	public Address toEntity(AddressDTO dto) {
		Address entity = null;
		if (dto != null) {
			entity = new Address();
			entity.setAddressId(dto.getAddressId());
			entity.setAddressLine1(dto.getAddressLine1());
			entity.setAddressLine2(dto.getAddressLine2());
			entity.setAddressName(dto.getAddressName());
			entity.setCity(dto.getCity());
			entity.setPostcode(dto.getPostcode());
		}
		return entity;
	}

}
