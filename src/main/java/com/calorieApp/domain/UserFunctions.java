package com.calorieApp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFunctions implements FunctionsInterface {
	@Autowired
	UserRepository userRepo;
	@Autowired
	MealRepository mealRepo;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	public boolean registerUser(User user) {

		user.setRole("USER");
		/*-------The passwords have been already confirmed to match by the entity class, the next lines are just to encode and
		 *-------set the password as the matching one. This is because of the way the constructor of the entity works.*/
		user.setPassword(encoder.encode(user.getPassword()));
		user.setMatchingPassword(user.getPassword());
		if (userRepo.save(user) != null) {
			return true;
		} else {
			return false;
		}
	}

	/* Returns the role of the user as a string */
	public String userAuthority() {
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "null";
		} else if (userRepo.findByEmail(authentication.getName()).getRole() == "ADMIN") {
			return "ADMIN";
		} else {
			return "USER";
		}
	}

	public boolean createAdmin(User user) {

		user.setRole("ADMIN");
		user.setPassword(encoder.encode(user.getPassword()));
		/*-------The passwords have been already confirmed to match by the entity class, the next lines are just to encode and
		 *-------set the password as the matching one. This is because of the way the constructor of the entity works.*/
		user.setMatchingPassword(user.getPassword());
		if (userRepo.save(user) != null) {
			return true;
		} else {
			return false;
		}
	}

	/* Deletes the user or returns false if the delete was unsuccessful */
	public boolean deleteUser(User user) {
		userRepo.deleteById(user.getId());
		if (userRepo.findById(user.getId()) != null) {
			return false;
		} else {
			return true;
		}
	}
	/*
	 * public boolean addMeal(Meal meal) { if () } private boolean checkID (Meal
	 * meal) {
	 * 
	 * } private boolean getID() { Object user = authentication.getName(); }
	 */
}
