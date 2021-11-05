package com.calorieApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.calorieApp.domain.FunctionsInterface;
import com.calorieApp.domain.UserRepository;

@Controller
public class LoginController {

	@Autowired
	FunctionsInterface functions;
	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "/login")
	public String loginController() {
		return ("login");
	}
}