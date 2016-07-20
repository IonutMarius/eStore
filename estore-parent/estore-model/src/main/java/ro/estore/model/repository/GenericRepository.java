package ro.estore.model.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T, K extends Serializable> {

	T findById(K id);

	T create(T t);

	T update(T t);

	void remove(T t);

	Integer count();

	List<T> findAll();

	T findById(T entity);
}
