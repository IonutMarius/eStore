package ro.estore.model.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ro.estore.model.entitiy.User;
import ro.estore.model.repository.UserRepository;

@Repository
public class UserRepositoryJpaImpl extends GenericRepositoryJpaImpl<User, Long> implements UserRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryJpaImpl.class);

	@Override
	public User findByUsername(String username) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(entityClass);
		Root<User> user = query.from(entityClass);
		query.select(user).where(criteriaBuilder.equal(user.get("username"), username));
		
		User foundUser = null;
		try {
			foundUser = entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.error("No user was found (" + e.getMessage() + ")");
		}
		
		return foundUser;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(entityClass);
		Root<User> user = query.from(entityClass);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(user.get("username"), username));
		predicates.add(criteriaBuilder.equal(user.get("password"), password));
		
		query.select(user).where(predicates.toArray(new Predicate[predicates.size()]));
		
		User foundUser = null;
		try {
			foundUser = entityManager.createQuery(query).getResultList().get(0);
		} catch (NoResultException | IndexOutOfBoundsException e) {
			LOGGER.error("No user was found (" + e.getMessage() + ")");
		}
		
		return foundUser;
	}

	@Override
	public User findById(User entity) {
		return this.findById(entity.getUserId());
	}

}
