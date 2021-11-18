package com.calorieApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.calorieApp.domain.UserFunctions;

public class DeleteUserController {

	@Autowired
	UserFunctions userFuncs;

	@GetMapping(value = "/deleteUser")
	public String deleteUser() {

		if (userFuncs.userAuthority() == "null") {
			return "login";
		} else {
			long id = userFuncs.getUserId();
			System.out.println(id);
			if (userFuncs.deleteUser(id)) {
				return "redirect:/login";
			} else {
				return "error";
			}
		}
	}
}
