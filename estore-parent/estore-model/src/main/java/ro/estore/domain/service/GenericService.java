package ro.estore.domain.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<DTO, ENTITY, PK extends Serializable> {
	DTO findById(PK id);
	List<DTO> findAll();
	DTO create(DTO t);
	DTO update(DTO t);
	void remove(DTO t);
	Integer count();
}
