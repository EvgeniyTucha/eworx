package com.eworx.tucha.eshop.product.service;

import com.eworx.tucha.eshop.product.entity.Product;
import com.eworx.tucha.eshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Iterable<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}
}
