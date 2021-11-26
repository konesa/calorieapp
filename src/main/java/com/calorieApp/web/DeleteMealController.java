package com.calorieApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.calorieApp.domain.Meal;
import com.calorieApp.domain.UserFunctions;

@Controller
public class DeleteMealController {

	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/deleteMeal/{id}")
	public String deleteMeal(@PathVariable("id") long id, Model model) {
		if (userFuncs.getMeal(id) != null) {
			Meal meal = userFuncs.getMeal(id);
			if (meal.getUserId() == userFuncs.getUserId() && userFuncs.deleteMeal(id) == true) {
				return "redirect:/";
			} else {
				return "error";
			}
		} else
			return "error";
	}
}
