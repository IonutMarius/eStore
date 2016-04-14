package ro.estore.ws.rest.converter;

import org.springframework.hateoas.ResourceSupport;

public interface GenericResourceConverter<RESOURCE extends ResourceSupport, DTO> {
	public RESOURCE toResource(DTO dto);
	public DTO toDto(RESOURCE resource);
}
