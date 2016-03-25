package ro.estore.domain.converter;

import org.springframework.stereotype.Component;

import ro.estore.domain.domain.ProductDTO;
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
			productDto.setProductId(entity.getProductId());
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
			product.setProductId(dto.getProductId());
			product.setStock(dto.getStock());
		}

		return product;
	}

}
