package com.eworx.tucha.eshop.login.service;

public interface SecurityService {

	String findLoggedInUsername();

	void autoLogin(String username, String password);
}