package com.shoppingcart.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.shoppingcart.dto.OrderDto;

@Component
public class ShoppingCartServiceHelper {
	
	 @Autowired
	 private RestTemplate restTemplate;
	
	 /**
	  * Executing/Calling create order service
	  * @param orderDto
	  * @param creteaOrderServiceUrl
	  * @return
	  * @throws Exception
	  */
     public ResponseEntity<OrderDto> createOrder(OrderDto orderDto, String creteaOrderServiceUrl) throws Exception{
    	HttpHeaders headers = new HttpHeaders();
        HttpEntity <OrderDto> httpEntity = new HttpEntity<>(orderDto, headers);
        return restTemplate.exchange(creteaOrderServiceUrl, HttpMethod.POST, httpEntity, OrderDto.class);
     }
}
