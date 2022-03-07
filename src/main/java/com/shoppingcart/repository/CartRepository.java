package com.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	List<Cart> findByUserIdAndProductId(String userId, Long productId);
	
	List<Cart> findByUserId(String userId);
	
	List<Cart> findByProductId(Long productId);

	void deleteByUserIdAndProductId(String userId, Long productId);

}
