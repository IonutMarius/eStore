package ro.estore.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.estore.domain.domain.OrderDTO;
import ro.estore.domain.domain.PurchaseDTO;
import ro.estore.model.entitiy.Order;
import ro.estore.model.entitiy.Purchase;

@Component
public class OrderConverter implements GenericEntityConverter<OrderDTO, Order> {

	@Autowired
	private AddressConverter addressConverter;
	
	@Autowired
	private PurchaseConverter purchaseConverter;

	@Override
	public OrderDTO toDto(Order entity) {
		OrderDTO dto = null;
		if (entity != null) {
			dto = new OrderDTO();
			dto.setAddress(addressConverter.toDto(entity.getAddress()));
			dto.setId(entity.getId());
			for (Purchase purchase : entity.getPurchases()) {
				dto.getPurchases().add(purchaseConverter.toDto(purchase));
			}
		}

		return dto;
	}

	@Override
	public Order toEntity(OrderDTO dto) {
		Order entity = null;
		if (dto != null) {
			entity = new Order();
			entity.setAddress(addressConverter.toEntity(dto.getAddress()));
			entity.setId(dto.getId());
			for (PurchaseDTO purchase : dto.getPurchases()) {
				Purchase purchaseEnt = purchaseConverter.toEntity(purchase);
				purchaseEnt.setOrder(entity);
				entity.getPurchases().add(purchaseEnt);
			}
		}

		return entity;
	}

}
