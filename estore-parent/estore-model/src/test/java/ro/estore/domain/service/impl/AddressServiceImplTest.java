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

import ro.estore.domain.object.AddressDTO;
import ro.estore.domain.service.AddressService;
import ro.estore.model.config.JpaHibernateTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class AddressServiceImplTest {
	
	@Autowired
	private Environment env;

	@Autowired
	private AddressService addressService;

	private Long DEFAULT_ID;
	
	@Before
	public void setUp(){
		DEFAULT_ID = Long.valueOf(env.getProperty("default.id"));
	}

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
