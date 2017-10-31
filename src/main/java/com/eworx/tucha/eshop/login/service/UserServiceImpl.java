package com.eworx.tucha.eshop.login.service;

import com.eworx.tucha.eshop.login.entity.User;

import com.eworx.tucha.eshop.login.repository.RoleRepository;
import com.eworx.tucha.eshop.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}