package ro.estore.domain.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.domain.domainObj.AddressDTO;
import ro.estore.domain.service.AddressService;
import ro.estore.model.config.JpaHibernateTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class AddressServiceImplTest {

	@Autowired
	private AddressService addressService;

	private static final Long DEFAULT_ID = new Long(1);

	@Test
	public void findAddressTest() {
		AddressDTO address = addressService.findById(DEFAULT_ID);

		Assert.assertNotNull(address);
	}

	@Test
	public void deleteAddressTest() {
		AddressDTO address = addressService.findById(DEFAULT_ID);
		addressService.remove(address);
		address = addressService.findById(address.getId());

		Assert.assertNull(address);
	}

	@Test
	public void updateAddressTest() {
		AddressDTO expectedAddress = addressService.findById(DEFAULT_ID);
		expectedAddress.setAddressName("addr_0");
		addressService.update(expectedAddress);
		AddressDTO actualAddress = addressService.findById(expectedAddress.getId());

		Assert.assertEquals(expectedAddress, actualAddress);
	}
}
