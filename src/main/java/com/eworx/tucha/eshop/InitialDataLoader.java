package com.eworx.tucha.eshop;

import com.eworx.tucha.eshop.login.entity.User;
import com.eworx.tucha.eshop.login.service.UserService;
import com.eworx.tucha.eshop.product.entity.Product;
import com.eworx.tucha.eshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitialDataLoader implements ApplicationRunner {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		Product dummyProduct = new Product();
		dummyProduct.setProductName("Iphone 8");
		dummyProduct.setProductPrice(new BigDecimal(22.5));
		dummyProduct.setWeightFactor(new BigDecimal(0.5));
		productService.save(dummyProduct);

		dummyProduct = new Product();
		dummyProduct.setProductName("Pixel 2XL");
		dummyProduct.setProductPrice(new BigDecimal(45.2));
		dummyProduct.setWeightFactor(new BigDecimal(1));
		productService.save(dummyProduct);

		User adminUser = new User();
		adminUser.setUsername("admin");
		adminUser.setPassword("admin");
		adminUser.setPasswordConfirm("admin");
		userService.save(adminUser);
	}
}