package ro.estore.domain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.domain.converter.GenericEntityConverter;
import ro.estore.domain.domainObj.DomainDTO;
import ro.estore.domain.service.GenericService;
import ro.estore.model.entitiy.ModelEntity;
import ro.estore.model.repository.GenericRepository;

public abstract class GenericServiceImpl<DTO extends DomainDTO, ENTITY extends ModelEntity, PK extends Serializable>
		implements GenericService<DTO, ENTITY, PK> {

	protected abstract GenericRepository<ENTITY, PK> getRepository();

	protected abstract GenericEntityConverter<DTO, ENTITY> getEntityConverter();

	@Override
	@Transactional
	public DTO findById(PK id) {
		ENTITY result = getRepository().findById(id);
		return getEntityConverter().toDto(result);
	}

	@Override
	public List<DTO> findAll() {
		List<ENTITY> results = getRepository().findAll();
		List<DTO> dtos = new ArrayList<>();

		for (ENTITY entity : results) {
			dtos.add(getEntityConverter().toDto(entity));
		}

		return dtos;
	}

	@Override
	@Transactional
	public DTO create(DTO dto) {
		ENTITY result = getRepository().create(getEntityConverter().toEntity(dto));
		return getEntityConverter().toDto(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public DTO update(DTO dto) {
		if(dto.getId() == null){
			throw new DataIntegrityViolationException("Missing id");
		} else if(getRepository().findById((PK) dto.getId()) == null){
			throw new DataIntegrityViolationException("Entity to update not found");
		}
		ENTITY result = getRepository().update(getEntityConverter().toEntity(dto));
		return getEntityConverter().toDto(result);
	}

	@Override
	@Transactional
	public void remove(DTO dto) {
		getRepository().remove(getEntityConverter().toEntity(dto));
	}

	@Override
	public Integer count() {
		return getRepository().count();
	}
}
