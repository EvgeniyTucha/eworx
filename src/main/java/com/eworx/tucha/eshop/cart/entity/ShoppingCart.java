package com.eworx.tucha.eshop.cart.entity;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

@Component
@Scope(value="session")
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 4573229359755965961L;
	private LinkedHashMap<Long, OrderProduct> orderProductsMap = new LinkedHashMap<>();
	private BigDecimal subtotal;
	private BigDecimal shippingCost;
	private BigDecimal grandTotal;
	private BigDecimal discount;
	private String userName;

	public LinkedHashMap<Long, OrderProduct> getOrderProductsMap() {
		return orderProductsMap;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getShippingCost() {
		return this.shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	public BigDecimal getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}