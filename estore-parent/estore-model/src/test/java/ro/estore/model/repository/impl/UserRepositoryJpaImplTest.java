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
import ro.estore.model.entitiy.Order;
import ro.estore.model.entitiy.Purchase;
import ro.estore.model.entitiy.User;
import ro.estore.model.repository.ProductRepository;
import ro.estore.model.repository.UserRepository;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class UserRepositoryJpaImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TestUtils testUtils;

	private Long DEFAULT_ID;
	private String DEFAULT_USERNAME;
	private String DEFAULT_PASSWORD;

	@Before
	public void setUp() {
		DEFAULT_ID = Long.valueOf(env.getProperty("default.id"));
		DEFAULT_USERNAME = env.getProperty("default.username");
		DEFAULT_PASSWORD = env.getProperty("default.password");
	}

	@Test
	@Transactional
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
	@Transactional
	public void deleteUserTest() {
		User user = userRepository.findById(DEFAULT_ID);
		userRepository.remove(user);
		user = userRepository.findById(DEFAULT_ID);

		Assert.assertNull(user);
	}

	@Test
	@Transactional
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
	public void findUserByUsernameAndPasswordFailTest() {
		User user = userRepository.findByUsernameAndPassword(DEFAULT_USERNAME + "_0", DEFAULT_PASSWORD + "_0");

		Assert.assertNull(user);
	}
}
