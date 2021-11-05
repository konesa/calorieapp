package com.calorieApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.calorieApp.domain.FunctionsInterface;
import com.calorieApp.domain.Meal;

@Controller
public class AddMealController {
	@Autowired
	FunctionsInterface functions;

	@GetMapping(value = "/addMeal")
	public String newMeal(Model model, Meal meal) {
		if (functions.userAuthority() == "null") {
			return "login";
		} else {
			model.addAttribute(meal);
			return "addMeal";
		}
	}

	@PostMapping(value = "/addMeal")
	public String addMeal(Model model, Meal meal, Errors errors) {
		if (functions.userAuthority() == "null") {
			return "login";
		} else {
			if (functions.addMeal) {
			return "redirect:/index";
		}
		}
	}
}