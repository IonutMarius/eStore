package ro.estore.ws.rest.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ro.estore.domain.object.PurchaseDTO;
import ro.estore.ws.rest.controller.UserController;
import ro.estore.ws.rest.resource.PurchaseResource;

@Service
public class PurchaseResourceConverter extends ResourceAssemblerSupport<PurchaseDTO, PurchaseResource>
		implements GenericResourceConverter<PurchaseDTO, PurchaseResource> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseResourceConverter.class);
	@Autowired
	private ProductResourceConverter productResourceConverter;

	public PurchaseResourceConverter() {
		super(UserController.class, PurchaseResource.class);
	}

	@Override
	public PurchaseResource toResource(PurchaseDTO dto) {
		PurchaseResource resource = new PurchaseResource();
		resource.setProduct(productResourceConverter.toResource(dto.getProduct()));
		resource.setPurchaseId(dto.getId());
		resource.setQuantity(dto.getQuantity());

		return resource;
	}

	@Override
	public PurchaseDTO toDto(PurchaseResource resource) {
		PurchaseDTO dto = new PurchaseDTO();
		dto.setId(resource.getPurchaseId());
		dto.setProduct(productResourceConverter.toDto(resource.getProduct()));
		dto.setQuantity(resource.getQuantity());

		return dto;
	}

}
