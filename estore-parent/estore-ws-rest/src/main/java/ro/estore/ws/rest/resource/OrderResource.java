package ro.estore.ws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.core.Relation;

@Relation(value = "order", collectionRelation = "orders")
public class OrderResource extends EstoreResource {
	private Long orderId;
	private AddressResource address;
	private List<PurchaseResource> purchase = new ArrayList<>();
	private Double total;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setPurchase(List<PurchaseResource> purchase) {
		this.purchase = purchase;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public AddressResource getAddress() {
		return address;
	}

	public void setAddress(AddressResource address) {
		this.address = address;
	}

	public List<PurchaseResource> getPurchase() {
		return purchase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((purchase == null) ? 0 : purchase.hashCode());
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
		OrderResource other = (OrderResource) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (purchase == null) {
			if (other.purchase != null)
				return false;
		} else if (!purchase.equals(other.purchase))
			return false;
		return true;
	}

}
