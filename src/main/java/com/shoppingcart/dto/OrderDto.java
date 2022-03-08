package com.shoppingcart.dto;

import java.util.Date;
import java.util.List;


public class OrderDto {

    private long id;
    private long price;
    private int qty;
    private Date dateTime;
    private List<Product> products;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", price=" + price + ", qty=" + qty + ", dateTime=" + dateTime + ", products="
				+ products + "]";
	}
    
    
}
