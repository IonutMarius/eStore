package ro.estore.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.estore.model.entitiy.Order;
import ro.estore.model.repository.OrderRepository;

@Repository
public class OrderRepositoryJpaImpl extends GenericRepositoryJpaImpl<Order, Long> implements OrderRepository {

	@Override
	public Order findById(Order entity) {
		return findById(entity.getId());
	}

}
