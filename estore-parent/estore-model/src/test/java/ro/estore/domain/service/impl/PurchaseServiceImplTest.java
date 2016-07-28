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

import ro.estore.domain.object.PurchaseDTO;
import ro.estore.domain.service.PurchaseService;
import ro.estore.model.config.JpaHibernateTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class PurchaseServiceImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private PurchaseService purchaseService;

	private Long DEFAULT_ID;

	@Before
	public void setUp() {
		DEFAULT_ID = Long.valueOf(env.getProperty("default.id"));
	}

	@Test
	public void findPurchaseTest() {
		PurchaseDTO purchase = purchaseService.findById(DEFAULT_ID);

		Assert.assertNotNull(purchase);
	}

	@Test
	@Transactional
	public void deletePurchaseTest() {
		PurchaseDTO purchase = purchaseService.findById(DEFAULT_ID);
		purchaseService.remove(purchase);
		purchase = purchaseService.findById(DEFAULT_ID);

		Assert.assertNull(purchase);

	}

	@Test
	@Transactional
	public void updatePurchaseTest() {
		PurchaseDTO expectedPurchase = purchaseService.findById(DEFAULT_ID);
		expectedPurchase.setQuantity(22);
		purchaseService.update(expectedPurchase);
		PurchaseDTO actualPurchase = purchaseService.findById(DEFAULT_ID);

		Assert.assertEquals(expectedPurchase, actualPurchase);

	}
}
