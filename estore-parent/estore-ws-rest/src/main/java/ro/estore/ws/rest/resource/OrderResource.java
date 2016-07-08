package ro.estore.ws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "order", collectionRelation = "orders")
public class OrderResource extends ResourceSupport {
	private Long orderId;
	private AddressResource address;
	private List<PurchaseResource> purchase = new ArrayList<>();

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

}
