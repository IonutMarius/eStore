package ro.estore.util;

import ro.estore.model.entitiy.Address;
import ro.estore.model.entitiy.Order;
import ro.estore.model.entitiy.Product;
import ro.estore.model.entitiy.Purchase;
import ro.estore.model.entitiy.User;
import ro.estore.model.entitiy.UserProfile;

public class TestUtils {

	// User
	private static final String DEFAULT_USERNAME = "username";
	private static final String DEFAULT_PASSWORD = "password";
	// UserProfile
	private static final String DEFAULT_NAME = "name";
	private static final String DEFAULT_SURNAME = "surname";
	private static final String DEFAULT_PHONE_NUMBER = "0123456789";
	private static final String DEFAULT_EMAIL_ADDRESS = "ceva@email.com";
	// Address
	private static final String DEFAULT_ADDRESS_NAME = "addr name";
	private static final String DEFAULT_CITY = "city";
	private static final String DEFAULT_POSTCODE = "postcode";
	private static final String DEFAULT_ADDRESS_LINE_1 = "addr line 1";
	private static final String DEFAULT_ADDRESS_LINE_2 = "addr line 2";
	// Purchase
	private static final Integer DEFAULT_QUANTITY = new Integer(23);
	// Product
	private static final String DEFAULT_PRODUCT_NAME = "prod name";
	private static final String DEFAULT_DESCRIPTION = "desc";
	private static final Double DEFAULT_PRICE = new Double(21.542);
	private static final Integer DEFAULT_STOCK = new Integer(543);

	private TestUtils() {
	}

	public static User createUser(String sufix) {
		User user = new User();
		user.setUsername(DEFAULT_USERNAME + sufix);
		user.setPassword(DEFAULT_PASSWORD + sufix);
		user.setUserProfile(createUserProfile(sufix));

		Order order1 = createOrderWithoutUserAndAddress();
		order1.setUser(user);
		order1.setAddress(user.getUserProfile().getAddresses().get(0));
		Order order2 = createOrderWithoutUserAndAddress();
		order2.setUser(user);
		order2.setAddress(user.getUserProfile().getAddresses().get(0));
		user.getOrders().add(order1);
		user.getOrders().add(order2);

		return user;
	}

	public static User createUserWithNoRelation(String sufix) {
		User user = new User();
		user.setUsername(DEFAULT_USERNAME + sufix);
		user.setPassword(DEFAULT_PASSWORD + sufix);

		return user;
	}

	public static UserProfile createUserProfile(String sufix) {
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

	public static UserProfile createUserProfileWithNoRelation(String sufix) {
		UserProfile userProfile = new UserProfile();
		userProfile.setName(DEFAULT_NAME + sufix);
		userProfile.setSurname(DEFAULT_SURNAME + sufix);
		userProfile.setPhoneNumber(DEFAULT_PHONE_NUMBER + sufix);
		userProfile.setEmailAddress(DEFAULT_EMAIL_ADDRESS + sufix);

		return userProfile;
	}

	public static Address createAddress(String sufix) {
		Address address = new Address();
		address.setAddressName(DEFAULT_ADDRESS_NAME + sufix);
		address.setCity(DEFAULT_CITY + sufix);
		address.setPostcode(DEFAULT_POSTCODE + sufix);
		address.setAddressLine1(DEFAULT_ADDRESS_LINE_1 + sufix);
		address.setAddressLine2(DEFAULT_ADDRESS_LINE_2 + sufix);

		UserProfile userProfile = createUserProfileWithNoRelation(sufix);
		address.setUserProfile(userProfile);

		return address;
	}

	public static Address createAddressWithNoRelation(String sufix) {
		Address address = new Address();
		address.setAddressName(DEFAULT_ADDRESS_NAME + sufix);
		address.setCity(DEFAULT_CITY + sufix);
		address.setPostcode(DEFAULT_POSTCODE + sufix);
		address.setAddressLine1(DEFAULT_ADDRESS_LINE_1 + sufix);
		address.setAddressLine2(DEFAULT_ADDRESS_LINE_2 + sufix);

		return address;
	}

	public static Order createOrder(String sufix) {
		Order order = new Order();

		Purchase purchase1 = createPurchaseWithNoRelation();
		Purchase purchase2 = createPurchaseWithNoRelation();
		order.getPurchases().add(purchase1);
		order.getPurchases().add(purchase2);

		User user = createUserWithNoRelation(sufix);
		user.getOrders().add(order);
		UserProfile userProfile = createUserProfile(sufix);
		user.setUserProfile(userProfile);
		order.setUser(user);

		Address address = createAddressWithNoRelation(sufix);
		address.setUserProfile(userProfile);
		order.setAddress(address);

		return order;
	}

	public static Order createOrderWithoutUserAndAddress() {
		Order order = new Order();

		Purchase purchase1 = createPurchaseWithNoRelation();
		Purchase purchase2 = createPurchaseWithNoRelation();
		order.getPurchases().add(purchase1);
		order.getPurchases().add(purchase2);

		return order;
	}

	public static Purchase createPurchase(String sufix) {
		User user = createUser(sufix);

		return user.getOrders().get(0).getPurchases().get(0);
	}

	public static Purchase createPurchaseWithNoRelation() {
		Purchase purchase = new Purchase();
		purchase.setQuantity(DEFAULT_QUANTITY);

		return purchase;
	}

	public static Product createProduct(String sufix) {
		Product product = new Product();
		product.setName(DEFAULT_PRODUCT_NAME + sufix);
		product.setDescription(DEFAULT_DESCRIPTION + sufix);
		product.setPrice(DEFAULT_PRICE);
		product.setStock(DEFAULT_STOCK);

		return product;
	}
}
