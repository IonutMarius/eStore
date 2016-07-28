package ro.estore.domain.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.domain.converter.ProductConverter;
import ro.estore.domain.object.ProductDTO;
import ro.estore.domain.service.ProductService;
import ro.estore.model.config.JpaHibernateTestConfig;
import ro.estore.model.entitiy.Product;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class ProductServiceImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductConverter productConverter;

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
		Product entity = testUtils.createProduct(sufix);
		ProductDTO actualProduct = productConverter.toDto(entity);
		ProductDTO expectedProduct = productService.create(actualProduct);
		actualProduct.setId(expectedProduct.getId());

		Assert.assertEquals(expectedProduct, actualProduct);
	}

	@Test
	public void findProductTest() {
		ProductDTO product = productService.findById(DEFAULT_ID);

		Assert.assertNotNull(product);
	}

	@Test
	@Transactional
	public void deleteProductTest() {
		ProductDTO product = productService.findById(DEFAULT_ID);
		productService.remove(product);
		product = productService.findById(product.getId());

		Assert.assertNull(product);

	}

	@Test
	@Transactional
	public void updateProductTest() {
		ProductDTO expectedProduct = productService.findById(DEFAULT_ID);
		expectedProduct.setDescription("changed description_0");
		productService.update(expectedProduct);
		ProductDTO actualProduct = productService.findById(DEFAULT_ID);

		Assert.assertEquals(expectedProduct, actualProduct);

	}
}
