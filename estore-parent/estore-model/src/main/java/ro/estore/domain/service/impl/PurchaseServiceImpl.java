package ro.estore.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.estore.domain.converter.PurchaseConverter;
import ro.estore.domain.object.PurchaseDTO;
import ro.estore.domain.service.PurchaseService;
import ro.estore.model.entitiy.Purchase;
import ro.estore.model.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl extends AbstractGenericServiceImpl<PurchaseDTO, Purchase, Long> implements PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private PurchaseConverter purchaseConverter;

	@Override
	protected PurchaseRepository getRepository() {
		return this.purchaseRepository;
	}

	@Override
	protected PurchaseConverter getEntityConverter() {
		return this.purchaseConverter;
	}
}
