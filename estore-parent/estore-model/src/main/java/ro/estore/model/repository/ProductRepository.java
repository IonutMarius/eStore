package ro.estore.model.repository;

import java.util.List;

import ro.estore.domain.filter.SearchProductFilter;
import ro.estore.model.entitiy.Product;

public interface ProductRepository extends GenericRepository<Product, Long> {

	Product findMatching(Product entity);
	List<Product> findByFilter(SearchProductFilter filter);

}
