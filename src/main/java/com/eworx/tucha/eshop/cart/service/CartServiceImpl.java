package com.eworx.tucha.eshop.cart.service;

import com.eworx.tucha.eshop.cart.entity.OrderProduct;
import com.eworx.tucha.eshop.cart.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

	public static final BigDecimal TEN_PERCENT_DISCOUNT = new BigDecimal(10);

	@Autowired
	private HttpSession httpSession;

	private static final String CART_ATTRIBUTE_NAME = "shoppingcart";

	public ShoppingCart getShoppingCartInSession() {
		ShoppingCart shoppingCart = (ShoppingCart) this.httpSession.getAttribute(CART_ATTRIBUTE_NAME);
		if (shoppingCart == null) {
			shoppingCart = initShoppingCart();
		} else {
			if (!isUsersNotEquals(shoppingCart)) {
				shoppingCart = initShoppingCart();
			}
		}
		return shoppingCart;
	}

	private ShoppingCart initShoppingCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserName(getCurrentUsername());
		this.httpSession.setAttribute(CART_ATTRIBUTE_NAME, shoppingCart);
		return shoppingCart;
	}

	private boolean isUsersNotEquals(ShoppingCart shoppingCart) {
		boolean result = true;
		String shoppingCartUser = shoppingCart.getUserName();
		String loggedInUserName = getCurrentUsername();
		if (!StringUtils.isEmpty(shoppingCartUser) && !StringUtils.isEmpty(loggedInUserName) && !shoppingCartUser.equalsIgnoreCase(loggedInUserName)) {
			result = false;
		}
		return result;
	}

	public BigDecimal calculateSubtotal() {
		BigDecimal subtotal = BigDecimal.ZERO;
		for (OrderProduct orderProduct : getProductsInCart()) {
			subtotal = subtotal.add(orderProduct.getTotalPriceForItem());
		}
		return subtotal;
	}

	public BigDecimal calculateDiscount() {
		BigDecimal discount = BigDecimal.ZERO;
		BigDecimal subtotal = getShoppingCartInSession().getSubtotal();
		discount = subtotal.compareTo(new BigDecimal(100)) == 1 ? discount.add(subtotal.divide(TEN_PERCENT_DISCOUNT)) : discount;
		discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		return discount;
	}

	public BigDecimal calculateShippingCost() {
		BigDecimal shippingCost = BigDecimal.ZERO;
		BigDecimal defaultShippingCost = new BigDecimal(5);
		for (OrderProduct orderProduct : getProductsInCart()) {
			shippingCost = shippingCost.add(orderProduct.getProduct().getWeightFactor().multiply(
				defaultShippingCost.multiply(new BigDecimal(orderProduct.getPurchasedQuantity()))));
		}
		return shippingCost;
	}

	public BigDecimal calculateGrandTotal() {
		BigDecimal grandTotal = BigDecimal.ZERO;
		BigDecimal subtotal = getShoppingCartInSession().getSubtotal();
		BigDecimal discount = getShoppingCartInSession().getDiscount();
		BigDecimal shippingCost = getShoppingCartInSession().getShippingCost();
		return grandTotal.add(subtotal.subtract(discount)).add(shippingCost);
	}

	private void updateCartInSession(ShoppingCart shoppingCart) {
		this.httpSession.setAttribute(CART_ATTRIBUTE_NAME, shoppingCart);
	}


	public OrderProduct addToCart(OrderProduct orderProduct) {
		ShoppingCart shoppingCart = getShoppingCartInSession();
		OrderProduct orderProductInCart = addOrderItemToCart(orderProduct);
		updateCartInSession(shoppingCart);
		return orderProductInCart;
	}

	private OrderProduct addOrderItemToCart(OrderProduct orderProduct) {
		Long productId = orderProduct.getProduct().getProductId();
		if (getShoppingCartInSession().getOrderProductsMap().containsKey(productId)) {
			OrderProduct existingOrderProduct = getShoppingCartInSession().getOrderProductsMap().get(productId);
			int newQuantity = existingOrderProduct.getPurchasedQuantity() + orderProduct.getPurchasedQuantity();
			OrderProduct newOrderProduct = new OrderProduct(orderProduct.getProduct(), newQuantity);
			getShoppingCartInSession().getOrderProductsMap().put(productId, newOrderProduct);
			return newOrderProduct;
		} else {
			getShoppingCartInSession().getOrderProductsMap().put(productId, orderProduct);
			return orderProduct;
		}
	}

	private void updateCart(OrderProduct orderProduct) {
		Long productId = orderProduct.getProduct().getProductId();
		if (getShoppingCartInSession().getOrderProductsMap().containsKey(productId)) {
			if (orderProduct.getPurchasedQuantity() <= 0) {
				removeFromCart(productId);
			} else {
				getShoppingCartInSession().getOrderProductsMap().put(productId, orderProduct);
			}
		} else {
			getShoppingCartInSession().getOrderProductsMap().put(productId, orderProduct);
		}
	}

	public void removeFromCart(long productId) {
		ShoppingCart shoppingCart = getShoppingCartInSession();
		getShoppingCartInSession().getOrderProductsMap().remove(productId);
		updateCartInSession(shoppingCart);
	}

	public void updateCart(List<OrderProduct> orderProducts) {
		ShoppingCart shoppingCart = getShoppingCartInSession();
		if (orderProducts != null) {
			for (OrderProduct orderProduct : orderProducts) {
				updateCart(orderProduct);
			}
		}
		updateCartInSession(shoppingCart);
	}

	private String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	public List<OrderProduct> getProductsInCart() {
		return new ArrayList<>(getShoppingCartInSession().getOrderProductsMap().values());
	}
}
