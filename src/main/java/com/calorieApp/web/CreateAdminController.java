package com.calorieApp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class CreateAdminController {
	
	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/createAdmin")
	@PreAuthorize("hasRole('ADMIN')")

	public String createAdmin(Model model) {
		System.out.println(userFuncs.userAuthority());
		if (userFuncs.userAuthority() == "ADMIN") {
			User user = new User();
			model.addAttribute("user", user);
			return "createAdmin";
		} else {
			return "index";
		}
	}

	@PostMapping(value = "/createAdmin")
	@PreAuthorize("hasRole('ADMIN')")

	public String createAdmin(@ModelAttribute @Valid User user, BindingResult result, Errors errors, Model model) {
		if (userFuncs.userAuthority() == "ADMIN") {
			if (result.hasErrors()) {
				return "register";
			} else {
				if (userFuncs.createAdmin(user)) {
					return "index";
				} else {
					return "error";
				}
			}
		} else {
			return "index";
		}
	}
}