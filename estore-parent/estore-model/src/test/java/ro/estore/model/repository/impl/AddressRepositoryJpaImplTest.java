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
import ro.estore.model.entitiy.Address;
import ro.estore.model.repository.AddressRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class AddressRepositoryJpaImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private AddressRepository addressRepository;

	private Long DEFAULT_ID;

	@Before
	public void setUp() {
		DEFAULT_ID = Long.valueOf(env.getProperty("default.id"));
	}

	@Test
	public void findAddressTest() {
		Address address = addressRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(address);
	}

	@Test
	@Transactional
	public void deleteAddressTest() {
		Address address = addressRepository.findById(DEFAULT_ID);
		addressRepository.remove(address);
		address = addressRepository.findById(address.getId());

		Assert.assertNull(address);
	}

	@Test
	@Transactional
	public void updateAddressTest() {
		Address expectedAddress = addressRepository.findById(DEFAULT_ID);
		expectedAddress.setAddressName("addr_0");
		addressRepository.update(expectedAddress);
		Address actualAddress = addressRepository.findById(expectedAddress.getId());

		Assert.assertEquals(expectedAddress, actualAddress);
	}
}
