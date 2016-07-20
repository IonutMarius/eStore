package ro.estore.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.estore.domain.converter.UserProfileConverter;
import ro.estore.domain.object.UserProfileDTO;
import ro.estore.domain.service.UserProfileService;
import ro.estore.model.entitiy.UserProfile;
import ro.estore.model.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl extends AbstractGenericServiceImpl<UserProfileDTO, UserProfile, Long>
		implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserProfileConverter userProfileConverter;

	@Override
	protected UserProfileRepository getRepository() {
		return this.userProfileRepository;
	}

	@Override
	protected UserProfileConverter getEntityConverter() {
		return this.userProfileConverter;
	}

}
