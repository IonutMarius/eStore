package ro.estore.domain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.domain.converter.GenericEntityConverter;
import ro.estore.domain.object.DomainDTO;
import ro.estore.domain.service.GenericService;
import ro.estore.model.entitiy.ModelEntity;
import ro.estore.model.repository.GenericRepository;

public abstract class GenericServiceImpl<D extends DomainDTO, E extends ModelEntity, K extends Serializable>
		implements GenericService<D, E, K> {

	protected abstract GenericRepository<E, K> getRepository();

	protected abstract GenericEntityConverter<D, E> getEntityConverter();

	@Override
	@Transactional
	public D findById(K id) {
		E result = getRepository().findById(id);
		return getEntityConverter().toDto(result);
	}

	@Override
	public List<D> findAll() {
		List<E> results = getRepository().findAll();
		List<D> dtos = new ArrayList<>();

		for (E entity : results) {
			dtos.add(getEntityConverter().toDto(entity));
		}

		return dtos;
	}

	@Override
	@Transactional
	public D create(D dto) {
		E result = getRepository().create(getEntityConverter().toEntity(dto));
		return getEntityConverter().toDto(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public D update(D dto) {
		if (dto.getId() == null) {
			throw new DataIntegrityViolationException("Missing id");
		} else if (getRepository().findById((K) dto.getId()) == null) {
			throw new DataIntegrityViolationException("Entity to update not found");
		}
		E result = getRepository().update(getEntityConverter().toEntity(dto));
		return getEntityConverter().toDto(result);
	}

	@Override
	@Transactional
	public void remove(D dto) {
		getRepository().remove(getEntityConverter().toEntity(dto));
	}

	@Override
	public Integer count() {
		return getRepository().count();
	}
}
