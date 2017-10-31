package com.eworx.tucha.eshop.login.repository;

import com.eworx.tucha.eshop.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}