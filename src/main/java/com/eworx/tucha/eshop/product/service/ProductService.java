package com.eworx.tucha.eshop.product.service;

import com.eworx.tucha.eshop.product.entity.Product;

public interface ProductService {
	Iterable<Product> findAllProducts();

	void save(Product product);

	Product getProductById(Long id);

	void deleteProduct(Product product);
}
