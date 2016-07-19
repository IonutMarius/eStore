package ro.estore.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ro.estore.domain.converter.UserConverter;
import ro.estore.domain.object.UserDTO;
import ro.estore.model.entitiy.Address;
import ro.estore.model.entitiy.Order;
import ro.estore.model.entitiy.Product;
import ro.estore.model.entitiy.Purchase;
import ro.estore.model.entitiy.User;
import ro.estore.model.entitiy.UserProfile;

@Component
public class TestUtils {

	// User
	private String DEFAULT_USERNAME;
	private String DEFAULT_PASSWORD;
	// UserProfile
	private String DEFAULT_NAME;
	private String DEFAULT_SURNAME;
	private String DEFAULT_PHONE_NUMBER;
	private String DEFAULT_EMAIL_ADDRESS;
	// Address
	private String DEFAULT_ADDRESS_NAME;
	private String DEFAULT_CITY;
	private String DEFAULT_POSTCODE;
	private String DEFAULT_ADDRESS_LINE_1;
	private String DEFAULT_ADDRESS_LINE_2;
	// Purchase
	private Integer DEFAULT_QUANTITY;
	// Product
	private String DEFAULT_PRODUCT_NAME;
	private String DEFAULT_DESCRIPTION ;
	private Double DEFAULT_PRICE;
	private Integer DEFAULT_STOCK;
	private String DEFAULT_BRAND;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private Environment env;
	
	@PostConstruct
	public void init(){
		// User
		DEFAULT_USERNAME = env.getProperty("default.util.username");
		DEFAULT_PASSWORD = env.getProperty("default.util.username");
		// UserProfile
		DEFAULT_NAME = env.getProperty("default.util.name");
		DEFAULT_SURNAME = env.getProperty("default.util.surname");
		DEFAULT_PHONE_NUMBER =  env.getProperty("default.util.phone_number");
		DEFAULT_EMAIL_ADDRESS = env.getProperty("default.util.email_address");
		// Address
		DEFAULT_ADDRESS_NAME = env.getProperty("default.util.email_address");
		DEFAULT_CITY = env.getProperty("default.util.city");
		DEFAULT_POSTCODE = env.getProperty("default.util.postcode");
		DEFAULT_ADDRESS_LINE_1 = env.getProperty("default.util.addr_line_1");
		DEFAULT_ADDRESS_LINE_2 = env.getProperty("default.util.addr_line_2");
		// Purchase
		DEFAULT_QUANTITY = Integer.valueOf(env.getProperty("default.util.quantity"));
		// Product
		DEFAULT_PRODUCT_NAME = env.getProperty("default.util.product_name");
		DEFAULT_DESCRIPTION = env.getProperty("default.util.description");
		DEFAULT_PRICE = Double.valueOf(env.getProperty("default.util.price"));
		DEFAULT_STOCK = Integer.valueOf(env.getProperty("default.util.stock"));
		DEFAULT_BRAND =  env.getProperty("default.util.brand");
	}

	public User createUser(String sufix) {
		User user = new User();
		user.setUsername(DEFAULT_USERNAME + sufix);
		user.setPassword(DEFAULT_PASSWORD + sufix);
		user.setUserProfile(createUserProfile(sufix));

		Order order1 = createOrderWithoutUserAndAddress();
		order1.setAddress(user.getUserProfile().getAddresses().get(0));
		Order order2 = createOrderWithoutUserAndAddress();
		order2.setAddress(user.getUserProfile().getAddresses().get(0));
		user.getOrders().add(order1);
		user.getOrders().add(order2);

		return user;
	}

	public UserDTO createUserDTO(String sufix) {
		return userConverter.toDto(createUser(sufix));
	}

	public User createUserWithNoRelation(String sufix) {
		User user = new User();
		user.setUsername(DEFAULT_USERNAME + sufix);
		user.setPassword(DEFAULT_PASSWORD + sufix);

		return user;
	}

	public UserProfile createUserProfile(String sufix) {
		UserProfile userProfile = new UserProfile();
		userProfile.setName(DEFAULT_NAME + sufix);
		userProfile.setSurname(DEFAULT_SURNAME + sufix);
		userProfile.setPhoneNumber(DEFAULT_PHONE_NUMBER + sufix);
		userProfile.setEmailAddress(DEFAULT_EMAIL_ADDRESS + sufix);

		Address address = createAddressWithNoRelation(sufix);
		Address address2 = createAddressWithNoRelation(sufix + "_2");

		userProfile.getAddresses().add(address);
		userProfile.getAddresses().add(address2);

		return userProfile;
	}

	public UserProfile createUserProfileWithNoRelation(String sufix) {
		UserProfile userProfile = new UserProfile();
		userProfile.setName(DEFAULT_NAME + sufix);
		userProfile.setSurname(DEFAULT_SURNAME + sufix);
		userProfile.setPhoneNumber(DEFAULT_PHONE_NUMBER + sufix);
		userProfile.setEmailAddress(DEFAULT_EMAIL_ADDRESS + sufix);

		return userProfile;
	}

	public Address createAddress(String sufix) {
		Address address = new Address();
		address.setAddressName(DEFAULT_ADDRESS_NAME + sufix);
		address.setCity(DEFAULT_CITY + sufix);
		address.setPostcode(DEFAULT_POSTCODE + sufix);
		address.setAddressLine1(DEFAULT_ADDRESS_LINE_1 + sufix);
		address.setAddressLine2(DEFAULT_ADDRESS_LINE_2 + sufix);

		return address;
	}

	public Address createAddressWithNoRelation(String sufix) {
		Address address = new Address();
		address.setAddressName(DEFAULT_ADDRESS_NAME + sufix);
		address.setCity(DEFAULT_CITY + sufix);
		address.setPostcode(DEFAULT_POSTCODE + sufix);
		address.setAddressLine1(DEFAULT_ADDRESS_LINE_1 + sufix);
		address.setAddressLine2(DEFAULT_ADDRESS_LINE_2 + sufix);

		return address;
	}

	public Order createOrder(String sufix) {
		Order order = new Order();

		Purchase purchase1 = createPurchaseWithNoRelation();
		Purchase purchase2 = createPurchaseWithNoRelation();
		order.getPurchases().add(purchase1);
		order.getPurchases().add(purchase2);

		User user = createUserWithNoRelation(sufix);
		user.getOrders().add(order);
		UserProfile userProfile = createUserProfile(sufix);
		user.setUserProfile(userProfile);

		Address address = createAddressWithNoRelation(sufix);
		order.setAddress(address);

		return order;
	}

	public Order createOrderWithoutUserAndAddress() {
		Order order = new Order();

		Purchase purchase1 = createPurchaseWithNoRelation();
		Purchase purchase2 = createPurchaseWithNoRelation();
		order.getPurchases().add(purchase1);
		order.getPurchases().add(purchase2);

		return order;
	}

	public Purchase createPurchase(String sufix) {
		User user = createUser(sufix);

		return user.getOrders().get(0).getPurchases().get(0);
	}

	public Purchase createPurchaseWithNoRelation() {
		Purchase purchase = new Purchase();
		purchase.setQuantity(DEFAULT_QUANTITY);

		return purchase;
	}

	public Product createProduct(String sufix) {
		Product product = new Product();
		product.setName(DEFAULT_PRODUCT_NAME + sufix);
		product.setDescription(DEFAULT_DESCRIPTION + sufix);
		product.setPrice(DEFAULT_PRICE);
		product.setStock(DEFAULT_STOCK);
		product.setBrand(DEFAULT_BRAND);

		return product;
	}
}
