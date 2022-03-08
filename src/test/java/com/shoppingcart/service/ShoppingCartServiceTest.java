package com.shoppingcart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.exception.DataNotFoundException;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.helper.ShoppingCartServiceHelper;
import com.shoppingcart.repository.CartRepository;

@SpringBootTest
public class ShoppingCartServiceTest {
	
    @Autowired
    private ShoppingCartService shoppingCartService;
    
    @MockBean
    private CartRepository cartRepository;
    
    private static List<Cart> cartItems = new ArrayList<> ();
    
    @MockBean
    private ShoppingCartServiceHelper shoppingCartServiceHelper;
    
	@BeforeAll
	public static void addItemsToCart() {
		Cart cartFirstProduct =new Cart();
    	cartFirstProduct.setProductDescription("XYZ Mobile has 6GB RAM and 128GB internal memory");
    	cartFirstProduct.setProductId(345678L);
    	cartFirstProduct.setProductName("XYZ");
    	cartFirstProduct.setProductPrice(17999.00);
    	cartFirstProduct.setUserId("User347");
    	cartFirstProduct.setProductQuantity(1);
    	
    	Cart cartSecondProduct =new Cart();
    	cartSecondProduct.setProductDescription("ABC Mobile has 8GB RAM and 128GB internal memory");
    	cartSecondProduct.setProductId(23896L);
    	cartSecondProduct.setProductName("ABC");
    	cartSecondProduct.setProductPrice(20500.00);
    	cartSecondProduct.setUserId("User347");
    	cartFirstProduct.setProductQuantity(1);
    	cartItems.add(cartFirstProduct);
    	cartItems.add(cartSecondProduct);
	}
    
    @Test
    public void testGetCartItemsByUserId() throws DataNotFoundException {
        doReturn(cartItems).when(cartRepository).findByUserId("User347");
    	assertEquals(2, cartItems.size());
    	assertNotNull(shoppingCartService.getCartItems("User347", null));
    	
    }
        
    @Test()
    public void testGetCartItemsWithException() throws DataNotFoundException {
        doReturn(new ArrayList<>()).when(cartRepository).findByUserId("User");
        Assertions.assertThrows(DataNotFoundException.class, () -> shoppingCartService.getCartItems("User", null));
    } 
        
    @Test
    public void testGetCartItemsByUserIdAndProductId() throws DataNotFoundException {
    	Cart cartFirstProduct =new Cart();
    	cartFirstProduct.setProductDescription("Good TV");
    	cartFirstProduct.setProductId(479654L);
    	cartFirstProduct.setProductName("Samsung TV");
    	cartFirstProduct.setProductPrice(35999.00);
    	cartFirstProduct.setUserId("User289");
    	doReturn(Arrays.asList(cartFirstProduct)).when(cartRepository).findByUserIdAndProductId("User289", 479654L);
    	List<Cart> cartList = shoppingCartService.getCartItems("User289", 479654L);
    	assertEquals(1, cartList.size());
    	assertNotNull(cartList);
   }
        
    @Test
    public void testCheckoutAndCreateOrderWithException() throws Exception {
    	doReturn(new ArrayList<>()).when(cartRepository).findAll();
    	Assertions.assertThrows(ShoppingCartException.class, () -> shoppingCartService.checkoutAndCreateOrder("User123"));
    }
    
}
