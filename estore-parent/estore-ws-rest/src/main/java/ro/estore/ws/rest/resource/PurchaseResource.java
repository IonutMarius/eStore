package ro.estore.ws.rest.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "purchase", collectionRelation = "purchases")
public class PurchaseResource extends ResourceSupport {
	private Long purchaseId;
	private ProductResource product;
	private Integer quantity;

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public ProductResource getProduct() {
		return product;
	}

	public void setProduct(ProductResource product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
