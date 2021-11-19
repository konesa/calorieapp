package com.calorieApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.calorieApp.domain.UserFunctions;

@Controller
public class DeleteUserController {

	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/deleteUser")
	public String deleteUser(Model model) {
		return "deleteUser";
	}

	@PostMapping(value = "/deleteUser")
	public String deleteUserConfirmed(Model model) {
		if (userFuncs.getMeals().size() != 0) {
			userFuncs.deleteAllMeals(userFuncs.getUserId());
			userFuncs.deleteUser(userFuncs.getUserId());
			return "redirect:/login";
		} else if (userFuncs.getMeals().size() == 0) {
			userFuncs.deleteUser(userFuncs.getUserId());
			return "redirect:/login";
		} else {
			return "error";
		}
	}
}
