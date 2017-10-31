package com.eworx.tucha.eshop.login.service;

import com.eworx.tucha.eshop.login.entity.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	void deleteUser(User user);
}