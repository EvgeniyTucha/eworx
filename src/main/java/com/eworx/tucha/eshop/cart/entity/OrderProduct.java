package com.eworx.tucha.eshop.cart.entity;

import com.eworx.tucha.eshop.product.entity.Product;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class OrderProduct implements Serializable {

	private static final long serialVersionUID = -3480296374500403880L;

	private int purchasedQuantity;
	private Product product;
	private BigDecimal totalPriceForItem = BigDecimal.ZERO;

	public OrderProduct() {
	}

	public OrderProduct(Product product, int purchasedQuantity) {
		this.product = product;
		this.purchasedQuantity = purchasedQuantity;
	}

	public int getPurchasedQuantity() {
		return purchasedQuantity;
	}

	public void setPurchasedQuantity(int purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getTotalPriceForItem() {
		return totalPriceForItem.add(getProduct().getProductPrice().multiply(new BigDecimal(getPurchasedQuantity())));
	}

	public void setTotalPriceForItem(BigDecimal totalPriceForItem) {
		this.totalPriceForItem = totalPriceForItem;
	}
}