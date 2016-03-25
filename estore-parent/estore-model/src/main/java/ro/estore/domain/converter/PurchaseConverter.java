package ro.estore.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.estore.domain.domain.PurchaseDTO;
import ro.estore.model.entitiy.Purchase;

@Component
public class PurchaseConverter implements GenericEntityConverter<PurchaseDTO, Purchase> {

	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public PurchaseDTO toDto(Purchase entity) {
		PurchaseDTO dto = null;
		if(entity != null){
			dto = new PurchaseDTO();
			dto.setProduct(productConverter.toDto(entity.getProduct()));
			dto.setPurchaseId(entity.getPurchaseId());
			dto.setQuantity(entity.getQuantity());
		}
		
		return dto;
	}

	@Override
	public Purchase toEntity(PurchaseDTO dto) {
		Purchase entity = null;
		if(dto != null){
			entity = new Purchase();
			entity.setProduct(productConverter.toEntity(dto.getProduct()));
			entity.setPurchaseId(dto.getPurchaseId());
			entity.setQuantity(dto.getQuantity());
		}
		
		return entity;
	}

}
