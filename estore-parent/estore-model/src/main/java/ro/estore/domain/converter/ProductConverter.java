package ro.estore.domain.converter;

import org.springframework.stereotype.Component;

import ro.estore.domain.domainObj.ProductDTO;
import ro.estore.model.entitiy.Product;

@Component(value = "productConverter")
public class ProductConverter implements GenericEntityConverter<ProductDTO, Product> {

	@Override
	public ProductDTO toDto(Product entity) {
		ProductDTO productDto = null;
		if (entity != null) {
			productDto = new ProductDTO();
			productDto.setDescription(entity.getDescription());
			productDto.setName(entity.getName());
			productDto.setBrand(entity.getBrand());
			productDto.setPrice(entity.getPrice());
			productDto.setId(entity.getId());
			productDto.setStock(entity.getStock());
		}

		return productDto;
	}

	@Override
	public Product toEntity(ProductDTO dto) {
		Product product = null;
		if (dto != null) {
			product = new Product();
			product.setDescription(dto.getDescription());
			product.setName(dto.getName());
			product.setBrand(dto.getBrand());
			product.setPrice(dto.getPrice());
			product.setId(dto.getId());
			product.setStock(dto.getStock());
		}

		return product;
	}

}
