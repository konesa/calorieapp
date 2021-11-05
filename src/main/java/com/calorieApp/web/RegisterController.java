package com.calorieApp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.calorieApp.domain.User;
import com.calorieApp.domain.UserFunctions;

@Controller
public class RegisterController {
	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/register")
	public String registerForm(Model model) {
		if (userFuncs.userAuthority() == "null") {
			model.addAttribute("user", new User());
			return "register";
		} else {
			return "redirect:/index";
		}
	}

	@PostMapping(value = "/register")
	public String registerUser(@ModelAttribute @Valid User user, BindingResult result, Errors errors, Model model) {

		if (userFuncs.userAuthority() == "null") {
			if (result.hasErrors()) {
				return "register";
			} else {
				if (userFuncs.registerUser(user)) {
					return "redirect:/index";
				} else {
					return "error";
				}
			}
		} else {
			return "redirect:/index";
		}
	}
}