package com.shoppingcart.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotNull(message = "user id should not be null")
	private String userId;
	@NotNull(message = "product id should not be null")
	private Long productId;
	@NotNull(message = "product name should not be null")
	private String productName;
	@NotNull(message = "product price should not be null")
	private Double productPrice;
	@NotNull(message = "product quantity should not be null")
	private Integer productQuantity;
	@NotNull(message = "purchased date should not be null")
	private Date productPurchasedDate;
	@NotNull(message = "product description should not be null")
	private String productDescription;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Date getProductPurchasedDate() {
		return productPurchasedDate;
	}
	public void setProductPurchasedDate(Date productPurchasedDate) {
		this.productPurchasedDate = productPurchasedDate;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", productId=" + productId + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productPurchasedDate="
				+ productPurchasedDate + ", productDescription=" + productDescription + "]";
	}
     	
}
