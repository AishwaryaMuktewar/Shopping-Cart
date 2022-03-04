package com.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.exception.DataNotFoundException;
import com.shoppingcart.repository.CartRepository;
import com.shoppingcart.util.Constant;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private CartRepository cartRepository;

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

}
