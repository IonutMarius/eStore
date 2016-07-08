package ro.estore.ws.rest.controller;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ro.estore.domain.service.ProductService;
import ro.estore.ws.rest.converter.ProductResourceConverter;
import ro.estore.ws.rest.resource.ProductResource;

@RestController
@ExposesResourceFor(ProductResource.class)
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductResourceConverter productResourceConverter;

	// TODO - implement
	public ResponseEntity<ProductResource> getProduct(Long id){
		throw new NotImplementedException("To be implemented");
	}
}
