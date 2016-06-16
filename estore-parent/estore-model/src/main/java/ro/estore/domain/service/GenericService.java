package ro.estore.domain.service;

import java.io.Serializable;
import java.util.List;

import ro.estore.domain.domainObj.DomainDTO;
import ro.estore.model.entitiy.ModelEntity;

public interface GenericService<DTO extends DomainDTO, ENTITY extends ModelEntity, PK extends Serializable> {
	DTO findById(PK id);

	List<DTO> findAll();

	DTO create(DTO t);

	DTO update(DTO t);

	void remove(DTO t);

	Integer count();
}
