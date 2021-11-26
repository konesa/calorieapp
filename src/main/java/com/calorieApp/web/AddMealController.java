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
import com.calorieApp.domain.Meal;

@Controller
public class AddMealController {
	@Autowired
	FunctionsInterface functions;

	@GetMapping(value = "/addMeal")
	public String newMeal(Model model, Meal meal) {
		model.addAttribute(meal);
		return "addMeal";
	}

	@PostMapping(value = "/addMeal")
	public String addMeal(@ModelAttribute @Valid Meal meal, BindingResult result, Errors errors, Model model) {
		meal.setUserId(functions.getUserId());
		if (result.hasErrors()) {
			return "addMeal";
		} else {
			if (functions.addMeal(meal)) {
				return "redirect:/";
			} else {
				return "error";
			}
		}
	}
}