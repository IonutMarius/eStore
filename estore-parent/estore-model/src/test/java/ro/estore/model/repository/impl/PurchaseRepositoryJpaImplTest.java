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
import ro.estore.model.entitiy.Purchase;
import ro.estore.model.repository.PurchaseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class PurchaseRepositoryJpaImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private PurchaseRepository purchaseRepository;

	private Long DEFAULT_ID;

	@Before
	public void setUp() {
		DEFAULT_ID = Long.valueOf(env.getProperty("default.id"));
	}

	@Test
	public void findPurchaseTest() {
		Purchase purchase = purchaseRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(purchase);
	}

	@Test
	@Transactional
	public void deletePurchaseTest() {
		Purchase purchase = purchaseRepository.findById(DEFAULT_ID);
		purchaseRepository.remove(purchase);
		purchase = purchaseRepository.findById(DEFAULT_ID);

		Assert.assertNull(purchase);

	}

	@Test
	@Transactional
	public void updatePurchaseTest() {
		Purchase expectedPurchase = purchaseRepository.findById(DEFAULT_ID);
		expectedPurchase.setQuantity(22);
		purchaseRepository.update(expectedPurchase);
		Purchase actualPurchase = purchaseRepository.findById(DEFAULT_ID);

		Assert.assertEquals(expectedPurchase, actualPurchase);

	}
}
