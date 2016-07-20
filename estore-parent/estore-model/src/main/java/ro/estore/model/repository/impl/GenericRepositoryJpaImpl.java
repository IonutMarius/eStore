package ro.estore.model.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ro.estore.model.repository.GenericRepository;

public abstract class GenericRepositoryJpaImpl<T, K extends Serializable> implements GenericRepository<T, K> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericRepositoryJpaImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T findById(K id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query.select(root);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public T create(T t) {
		entityManager.persist(t);
		return t;
	}

	@Override
	public T update(T t) {
		return entityManager.merge(t);
	}

	@Override
	public void remove(T t) {
		T temp = t;
		if (!entityManager.contains(t)) {
			temp = this.findById(t);
		}
		entityManager.remove(temp);
	}

	@Override
	public Integer count() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
		Root<T> root = query.from(entityClass);
		query.multiselect(criteriaBuilder.count(root));

		return entityManager.createQuery(query).getSingleResult();
	}
}
