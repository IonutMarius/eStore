package ro.estore.domain.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.domain.converter.ProductConverter;
import ro.estore.domain.domainObj.OrderDTO;
import ro.estore.domain.domainObj.ProductDTO;
import ro.estore.domain.domainObj.PurchaseDTO;
import ro.estore.domain.service.OrderService;
import ro.estore.domain.service.ProductService;
import ro.estore.model.config.JpaHibernateTestConfig;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class OrderServiceImplTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductService productService;

	@Autowired
	private TestUtils testUtils;

	private static final Long DEFAULT_ID = new Long(1);

	@Test
	public void findOrderTest() {
		OrderDTO order = orderService.findById(DEFAULT_ID);

		Assert.assertNotNull(order);
	}

	@Test
	public void deleteOrderTest() {
		OrderDTO order = orderService.findById(DEFAULT_ID);
		orderService.remove(order);
		order = orderService.findById(order.getId());

		Assert.assertNull(order);

	}

	@Test
	public void updateOrderTest() {
		OrderDTO expectedOrder = orderService.findById(DEFAULT_ID);
		PurchaseDTO purchaseDto = new PurchaseDTO();
		ProductDTO newProduct = productService.create(productConverter.toDto(testUtils.createProduct("_1")));
		purchaseDto.setProduct(newProduct);
		purchaseDto.setQuantity(2);
		expectedOrder.getPurchases().add(purchaseDto);
		orderService.update(expectedOrder);
		OrderDTO actualOrder = orderService.findById(expectedOrder.getId());

		Assert.assertEquals(expectedOrder.getPurchases().size(), actualOrder.getPurchases().size());

	}
}
