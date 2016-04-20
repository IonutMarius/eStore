package ro.estore.domain.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO implements DomainDTO{
	private Long id;
	private AddressDTO address;
	private List<PurchaseDTO> purchases = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long orderId) {
		this.id = orderId;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public List<PurchaseDTO> getPurchases() {
		return purchases;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((purchases == null) ? 0 : purchases.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDTO other = (OrderDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (purchases == null) {
			if (other.purchases != null)
				return false;
		} else{
			for(int i = 0; i < purchases.size(); i++){
				if (!purchases.get(i).getId().equals(other.purchases.get(i).getId()))
					return false;
			}
		}
		return true;
	}

}
