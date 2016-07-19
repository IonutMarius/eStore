package ro.estore.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.estore.domain.object.ProductDTO;
import ro.estore.domain.service.ProductService;
import ro.estore.ws.rest.converter.ProductResourceConverter;
import ro.estore.ws.rest.resource.ProductResource;

@RestController
@ExposesResourceFor(ProductResource.class)
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductResourceConverter productResourceConverter;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductResource> getProduct(@PathVariable(value = "id") Long id) {
		ResponseEntity<ProductResource> resp = null;

		ProductDTO product = productService.findById(id);
		if (product == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			ProductResource resource = productResourceConverter.toResource(product);
			resp = new ResponseEntity<ProductResource>(resource, HttpStatus.OK);
		}

		return resp;
	}
}
