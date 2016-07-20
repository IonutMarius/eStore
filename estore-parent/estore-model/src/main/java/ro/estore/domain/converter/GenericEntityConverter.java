package ro.estore.domain.converter;

public interface GenericEntityConverter<D, E> {
	public D toDto(E entity);

	public E toEntity(D dto);
}
