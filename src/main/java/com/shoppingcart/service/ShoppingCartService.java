package com.shoppingcart.service;

import java.util.List;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.exception.DataNotFoundException;

public interface ShoppingCartService {

	
	/**
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 * @throws DataNotFoundException
	 */
	List<Cart> getCartItems(String userId, Long productId) throws DataNotFoundException;
}
