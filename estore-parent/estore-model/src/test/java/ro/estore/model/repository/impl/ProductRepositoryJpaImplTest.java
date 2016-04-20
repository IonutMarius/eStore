package ro.estore.model.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.model.config.JpaHibernateTestConfig;
import ro.estore.model.entitiy.Product;
import ro.estore.model.repository.ProductRepository;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class ProductRepositoryJpaImplTest {

	@Autowired
	private ProductRepository productRepository;

	private String sufix = "_1";
	
	private static final Long DEFAULT_ID = new Long(0);

	@Test
	public void createProductTest(){
		Product expectedProduct = TestUtils.createProduct(sufix);
		Product actualProduct = productRepository.create(expectedProduct);
		
		Assert.assertEquals(expectedProduct, actualProduct);
	}
	
	@Test
	public void findProductTest() {
		Product product = productRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(product);
	}

	@Test
	public void deleteProductTest() {
		Product product = productRepository.findById(DEFAULT_ID);
		productRepository.remove(product);
		product = productRepository.findById(product.getId());

		Assert.assertNull(product);

	}

	@Test
	public void updateProductTest() {
		Product expectedProduct = productRepository.findById(DEFAULT_ID);
		expectedProduct = productRepository.create(expectedProduct);
		expectedProduct.setDescription("changed description_0");
		productRepository.update(expectedProduct);
		Product actualProduct = productRepository.findById(DEFAULT_ID);

		Assert.assertEquals(expectedProduct, actualProduct);

	}
}
