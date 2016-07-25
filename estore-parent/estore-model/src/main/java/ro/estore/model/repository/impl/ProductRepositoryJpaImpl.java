package ro.estore.model.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ro.estore.domain.filter.SearchProductFilter;
import ro.estore.model.entitiy.Product;
import ro.estore.model.repository.ProductRepository;

@Repository
public class ProductRepositoryJpaImpl extends AbstractGenericRepositoryJpaImpl<Product, Long> implements ProductRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryJpaImpl.class);

	private static final String PRICE = "price";
	private static final String NAME = "name";
	private static final String BRAND = "brand";
	private static final String DESCRIPTION = "description";

	@Override
	public Product findById(Product entity) {
		return this.findById(entity.getId());
	}

	@Override
	public Product findMatching(Product entity) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder.createQuery(entityClass);
		Root<Product> product = query.from(entityClass);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(product.get(NAME), entity.getName()));
		predicates.add(criteriaBuilder.equal(product.get(BRAND), entity.getBrand()));
		predicates.add(criteriaBuilder.equal(product.get(DESCRIPTION), entity.getDescription()));
		predicates.add(criteriaBuilder.equal(product.<Double> get(PRICE), entity.getPrice()));

		query.select(product).where(predicates.toArray(new Predicate[predicates.size()]));

		Product foundProduct = null;
		try {
			foundProduct = entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.warn("No product was found (" + e.getMessage() + ")");
			LOGGER.trace("Exception stack trace", e);
		}

		return foundProduct;
	}

	@Override
	public List<Product> findByFilter(SearchProductFilter filter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder.createQuery(entityClass);
		Root<Product> product = query.from(entityClass);
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getPriceMin() != null && filter.getPriceMax() != null) {
			predicates.add(
					criteriaBuilder.between(product.<Double> get(PRICE), filter.getPriceMin(), filter.getPriceMax()));
		} else if (filter.getPriceMin() != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(product.<Double> get(PRICE), filter.getPriceMin()));
		} else if (filter.getPriceMax() != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(product.<Double> get(PRICE), filter.getPriceMax()));
		}

		if (!filter.getKeywords().isEmpty()) {
			List<Predicate> keywordPreds = new ArrayList<>();
			for (String keyword : filter.getKeywords()) {
				StringBuilder sbLike = new StringBuilder();
				sbLike.append("%");
				sbLike.append(keyword);
				sbLike.append("%");
				String strLike = sbLike.toString();

				Predicate namePredicate = criteriaBuilder.like(product.get(NAME), strLike);
				Predicate brandPredicate = criteriaBuilder.like(product.get(BRAND), strLike);
				Predicate descPredicate = criteriaBuilder.like(product.get(DESCRIPTION), strLike);
				keywordPreds.add(criteriaBuilder.or(namePredicate, descPredicate, brandPredicate));
			}
			predicates.add(criteriaBuilder.and(keywordPreds.toArray(new Predicate[predicates.size()])));
		}

		query.select(product).where(predicates.toArray(new Predicate[predicates.size()]));

		List<Product> foundProducts = null;
		try {
			foundProducts = entityManager.createQuery(query).getResultList();
		} catch (NoResultException e) {
			LOGGER.error("No product was found", e);
		}

		return foundProducts;
	}

}
