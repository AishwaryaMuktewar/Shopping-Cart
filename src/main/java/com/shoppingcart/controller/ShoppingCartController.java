package com.shoppingcart.controller;

import java.util.List;

import com.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shoppingcart.dto.OrderDto;
import com.shoppingcart.entity.Cart;
import com.shoppingcart.exception.DataNotFoundException;
import com.shoppingcart.exception.ShoppingCartException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 * @throws DataNotFoundException
	 */
	@GetMapping("/get-cart-items")
	public ResponseEntity<List<Cart>> getCartItems(@RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "productId", required = false) Long productId) throws DataNotFoundException{
		return new ResponseEntity<>(shoppingCartService.getCartItems(userId, productId), HttpStatus.OK);
	}

	@PostMapping("/add-to-kart")
	public ResponseEntity<com.shoppingcart.dto.Cart> addProductToCart(@Valid @RequestBody com.shoppingcart.dto.Cart cart) {
		shoppingCartService.addProduct(cart);
		return new ResponseEntity<>(cart, HttpStatus.CREATED);
	}

	/**
	 *
	 * @param userId
	 * @param productId
	 * @return ResponseEntity
	 * @throws DataNotFoundException
	 */
	@DeleteMapping("/remove-from-cart")
	public ResponseEntity removeCartItems(@RequestParam(value = "userId") String userId, @RequestParam(value = "productId") Long productId) throws DataNotFoundException{
		shoppingCartService.removeCartItems(userId, productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * 
	 * @return
	 * @throws ShoppingCartException
	 */
	@PostMapping("/checkout-and-create-order")
	public ResponseEntity<OrderDto> checkoutAndCreateOrder() throws ShoppingCartException{
		return new ResponseEntity<>(shoppingCartService.checkoutAndCreateOrder(), HttpStatus.CREATED);
	}
}
