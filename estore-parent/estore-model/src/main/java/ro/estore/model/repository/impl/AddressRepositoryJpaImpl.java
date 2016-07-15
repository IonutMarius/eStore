package ro.estore.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.estore.model.entitiy.Address;
import ro.estore.model.repository.AddressRepository;

@Repository
public class AddressRepositoryJpaImpl extends GenericRepositoryJpaImpl<Address, Long> implements AddressRepository {

	@Override
	public Address findById(Address entity) {
		return this.findById(entity.getId());
	}

}
