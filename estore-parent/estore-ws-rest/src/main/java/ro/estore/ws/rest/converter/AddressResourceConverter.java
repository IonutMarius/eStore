package ro.estore.ws.rest.converter;

import org.springframework.stereotype.Service;

import ro.estore.domain.domain.AddressDTO;
import ro.estore.ws.rest.resource.AddressResource;

@Service
public class AddressResourceConverter implements GenericResourceConverter<AddressResource, AddressDTO> {

	@Override
	public AddressResource toResource(AddressDTO dto) {
		AddressResource resource = new AddressResource();
		resource.setAddressLine1(dto.getAddressLine1());
		resource.setAddressLine2(dto.getAddressLine2());
		resource.setAddressName(dto.getAddressName());
		resource.setCity(dto.getCity());
		resource.setPostcode(dto.getPostcode());

		return resource;
	}

	@Override
	public AddressDTO toDto(AddressResource resource) {
		AddressDTO dto = new AddressDTO();
		dto.setAddressLine1(resource.getAddressLine1());
		dto.setAddressLine2(resource.getAddressLine2());
		dto.setAddressName(resource.getAddressName());
		dto.setCity(resource.getCity());
		dto.setPostcode(resource.getPostcode());

		return dto;
	}
}
