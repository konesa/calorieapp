package com.calorieApp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.calorieApp.domain.Meal;
import com.calorieApp.domain.UserFunctions;

@Controller
public class EditMealController {

	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/editMeal/{id}")
	public String getMealEdit(@PathVariable("id") long id, Model model) {
		if (userFuncs.getMeal(id) != null) {
			Meal meal = userFuncs.getMeal(id);
			if (meal.getUserId() == userFuncs.getUserId()) {
				model.addAttribute("meal", meal);
				return "editMeal";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}

	@PostMapping(value = "/editMeal")
	public String postMeal(@ModelAttribute @Valid Meal meal, BindingResult result, Errors errors, Model model) {
		if (result.hasErrors()) {
			return "editMeal";
		} else {
			if (userFuncs.addMeal(meal)) {
				return "redirect:/";
			} else {
				return "error";
			}
		}
	}
}
