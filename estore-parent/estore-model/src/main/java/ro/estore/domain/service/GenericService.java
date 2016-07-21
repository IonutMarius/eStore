package ro.estore.domain.service;

import java.io.Serializable;
import java.util.List;

import ro.estore.domain.object.DomainDTO;
import ro.estore.model.entitiy.AbstractModelEntity;

public interface GenericService<D extends DomainDTO, E extends AbstractModelEntity, K extends Serializable> {
	D findById(K id);

	List<D> findAll();

	D create(D t);

	D update(D t);

	void remove(D t);

	Integer count();
}
