package ro.estore.domain.service;

import ro.estore.domain.object.OrderDTO;
import ro.estore.domain.object.UserDTO;
import ro.estore.model.entitiy.User;

public interface UserService extends GenericService<UserDTO, User, Long> {

	UserDTO findByUsername(String username);

	UserDTO findByUsernameAndPassword(String username, String password);

	OrderDTO addOrder(Long userId, OrderDTO orderDto);
}
