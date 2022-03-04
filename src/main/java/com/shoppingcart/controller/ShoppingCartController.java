package com.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.exception.DataNotFoundException;
import com.shoppingcart.service.ShoppingCartServiceImpl;


@RestController
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartServiceImpl ShoppingCartService;
	
	/**
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 * @throws DataNotFoundException
	 */
	@GetMapping("/get-cart-items")
	public ResponseEntity<List<Cart>> getCartItems(@RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "productId", required = false) Long productId) throws DataNotFoundException{
		return new ResponseEntity<> (ShoppingCartService.getCartItems(userId, productId), HttpStatus.OK);
	}

}
