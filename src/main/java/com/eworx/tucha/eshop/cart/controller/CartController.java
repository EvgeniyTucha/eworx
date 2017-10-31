package com.eworx.tucha.eshop.cart.controller;

import com.eworx.tucha.eshop.cart.entity.OrderProduct;
import com.eworx.tucha.eshop.cart.entity.ShoppingCart;
import com.eworx.tucha.eshop.cart.service.CartServiceImpl;
import com.eworx.tucha.eshop.product.entity.Product;
import com.eworx.tucha.eshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class CartController {

	public static final String REDIRECT_TO_BASKET = "redirect:basket";
	public static final String SHOPPING_CART_ATTRIBUTE = "shoppingCart";
	public static final String ORDER_PRODUCTS_ATTRIBUTE = "orderProducts";
	@Autowired
	private CartServiceImpl cartService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String getShoppingCart(Model model) {
		ShoppingCart shoppingCart = cartService.getShoppingCartInSession();
		if (!CollectionUtils.isEmpty(cartService.getProductsInCart())) {
			shoppingCart.setSubtotal(cartService.calculateSubtotal());
			shoppingCart.setDiscount(cartService.calculateDiscount());
			shoppingCart.setShippingCost(cartService.calculateShippingCost());
			shoppingCart.setGrandTotal(cartService.calculateGrandTotal());
		}
		model.addAttribute(SHOPPING_CART_ATTRIBUTE, shoppingCart);
		model.addAttribute(ORDER_PRODUCTS_ATTRIBUTE, cartService.getProductsInCart());
		return "basket";
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCart(@RequestParam String id) {
		long productId = Long.parseLong(id);
		Product product = productService.getProductById(productId);
		OrderProduct orderProductToSave = new OrderProduct(product, 1);
		cartService.addToCart(orderProductToSave);
		return REDIRECT_TO_BASKET;
	}

	@RequestMapping(value = "/updateOrderProduct", method = RequestMethod.POST)
	public String updateProductInCart(@RequestParam String id, HttpServletRequest request) {
		String quantity = request.getParameter("quantity");
		long productId = Long.parseLong(id);
		Product product = productService.getProductById(productId);
		OrderProduct toBeSaved = new OrderProduct(product, Integer.valueOf(quantity));
		cartService.updateCart(Arrays.asList(toBeSaved));
		return REDIRECT_TO_BASKET;
	}

	@RequestMapping(value = "/deleteFromCart", method = RequestMethod.POST)
	public String deleteFromCart(@RequestParam String id) {
		long productId = Long.parseLong(id);
		Product product = productService.getProductById(productId);
		if (product != null) {
			cartService.removeFromCart(product.getProductId());
		}
		return REDIRECT_TO_BASKET;
	}
}