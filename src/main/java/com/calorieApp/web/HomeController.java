package com.calorieApp.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.calorieApp.domain.Meal;
import com.calorieApp.domain.UserFunctions;
import com.calorieApp.domain.UserRepository;

@Controller
public class HomeController {

	@Autowired
	UserFunctions userFuncs;
	@Autowired
	UserRepository userRepo;

	@GetMapping(value = "/home")
	public String indexController(Model model) {
		if (userFuncs.userAuthority() == null) {
			return "login";
		} else {
			List<Meal> meals = userFuncs.getMeals(userFuncs.getUserId());
			model.addAttribute("meals", meals);
			return "home";
		}
	}
}