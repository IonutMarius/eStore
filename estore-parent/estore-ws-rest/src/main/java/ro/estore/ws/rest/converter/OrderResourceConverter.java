package ro.estore.ws.rest.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ro.estore.domain.object.OrderDTO;
import ro.estore.domain.object.PurchaseDTO;
import ro.estore.ws.rest.controller.UserController;
import ro.estore.ws.rest.resource.OrderResource;
import ro.estore.ws.rest.resource.PurchaseResource;

@Service
public class OrderResourceConverter extends ResourceAssemblerSupport<OrderDTO, OrderResource>
		implements GenericResourceConverter<OrderDTO, OrderResource> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderResourceConverter.class);
	@Autowired
	private AddressResourceConverter addressResourceConverter;
	@Autowired
	private PurchaseResourceConverter purchaseResourceConverter;

	public OrderResourceConverter() {
		super(UserController.class, OrderResource.class);
	}

	@Override
	public OrderResource toResource(OrderDTO dto) {
		OrderResource resource = new OrderResource();
		resource.setAddress(addressResourceConverter.toResource(dto.getAddress()));
		resource.setOrderId(dto.getId());
		dto.getPurchases().forEach(purhcase -> {
			PurchaseResource pr = purchaseResourceConverter.toResource(purhcase);
			resource.getPurchase().add(pr);
		});

		return resource;
	}

	@Override
	public OrderDTO toDto(OrderResource resource) {
		OrderDTO dto = new OrderDTO();
		dto.setAddress(addressResourceConverter.toDto(resource.getAddress()));
		dto.setId(resource.getOrderId());
		resource.getPurchase().forEach(purchase -> {
			PurchaseDTO pdto = purchaseResourceConverter.toDto(purchase);
			dto.getPurchases().add(pdto);
		});

		return dto;
	}

}
