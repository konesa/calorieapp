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

import com.calorieApp.domain.FunctionsInterface;
import com.calorieApp.domain.User;

@Controller
public class EditCustomer {
	@Autowired
	FunctionsInterface functions;

	@GetMapping(value = "/editInfo")
	public String newMeal(Model model) {
		if (functions.userAuthority() == "null") {
			return "login";
		} else {
			model.addAttribute("user", functions.getUser());
			return "editInfo";
		}
	}

	@PostMapping(value = "/editInfo")
	public String addMeal(@ModelAttribute @Valid User user, BindingResult result, Errors errors, Model model) {
		functions.updateUser(user);
		if (functions.userAuthority() == "null") {
			return "login";
		}
		if (result.hasErrors()) {
			return "editInfo";
		} else {
			functions.updateUser(user);
			return "redirect:/index";
		}
	}
}