package ro.estore.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.estore.domain.object.OrderDTO;
import ro.estore.domain.object.UserDTO;
import ro.estore.model.entitiy.Order;
import ro.estore.model.entitiy.User;

@Component
public class UserConverter implements GenericEntityConverter<UserDTO, User> {

	@Autowired
	private OrderConverter orderConverter;
	@Autowired
	private UserProfileConverter userProfileConverter;

	@Override
	public UserDTO toDto(User entity) {
		UserDTO dto = null;
		if (entity != null) {
			dto = new UserDTO();
			for (Order order : entity.getOrders()) {
				OrderDTO orderDto = orderConverter.toDto(order);
				dto.getOrders().add(orderDto);
			}
			dto.setPassword(entity.getPassword());
			dto.setId(entity.getId());
			dto.setUsername(entity.getUsername());
			dto.setUserProfile(userProfileConverter.toDto(entity.getUserProfile()));
		}

		return dto;
	}

	@Override
	public User toEntity(UserDTO dto) {
		User entity = null;
		if (dto != null) {
			entity = new User();
			for (OrderDTO order : dto.getOrders()) {
				Order orderEnt = orderConverter.toEntity(order);
				entity.getOrders().add(orderEnt);
			}
			entity.setPassword(dto.getPassword());
			entity.setId(dto.getId());
			entity.setUsername(dto.getUsername());
			entity.setUserProfile(userProfileConverter.toEntity(dto.getUserProfile()));
		}

		return entity;
	}

}
