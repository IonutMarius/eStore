package ro.estore.model.repository;

import ro.estore.model.entitiy.User;

public interface UserRepository extends GenericRepository<User, Long> {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
}
