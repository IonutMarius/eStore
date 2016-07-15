package ro.estore.model.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.model.config.JpaHibernateTestConfig;
import ro.estore.model.entitiy.Order;
import ro.estore.model.entitiy.Purchase;
import ro.estore.model.entitiy.User;
import ro.estore.model.repository.ProductRepository;
import ro.estore.model.repository.UserRepository;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
@Transactional
public class UserRepositoryJpaImplTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TestUtils testUtils;

	private static final Long DEFAULT_ID = new Long(1);
	private static final String DEFAULT_USERNAME = "user0";
	private static final String DEFAULT_PASSWORD = "pass0";

	@Test
	public void createUser() {
		User user = testUtils.createUser("_1");
		for (Order order : user.getOrders()) {
			for (Purchase purchase : order.getPurchases()) {
				purchase.setProduct(productRepository.findById(DEFAULT_ID));
			}
		}
		user = userRepository.create(user);

		Assert.assertNotNull(user);
		Assert.assertNotEquals(user.getOrders().size(), 0);
	}

	@Test
	public void findUserTest() {
		User user = userRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(user);
	}

	@Test
	public void deleteUserTest() {
		User user = userRepository.findById(DEFAULT_ID);
		userRepository.remove(user);
		user = userRepository.findById(DEFAULT_ID);

		Assert.assertNull(user);
	}

	@Test
	public void updateUserTest() {
		User expectedUser = userRepository.findById(DEFAULT_ID);
		expectedUser.setUsername("u_0");
		userRepository.update(expectedUser);
		User actualUser = userRepository.findById(expectedUser.getId());

		Assert.assertEquals(expectedUser, actualUser);
	}

	@Test
	public void findUserByUsernameTest() {
		User user = userRepository.findByUsername(DEFAULT_USERNAME);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByUsernameFailTest() {
		User user = userRepository.findByUsername(DEFAULT_USERNAME + "_0");

		Assert.assertNull(user);
	}

	@Test
	public void saveAndFindUserByUsernameAndPasswordTest() {
		User user = userRepository.findByUsernameAndPassword(DEFAULT_USERNAME, DEFAULT_PASSWORD);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByUsernameAndPasswordFailTest() {
		User user = userRepository.findByUsernameAndPassword(DEFAULT_USERNAME + "_0", DEFAULT_PASSWORD + "_0");

		Assert.assertNull(user);
	}
}
