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

import ro.estore.domain.object.OrderDTO;
import ro.estore.domain.object.PurchaseDTO;
import ro.estore.domain.object.UserDTO;
import ro.estore.domain.service.ProductService;
import ro.estore.domain.service.UserService;
import ro.estore.model.config.JpaHibernateTestConfig;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class UserServiceImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

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
		UserDTO user = testUtils.createUserDTO("_1");
		for (OrderDTO order : user.getOrders()) {
			for (PurchaseDTO purchase : order.getPurchases()) {
				// problem caused by the converter -
				// instead of persisting the already existing object, a new
				// instance is created
				purchase.setProduct(productService.findById(DEFAULT_ID));
			}
		}
		user = userService.create(user);

		Assert.assertNotNull(user);
		Assert.assertNotEquals(user.getOrders().size(), 0);
	}

	@Test
	public void findUserTest() {
		UserDTO user = userService.findById(DEFAULT_ID);

		Assert.assertNotNull(user);
	}

	@Test
	@Transactional
	public void deleteUserTest() {
		UserDTO user = userService.findById(DEFAULT_ID);
		userService.remove(user);
		user = userService.findById(DEFAULT_ID);

		Assert.assertNull(user);
	}

	@Test
	@Transactional
	public void updateUserTest() {
		UserDTO expectedUser = userService.findById(DEFAULT_ID);
		expectedUser.setUsername("u_0");
		userService.update(expectedUser);
		UserDTO actualUser = userService.findById(expectedUser.getId());

		Assert.assertEquals(expectedUser, actualUser);
	}

	@Test
	public void findUserByUsernameTest() {
		UserDTO user = userService.findByUsername(DEFAULT_USERNAME);

		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByUsernameFailTest() {
		UserDTO user = userService.findByUsername(DEFAULT_USERNAME + "_0");

		Assert.assertNull(user);
	}

	@Test
	public void findUserByUsernameAndPasswordFailTest() {
		UserDTO user = userService.findByUsernameAndPassword(DEFAULT_USERNAME + "_0", DEFAULT_PASSWORD + "_0");

		Assert.assertNull(user);
	}
}
