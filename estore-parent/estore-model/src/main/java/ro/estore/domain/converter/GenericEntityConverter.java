package ro.estore.domain.converter;

public interface GenericEntityConverter<DTO, ENTITY> {
	public DTO toDto(ENTITY entity);
	public ENTITY toEntity(DTO dto);
}
