package ro.estore.ws.rest.converter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ro.estore.domain.domainObj.ProductDTO;
import ro.estore.ws.rest.controller.ProductController;
import ro.estore.ws.rest.resource.ProductResource;

@Service
public class ProductResourceConverter extends ResourceAssemblerSupport<ProductDTO, ProductResource>
		implements GenericResourceConverter<ProductDTO, ProductResource> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductResourceConverter.class);

	public ProductResourceConverter() {
		super(ProductController.class, ProductResource.class);
	}

	@Override
	public ProductResource toResource(ProductDTO dto) {
		ProductResource resource = new ProductResource();
		resource.setBrand(dto.getBrand());
		resource.setDescription(dto.getDescription());
		resource.setName(dto.getName());
		resource.setPrice(dto.getPrice());
		resource.setProductId(dto.getId());
		resource.setStock(dto.getStock());

		// link to self
		try {
			Method getProductMethod = ProductController.class.getMethod("getProduct", Long.class);
			Link self = linkTo(getProductMethod, dto.getId()).withSelfRel();
			resource.add(self);
		} catch (NoSuchMethodException | SecurityException e) {
			LOGGER.error("Could not create link to self from method", e);
		}

		return resource;
	}

	@Override
	public ProductDTO toDto(ProductResource resource) {
		ProductDTO dto = new ProductDTO();
		dto.setBrand(resource.getBrand());
		dto.setDescription(resource.getDescription());
		dto.setName(resource.getName());
		dto.setPrice(resource.getPrice());
		dto.setId(resource.getProductId());
		dto.setStock(resource.getStock());

		return dto;
	}

}
