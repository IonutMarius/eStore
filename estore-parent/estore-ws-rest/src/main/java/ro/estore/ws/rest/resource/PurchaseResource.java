package ro.estore.ws.rest.resource;

import org.springframework.hateoas.core.Relation;

@Relation(value = "purchase", collectionRelation = "purchases")
public class PurchaseResource extends EstoreResource {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((purchaseId == null) ? 0 : purchaseId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseResource other = (PurchaseResource) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (purchaseId == null) {
			if (other.purchaseId != null)
				return false;
		} else if (!purchaseId.equals(other.purchaseId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}
}
