package ro.estore.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.estore.domain.converter.AddressConverter;
import ro.estore.domain.object.AddressDTO;
import ro.estore.domain.service.AddressService;
import ro.estore.model.entitiy.Address;
import ro.estore.model.repository.AddressRepository;

@Service
public class AddressServiceImpl extends AbstractGenericServiceImpl<AddressDTO, Address, Long> implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressConverter addressConverter;

	@Override
	protected AddressRepository getRepository() {
		return this.addressRepository;
	}

	@Override
	protected AddressConverter getEntityConverter() {
		return this.addressConverter;
	}

}
