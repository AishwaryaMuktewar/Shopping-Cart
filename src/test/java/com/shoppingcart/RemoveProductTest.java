package com.shoppingcart;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.exception.DataNotFoundException;
import com.shoppingcart.repository.CartRepository;
import com.shoppingcart.service.ShoppingCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RemoveProductTest {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CartRepository cartRepository;

    @BeforeAll
    public void addItemsToCart() {
        addProductToCart("11", 1L);
        addProductToCart("22", 2L);
    }

    @Test
    void testRemoveProductFromCart() throws DataNotFoundException {

        shoppingCartService.removeCartItems("11", 1L);
        Assertions.assertEquals(0, cartRepository.findByUserIdAndProductId("11", 1L).size());
        Assertions.assertEquals("22", cartRepository.findAll().get(0).getUserId());
        Assertions.assertEquals(2L, cartRepository.findAll().get(0).getProductId());
    }

    @Test
    void testRemoveProductFromCartFailure() {
        Assertions.assertThrows(DataNotFoundException.class, () -> shoppingCartService.removeCartItems("11", 1L));
    }

    @BeforeAll
    public void clearCartTable() {
        cartRepository.deleteAll();
    }

    private void addProductToCart(String userId, Long productId) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setUserId(userId);
        cartRepository.save(cart);
    }
}
