package ro.estore.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.estore.model.entitiy.Purchase;
import ro.estore.model.repository.PurchaseRepository;

@Repository
public class PurchaseRepositoryJpaImpl extends GenericRepositoryJpaImpl<Purchase, Long> implements PurchaseRepository{

	@Override
	public Purchase findById(Purchase entity) {
		return this.findById(entity.getPurchaseId());
	}

}
