package ro.estore.ws.rest.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import ro.estore.domain.object.ProductDTO;
import ro.estore.ws.rest.controller.ProductController;
import ro.estore.ws.rest.resource.ProductResource;

@Service
public class ProductResourceConverter extends ResourceAssemblerSupport<ProductDTO, ProductResource>
		implements GenericResourceConverter<ProductDTO, ProductResource> {

	@SuppressWarnings("unused")
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
