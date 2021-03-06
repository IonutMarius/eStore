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
import ro.estore.model.entitiy.UserProfile;
import ro.estore.model.repository.UserProfileRepository;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaHibernateTestConfig.class })
public class UserProfileRepositoryJpaImplTest {

	@Autowired
	private Environment env;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private TestUtils testUtils;

	private Long DEFAULT_ID;

	@Before
	public void setUp() {
		DEFAULT_ID = Long.valueOf(env.getProperty("default.id"));
	}

	@Test
	@Transactional
	public void createUserProfileTest() {
		UserProfile userProfile = testUtils.createUserProfile("_1");
		userProfile = userProfileRepository.create(userProfile);

		Assert.assertNotNull(userProfile);
	}

	@Test
	public void findUserProfileTest() {
		UserProfile userProfile = userProfileRepository.findById(DEFAULT_ID);

		Assert.assertNotNull(userProfile);
	}

	@Test
	@Transactional
	public void deleteUserProfileTest() {
		UserProfile userProfile = userProfileRepository.findById(DEFAULT_ID);
		userProfileRepository.remove(userProfile);
		userProfile = userProfileRepository.findById(userProfile.getId());

		Assert.assertNull(userProfile);
	}

	@Test
	@Transactional
	public void updateUserProfileTest() {
		UserProfile expectedUserProfile = userProfileRepository.findById(DEFAULT_ID);
		expectedUserProfile.setName("n_0");
		userProfileRepository.update(expectedUserProfile);
		UserProfile actualUser = userProfileRepository.findById(expectedUserProfile.getId());

		Assert.assertEquals(expectedUserProfile, actualUser);
	}
}
