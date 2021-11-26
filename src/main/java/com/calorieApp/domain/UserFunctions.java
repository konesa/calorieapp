package com.calorieApp.domain;

import java.util.Collections;
import java.util.List;
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

	// Returns the role of the user as a string
	public String userAuthority() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "null";
		}
		if (userRepo.findByEmail(authentication.getName()).getRole().equalsIgnoreCase("admin")) {
			return "ADMIN";
		} else {
			return "USER";
		}
	}

	// Gets the current authentication name and returns the ID of the user from the
	// database
	public long getUserId() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByEmail(username);
		return user.getId();
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

	// Deletes the user or returns false if the delete was unsuccessful
	public boolean deleteUser(long id) {
		userRepo.deleteById(id);
		SecurityContextHolder.clearContext();
		if ((userRepo.findById(id).orElse(null)) == null) {
			return true;
		} else {
			return false;
		}
	}

	// Gets the meal object from the controller and saves it. If unsuccessful, false
	// is returned.
	public boolean addMeal(Meal meal) {
		if (mealRepo.save(meal) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	// Returns all the meals belonging to a specific user id.
	public List<Meal> getMeals(long id) {
		List<Meal> meals = mealRepo.findByUserId(id);
		Collections.reverse(meals);
		return meals;
	}

	// An ID for a meal is passed as an argument, if the meal with that id is not
	// found then null is returned.
	public Meal getMeal(long id) {
		return mealRepo.findById(id).orElse(null);
	}

	// Takes the meal id as an input and removes is from the database. If the
	// removal is unsuccessful, false is returned.
	public boolean deleteMeal(long id) {
		mealRepo.deleteById(id);
		if (mealRepo.findById(id).orElse(null) == null) {
			return true;
		} else {
			return false;
		}
	}

	// When the user deletes their account, this is run automatically and the
	// entries belonging to the user will be deleted from the database.
	public void deleteAllMeals(long userID) {
		List<Meal> meals = getMeals(userID);
		mealRepo.deleteAll(meals);
	}
}
