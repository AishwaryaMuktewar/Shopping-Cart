package com.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	@PostMapping("/add-to-kart")
	public ResponseEntity<com.shoppingcart.dto.Cart> addProductToCart(@RequestBody com.shoppingcart.dto.Cart cart) {
		ShoppingCartService.addProduct(cart);
		return new ResponseEntity<>(cart, HttpStatus.CREATED);
	}

}
