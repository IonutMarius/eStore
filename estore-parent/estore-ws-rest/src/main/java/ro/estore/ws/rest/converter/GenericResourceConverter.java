package ro.estore.ws.rest.converter;

import org.springframework.hateoas.ResourceSupport;

public interface GenericResourceConverter<DTO, RESOURCE extends ResourceSupport> {
	public RESOURCE toResource(DTO dto);
	public DTO toDto(RESOURCE resource);
}
