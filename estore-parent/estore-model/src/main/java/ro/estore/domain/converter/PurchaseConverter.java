package ro.estore.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.estore.domain.object.PurchaseDTO;
import ro.estore.model.entitiy.Purchase;

@Component
public class PurchaseConverter implements GenericEntityConverter<PurchaseDTO, Purchase> {

	@Autowired
	private ProductConverter productConverter;

	@Override
	public PurchaseDTO toDto(Purchase entity) {
		PurchaseDTO dto = null;
		if (entity != null) {
			dto = new PurchaseDTO();
			dto.setProduct(productConverter.toDto(entity.getProduct()));
			dto.setId(entity.getId());
			dto.setQuantity(entity.getQuantity());
		}

		return dto;
	}

	@Override
	public Purchase toEntity(PurchaseDTO dto) {
		Purchase entity = null;
		if (dto != null) {
			entity = new Purchase();
			entity.setProduct(productConverter.toEntity(dto.getProduct()));
			entity.setId(dto.getId());
			entity.setQuantity(dto.getQuantity());
		}

		return entity;
	}

}
