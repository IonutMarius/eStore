package ro.estore.domain.service;

import java.util.List;

import ro.estore.domain.filter.SearchProductFilter;
import ro.estore.domain.object.ProductDTO;
import ro.estore.model.entitiy.Product;

public interface ProductService extends GenericService<ProductDTO, Product, Long> {

	List<ProductDTO> findByFilter(SearchProductFilter filter);

}
