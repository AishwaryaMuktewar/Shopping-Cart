
package com.shoppingcart.util;

import com.shoppingcart.entity.Cart;

public class EntityMapper {

    public Cart convertDtoToEntity(com.shoppingcart.dto.Cart cart) {

        Cart cartEntity = new Cart();
        cartEntity.setId(cart.getId());
        cartEntity.setUserId(cart.getUserId());
        cartEntity.setProductId(cart.getProductId());
        cartEntity.setProductName(cart.getProductName());
        cartEntity.setProductQuantity(cart.getProductQuantity());
        cartEntity.setProductPrice(cart.getProductPrice());
        cartEntity.setProductDescription(cart.getProductDescription());

        return cartEntity;
    }
}