package com.eworx.tucha.eshop.product.repository;

import com.eworx.tucha.eshop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}