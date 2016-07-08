package ro.estore.ws.soap.util;

import java.util.ArrayList;
import java.util.Arrays;

import ro.estore.domain.domainObj.AddressDTO;
import ro.estore.domain.domainObj.OrderDTO;
import ro.estore.domain.domainObj.ProductDTO;
import ro.estore.domain.domainObj.PurchaseDTO;
import ro.estore.domain.domainObj.UserDTO;
import ro.estore.domain.domainObj.UserProfileDTO;
import ro.estore.domain.filter.SearchProductFilter;
import ro.estore.ws.soap.store.OrderXml;
import ro.estore.ws.soap.store.ProductXml;
import ro.estore.ws.soap.store.PurchaseXml;
import ro.estore.ws.soap.store.SearchProductFilterXml;
import ro.estore.ws.soap.user.AddressXml;
import ro.estore.ws.soap.user.UserProfileXml;
import ro.estore.ws.soap.user.UserXml;

public class ConverterUtils {

	private ConverterUtils() {
	}

	// XML to Entity
	public static UserDTO convertUserDTOXmlToUser(UserXml userXml) {
		UserDTO user = new UserDTO();
		user.setUsername(userXml.getUsername());
		user.setPassword(userXml.getPassword());
		UserProfileXml userProfileXml = userXml.getUserProfile();
		UserProfileDTO userProfile = convertUserProfileXmlToUserProfileDTO(userProfileXml);
		user.setUserProfile(userProfile);

		return user;
	}

	public static UserProfileDTO convertUserProfileXmlToUserProfileDTO(UserProfileXml userProfileXml) {
		UserProfileDTO userProfile = new UserProfileDTO();
		userProfile.setName(userProfileXml.getName());
		userProfile.setSurname(userProfileXml.getSurname());
		userProfile.setPhoneNumber(userProfileXml.getPhoneNumber());
		userProfile.setEmailAddress(userProfileXml.getEmailAddress());
		for (AddressXml addressXml : userProfileXml.getAddresses()) {
			AddressDTO address = convertAddressXmlToAddressDTO(addressXml);
			userProfile.getAddresses().add(address);
		}

		return userProfile;
	}

	public static AddressDTO convertAddressXmlToAddressDTO(AddressXml addressXml) {
		AddressDTO address = new AddressDTO();
		address.setAddressName(addressXml.getAddressName());
		address.setCity(addressXml.getCity());
		address.setPostcode(addressXml.getPostcode());
		address.setAddressLine1(addressXml.getAddressLine1());
		address.setAddressLine2(addressXml.getAddressLine2());

		return address;
	}

	public static ProductDTO convertProductXmlToProductDTO(ProductXml productXml) {
		ProductDTO product = new ProductDTO();
		product.setId(productXml.getProductId());
		product.setDescription(productXml.getDescription());
		product.setName(productXml.getName());
		product.setBrand(productXml.getBrand());
		product.setPrice(productXml.getPrice());
		product.setStock(productXml.getQuantity());

		return product;
	}

	public static SearchProductFilter convertSearchProductFilterXmlToSearchProductFilter(SearchProductFilterXml xml) {
		SearchProductFilter filter = new SearchProductFilter();
		if (xml != null) {
			filter.setPriceMax(xml.getPriceMax());
			filter.setPriceMin(xml.getPriceMin());
			if (xml.getKeywords() != null) {
				filter.setKeywords(new ArrayList<String>(Arrays.asList(xml.getKeywords().split(" "))));
			}
		}

		return filter;
	}

	public static OrderDTO convertOrderXmlToOrderDTO(OrderXml xml) {
		OrderDTO dto = new OrderDTO();

		AddressDTO addrDto = new AddressDTO();
		addrDto.setId(xml.getAddressId());
		dto.setAddress(addrDto);

		for (PurchaseXml purchaseXml : xml.getPurchase()) {
			PurchaseDTO purchaseDto = new PurchaseDTO();
			ProductDTO prodDto = new ProductDTO();
			prodDto.setId(purchaseXml.getProductId());
			purchaseDto.setProduct(prodDto);
			purchaseDto.setQuantity(purchaseXml.getQuantity());
			dto.getPurchases().add(purchaseDto);
		}

		return dto;
	}

	// Entity to XML
	public static UserXml convertUserDTOToUserXml(UserDTO user) {
		UserXml userXml = new UserXml();
		userXml.setUserId(user.getId());
		userXml.setUsername(user.getUsername());
		userXml.setPassword(user.getPassword());
		UserProfileDTO userProfile = user.getUserProfile();
		UserProfileXml userProfileXml = convertUserProfileDTOToUserProfileXml(userProfile);
		userXml.setUserProfile(userProfileXml);

		return userXml;
	}

	public static UserProfileXml convertUserProfileDTOToUserProfileXml(UserProfileDTO userProfile) {
		UserProfileXml userProfileXml = new UserProfileXml();
		userProfileXml.setName(userProfile.getName());
		userProfileXml.setSurname(userProfile.getSurname());
		userProfileXml.setPhoneNumber(userProfile.getPhoneNumber());
		userProfileXml.setEmailAddress(userProfile.getEmailAddress());
		for (AddressDTO address : userProfile.getAddresses()) {
			AddressXml addressXml = convertAddressDTOToAddressXml(address);
			userProfileXml.getAddresses().add(addressXml);
		}

		return userProfileXml;
	}

	public static AddressXml convertAddressDTOToAddressXml(AddressDTO address) {
		AddressXml addressXml = new AddressXml();
		addressXml.setAddressName(address.getAddressName());
		addressXml.setCity(address.getCity());
		addressXml.setPostcode(address.getPostcode());
		addressXml.setAddressLine1(address.getAddressLine1());
		addressXml.setAddressLine2(address.getAddressLine2());

		return addressXml;
	}

	public static ProductXml convertProductDTOToProductXml(ProductDTO product) {
		ProductXml productXml = new ProductXml();
		productXml.setProductId(product.getId());
		productXml.setDescription(product.getDescription());
		productXml.setName(product.getName());
		productXml.setBrand(product.getBrand());
		productXml.setPrice(product.getPrice());
		productXml.setQuantity(product.getStock());

		return productXml;
	}
}
