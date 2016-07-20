package ro.estore.ws.rest.converter;

import org.springframework.hateoas.ResourceSupport;

public interface GenericResourceConverter<D, R extends ResourceSupport> {
	public R toResource(D dto);

	public D toDto(R resource);
}
