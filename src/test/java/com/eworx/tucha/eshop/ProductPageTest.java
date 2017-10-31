package com.eworx.tucha.eshop;

import com.eworx.tucha.eshop.product.entity.Product;
import com.eworx.tucha.eshop.product.service.ProductService;
import com.eworx.tucha.eshop.product.service.ProductServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductPageTest {

	public static final BigDecimal WEIGHT_FACTOR_CONSTANT = new BigDecimal(0.5);
	public static final BigDecimal PRODUCT_PRICE_CONSTANT = new BigDecimal(22.5);
	@Autowired
	private ProductService productService;

	private Product product;

	@Before
	public void setUp() {
		product = new Product();
		product.setProductName("TestProduct");
		product.setProductPrice(PRODUCT_PRICE_CONSTANT);
		product.setWeightFactor(WEIGHT_FACTOR_CONSTANT);
		productService.save(product);
	}

	@Test
	public void whenLoadProductById_thenProductNameShouldBeEquals() {
		Product product = productService.getProductById(this.product.getProductId());
		Assert.assertEquals(product.getProductName(), this.product.getProductName());
	}

	@Test
	public void whenLoadProductById_thenProductIdShouldBeEquals() {
		Product product = productService.getProductById(this.product.getProductId());
		Assert.assertEquals(product.getProductId(), this.product.getProductId());
	}

	@Test
	public void whenLoadProductById_thenProductPriceShouldBeEquals() {
		Product product = productService.getProductById(this.product.getProductId());
		Assert.assertTrue(product.getProductPrice().compareTo(this.product.getProductPrice()) == 0);
	}

	@Test
	public void whenLoadProductById_thenProductWeightFactorShouldBeEquals() {
		Product product = productService.getProductById(this.product.getProductId());
		Assert.assertTrue(product.getWeightFactor().compareTo(this.product.getWeightFactor()) == 0);
	}

	@Test
	public void whenFindAllProducts_thenThreeProductsShouldBeReturned() {
		Iterable<Product> products = productService.findAllProducts();
		Assert.assertEquals(3, ((Collection<?>) products).size());
	}

	@After
	public void cleanup() {
		productService.deleteProduct(product);
	}
}