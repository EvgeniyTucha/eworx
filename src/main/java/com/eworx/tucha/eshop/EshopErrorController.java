package com.eworx.tucha.eshop;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EshopErrorController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "redirect:/error.jsp";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}