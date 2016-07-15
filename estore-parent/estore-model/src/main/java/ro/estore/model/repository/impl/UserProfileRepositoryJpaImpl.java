package ro.estore.model.repository.impl;

import org.springframework.stereotype.Repository;

import ro.estore.model.entitiy.UserProfile;
import ro.estore.model.repository.UserProfileRepository;

@Repository
public class UserProfileRepositoryJpaImpl extends GenericRepositoryJpaImpl<UserProfile, Long>
		implements UserProfileRepository {

	@Override
	public UserProfile findById(UserProfile entity) {
		return findById(entity.getId());
	}

}
