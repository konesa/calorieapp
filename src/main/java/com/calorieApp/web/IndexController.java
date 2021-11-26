package com.calorieApp.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calorieApp.domain.Meal;
import com.calorieApp.domain.UserFunctions;

@Controller
public class IndexController {

	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/index")
	public String indexController(Model model) {
		if (userFuncs.userAuthority() == null) {
			return "login";
		} else {
			List<Meal> meals = userFuncs.getMeals(userFuncs.getUserId());
			model.addAttribute("meals", meals);
			return "index";
		}
	}
	@RequestMapping(value = "/")
	public String indexCont2() {
		return "index";
	}
}