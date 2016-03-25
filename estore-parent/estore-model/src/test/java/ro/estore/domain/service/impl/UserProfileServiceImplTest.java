package ro.estore.domain.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.estore.domain.converter.UserProfileConverter;
import ro.estore.domain.domain.UserProfileDTO;
import ro.estore.domain.service.UserProfileService;
import ro.estore.model.config.JpaHibernateTestConfig;
import ro.estore.model.entitiy.UserProfile;
import ro.estore.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaHibernateTestConfig.class})
@Transactional
public class UserProfileServiceImplTest {

	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private UserProfileConverter userProfileConverter;
	
	private static final Long DEFAULT_ID = new Long(0);
	
	@Test
	public void createUserProfileTest(){
		UserProfile entity = TestUtils.createUserProfile("_1");
		UserProfileDTO userProfile = userProfileConverter.toDto(entity);
		userProfile = userProfileService.create(userProfile);
		
		Assert.assertNotNull(userProfile);
	}
	
	@Test
	public void findUserProfileTest(){
		UserProfileDTO userProfile = userProfileService.findById(DEFAULT_ID);
		
		Assert.assertNotNull(userProfile);
	}
	
	@Test
	public void deleteUserProfileTest(){
		UserProfileDTO userProfile = userProfileService.findById(DEFAULT_ID);
		userProfileService.remove(userProfile);
		userProfile = userProfileService.findById(userProfile.getUserProfileId());
		
		Assert.assertNull(userProfile);
	}
	
	@Test
	public void updateUserProfileTest(){
		UserProfileDTO expectedUserProfile = userProfileService.findById(DEFAULT_ID);
		expectedUserProfile.setName("n_0");
		userProfileService.update(expectedUserProfile);
		UserProfileDTO actualUserProfile = userProfileService.findById(expectedUserProfile.getUserProfileId());

		Assert.assertEquals(expectedUserProfile, actualUserProfile);
	}
}
