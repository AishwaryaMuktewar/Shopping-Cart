package com.shoppingcart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.shoppingcart.util.EntityMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shoppingcart.dto.OrderDto;
import com.shoppingcart.dto.Product;
import com.shoppingcart.entity.Cart;
import com.shoppingcart.exception.DataNotFoundException;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.helper.ShoppingCartServiceHelper;
import com.shoppingcart.repository.CartRepository;
import com.shoppingcart.util.Constant;

import javax.transaction.Transactional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ShoppingCartServiceHelper shoppingCartServiceHelper;
	
	@Value("${create.order.service.url:}")
	private String creteaOrderServiceUrl;

    /**
     * If userId or productId exist, get items from Cart by userId or productId or both. 
     * If not, get all the items from Cart. If nothing
     * exist, thrown an error message.
     */
	@Override
	public List<Cart> getCartItems(String userId, Long productId) throws DataNotFoundException {
		List<Cart> cartItems = new ArrayList<>();
 		if(StringUtils.isNotBlank(userId) && Objects.nonNull(productId)) {
 			cartItems = cartRepository.findByUserIdAndProductId(userId, productId);
		}
 		
 		else if(StringUtils.isNotBlank(userId)) {
 			cartItems = cartRepository.findByUserId(userId);
		}
 		
 		else if(Objects.nonNull(productId)) {
 			cartItems = cartRepository.findByProductId(productId);
		}
 		
 		else {
 			cartItems = cartRepository.findAll();
 		}
 		
 		if(CollectionUtils.isEmpty(cartItems)) {
 			throw new DataNotFoundException(Constant.DATA_NOT_FOUND_IN_CART_ERROR_MESSGAE);
 		}
 		return cartItems;
	}
	@Override
	public void addProduct(com.shoppingcart.dto.Cart cart) {
		cartRepository.save(new EntityMapper().convertDtoToEntity(cart));

	}

	/**
	 * remove items from Cart by userId and productId.
	 * If data does not exist for the given input, thrown an error message.
	 */
	@Override
	@Transactional
	public void removeCartItems(String userId, Long productId) throws DataNotFoundException {
			if(CollectionUtils.isEmpty(cartRepository.findByUserIdAndProductId(userId, productId))){
				throw new DataNotFoundException(Constant.DATA_NOT_FOUND_IN_CART_ERROR_MESSGAE);
			}
		cartRepository.deleteByUserIdAndProductId(userId, productId);
	}
	
	
	/**
	 * Get all the items from Cart and call create order service
	 * to create Order. If no data available in Cart throw an 
	 * error message.
	 */
	@Override
	public OrderDto checkoutAndCreateOrder() throws ShoppingCartException {
		List<Product> productList = new ArrayList<> ();
		OrderDto orderDto = new OrderDto();
		ResponseEntity<OrderDto> orderDtoResponseEntity = null;
		List<Cart> cartItems = cartRepository.findAll();
		if(CollectionUtils.isEmpty(cartItems)) {
			throw new ShoppingCartException(HttpStatus.NOT_FOUND.value(), Constant.CART_EMPTY_ERROR_MESSGAE);
		}
		for(Cart  cart: cartItems) {
			Product product = new Product();
			product.setProdId(cart.getProductId());
			product.setProdName(cart.getProductName());
			product.setProdPrice(cart.getProductPrice().longValue());
			productList.add(product);
		}
		Cart cart = cartItems.stream().findFirst().get();
		orderDto.setPrice(cart.getProductPrice().longValue());
		orderDto.setQty(cart.getProductQuantity());
		orderDto.setDateTime(new Date());
		orderDto.setProducts(productList);
		try {
			orderDtoResponseEntity = shoppingCartServiceHelper.createOrder(orderDto, creteaOrderServiceUrl);
		} catch (Exception ex) {
			throw new ShoppingCartException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		}
		return orderDtoResponseEntity.getBody();
	}
}
