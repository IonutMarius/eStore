package ro.estore.ws.rest.converter;

public interface GenericResourceConverter<RESOURCE, DTO> {
	public RESOURCE toResource(DTO dto);
	public DTO toDto(RESOURCE resource);
}
