package com.eworx.tucha.eshop.product.controller;

import com.eworx.tucha.eshop.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductPageController {

	@Autowired
	private ProductServiceImpl productService;

	@RequestMapping("product/{id}")
	public String showProduct(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productshow";
	}

	@RequestMapping(value = {"/", "/productPage"}, method = RequestMethod.GET)
	public String productPage(Model model) {
		model.addAttribute("products", productService.findAllProducts());
		return "productPage";
	}
}
