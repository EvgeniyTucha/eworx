package com.eworx.tucha.eshop.login.validator;

import com.eworx.tucha.eshop.EshopConstants;
import com.eworx.tucha.eshop.login.entity.User;
import com.eworx.tucha.eshop.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	public static final String USERNAME_FIELD = "username";
	public static final String PASSWORD_FIELD = "password";
	public static final String PASSWORD_CONFIRM_FIELD = "passwordConfirm";

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME_FIELD, EshopConstants.NOT_EMPTY_ERROR);
		String username = user.getUsername();
		if (username.length() < 4 || username.length() > 32) {
			errors.rejectValue(USERNAME_FIELD, EshopConstants.USER_FORM_ERROR_SIZE);
		}

		if (userService.findByUsername(username) != null) {
			errors.rejectValue(USERNAME_FIELD, EshopConstants.USER_FORM_ERROR_DUPLICATE);
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD_FIELD, EshopConstants.NOT_EMPTY_ERROR);
		String userPassword = user.getPassword();
		if (userPassword.length() < 8 || userPassword.length() > 32) {
			errors.rejectValue(PASSWORD_FIELD, EshopConstants.USER_FORM_PASSWORD_ERROR_SIZE);
		}

		if (!user.getPasswordConfirm().equals(userPassword)) {
			errors.rejectValue(PASSWORD_CONFIRM_FIELD, EshopConstants.USER_FORM_PASSWORD_ERROR_DIFF);
		}
	}
}