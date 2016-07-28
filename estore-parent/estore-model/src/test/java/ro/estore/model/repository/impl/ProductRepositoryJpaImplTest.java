package ro.estore.model.repository.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.model.config.JpaHibernateTestConfig;
import ro.estore.model.entitiy.Product;
import ro.estore.model.repository.ProductRepository;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class ProductRepositoryJpaImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TestUtils testUtils;

	private String sufix = "_1";

	private Long DEFAULT_ID;

	@Before
	public void setUp() {
		DEFAULT_ID = Long.valueOf(env.getProperty("default.id"));
	}

	@Test
	@Transactional
	public void createProductTest() {
		Product expectedProduct = testUtils.createProduct(sufix);
		Product actualProduct = productRepository.create(expectedProduct);

		Assert.assertEquals(expectedProduct, actualProduct);
	}

	@Test
	public void findProductTest() {
		Product product = productRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(product);
	}

	@Test
	@Transactional
	public void deleteProductTest() {
		Product product = productRepository.findById(DEFAULT_ID);
		productRepository.remove(product);
		product = productRepository.findById(product.getId());

		Assert.assertNull(product);

	}

	@Test
	@Transactional
	public void updateProductTest() {
		Product expectedProduct = productRepository.findById(DEFAULT_ID);
		expectedProduct = productRepository.create(expectedProduct);
		expectedProduct.setDescription("changed description_0");
		productRepository.update(expectedProduct);
		Product actualProduct = productRepository.findById(DEFAULT_ID);

		Assert.assertEquals(expectedProduct, actualProduct);

	}
}
