package com.calorieApp.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.calorieApp.domain.Meal;
import com.calorieApp.domain.UserFunctions;

@Controller
public class IndexController {

	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/index")
	public String indexController(Model model) {
		List<Meal> meals = userFuncs.getMeals();
		model.addAttribute("meals", meals);
		return "index";
	}
}