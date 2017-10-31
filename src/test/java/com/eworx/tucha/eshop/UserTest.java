package com.eworx.tucha.eshop;

import com.eworx.tucha.eshop.login.entity.User;
import com.eworx.tucha.eshop.login.service.UserService;
import com.eworx.tucha.eshop.login.validator.UserValidator;
import com.eworx.tucha.eshop.product.entity.Product;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	public static final String USERNAME = "test";
	public static final String NON_EXISTING_USERNAME = "nonExistingUser";
	public static final String NOT_VALID_PASSWORD = "test";
	public static final String VALID_PASSWORD = "test1234";
	public static final String EMPTY_STRING = "";

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserValidator userValidator;

	private User user;

	@Before
	public void setUp() {
		user = new User();
		user.setUsername(USERNAME);
		user.setPassword(VALID_PASSWORD);
		user.setPasswordConfirm(VALID_PASSWORD);
		userService.save(user);
	}

	private User createUserForValidation(String nonExistingUsername, String password, String passwordConfirm) {
		User emptyUser = new User();
		emptyUser.setUsername(nonExistingUsername);
		emptyUser.setPassword(password);
		emptyUser.setPasswordConfirm(passwordConfirm);
		return emptyUser;
	}

	private BindingResult getBindingResult(User emptyUser) {
		return new BeanPropertyBindingResult(emptyUser, "userForm");
	}

	@Test
	public void whenLoadUserByUserName_thenReturnUser() {
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		Assert.assertEquals(userDetails.getUsername(), user.getUsername());
	}

	@Test
	public void whenAuthenticateUser_thenUserShouldBeAuthenticated() {
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
			new UsernamePasswordAuthenticationToken(userDetails, user.getPasswordConfirm(), userDetails.getAuthorities());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		Assert.assertTrue(usernamePasswordAuthenticationToken.isAuthenticated());
	}

	@Test
	public void whenValidateUser_thenUserClassShouldBeSupported() {
		Assert.assertTrue(userValidator.supports(User.class));
	}

	@Test
	public void whenValidateUser_thenObjectClassShouldNotBeSupported() {
		Assert.assertFalse(userValidator.supports(Product.class));
	}

	@Test
	public void whenValidateUserWithEmptyFields_thenValidationShouldFail() {
		User user = createUserForValidation(EMPTY_STRING, VALID_PASSWORD, VALID_PASSWORD);
		BindingResult errors = getBindingResult(user);
		userValidator.validate(user, errors);
		Assert.assertEquals(2, errors.getErrorCount());
	}

	@Test
	public void whenValidateUserWithPasswordAndPasswordConfirmNotEquals_thenValidationShouldFail() {
		User emptyUser = createUserForValidation(NON_EXISTING_USERNAME, VALID_PASSWORD, NOT_VALID_PASSWORD);
		BindingResult errors = getBindingResult(emptyUser);
		userValidator.validate(emptyUser, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

	@Test
	public void whenValidateUserWithPasswordAndPasswordConfirmLessThanEightSymbols_thenValidationShouldFail() {
		User user = createUserForValidation(NON_EXISTING_USERNAME, NOT_VALID_PASSWORD, NOT_VALID_PASSWORD);
		BindingResult errors = getBindingResult(user);
		userValidator.validate(user, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

	@Test
	public void whenValidateExistingUser_thenValidationShouldFail() {
		User user = createUserForValidation(USERNAME, VALID_PASSWORD, VALID_PASSWORD);
		BindingResult errors = getBindingResult(user);
		userValidator.validate(user, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

	@Test
	public void whenValidateUserWithNonExistingLogin_thenValidationShouldPass() {
		User user = createUserForValidation(NON_EXISTING_USERNAME, VALID_PASSWORD, VALID_PASSWORD);
		BindingResult errors = getBindingResult(user);
		userValidator.validate(user, errors);
		Assert.assertEquals(0, errors.getErrorCount());
	}

	@After
	public void cleanup() {
		userService.deleteUser(user);
	}
}