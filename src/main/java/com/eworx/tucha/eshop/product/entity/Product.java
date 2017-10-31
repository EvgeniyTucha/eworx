package com.eworx.tucha.eshop.product.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	private String productName;

	private BigDecimal productPrice;

	private BigDecimal weightFactor;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getWeightFactor() {
		return weightFactor;
	}

	public void setWeightFactor(BigDecimal weightFactor) {
		this.weightFactor = weightFactor;
	}
}