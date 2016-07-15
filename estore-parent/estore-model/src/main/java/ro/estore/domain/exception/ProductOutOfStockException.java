package ro.estore.domain.exception;

import ro.estore.domain.domainObj.ProductDTO;

public class ProductOutOfStockException extends RuntimeException {

	private static final long serialVersionUID = 5127701882951890193L;

	private final ProductDTO product;

	public ProductOutOfStockException(ProductDTO product) {
		super();
		this.product = product;
	}

	public ProductDTO getProduct() {
		return product;
	}
}
