package ro.estore.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.estore.domain.converter.OrderConverter;
import ro.estore.domain.domainObj.OrderDTO;
import ro.estore.domain.service.OrderService;
import ro.estore.model.entitiy.Order;
import ro.estore.model.repository.OrderRepository;

@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderDTO, Order, Long> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderConverter orderConverter;

	@Override
	protected OrderRepository getRepository() {
		return this.orderRepository;
	}

	@Override
	protected OrderConverter getEntityConverter() {
		return this.orderConverter;
	}

}
