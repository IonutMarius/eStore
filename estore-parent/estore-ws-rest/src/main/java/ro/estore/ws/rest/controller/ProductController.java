package ro.estore.ws.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.estore.domain.filter.SearchProductFilter;
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
		ResponseEntity<ProductResource> resp;

		ProductDTO product = productService.findById(id);
		if (product == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			ProductResource resource = productResourceConverter.toResource(product);
			resp = new ResponseEntity<>(resource, HttpStatus.OK);
		}

		return resp;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductResource>> getSearchedProducts(@RequestParam(required = false) String keywords,
			@RequestParam(required = false) Double priceMin, @RequestParam(required = false) Double priceMax) {
		ResponseEntity<List<ProductResource>> resp;
		SearchProductFilter filter = new SearchProductFilter();

		if (!StringUtils.isEmpty(keywords)) {
			String[] keyWordsArray = StringUtils.split(keywords, ",");
			filter.setKeywords(Arrays.asList(keyWordsArray));
		}
		filter.setPriceMin(priceMin);
		filter.setPriceMax(priceMax);

		List<ProductDTO> products = productService.findByFilter(filter);
		if (products.isEmpty()) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			List<ProductResource> resourceList = new ArrayList<>();
			products.forEach(product -> {
				ProductResource resource = productResourceConverter.toResource(product);
				resourceList.add(resource);
			});
			resp = new ResponseEntity<>(resourceList, HttpStatus.OK);
		}

		return resp;
	}
}
