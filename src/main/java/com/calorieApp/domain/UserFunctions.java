package com.calorieApp.domain;

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

	/* Returns the role of the user as a string */
	public String userAuthority() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "null";
		} else if (userRepo.findByEmail(authentication.getName()).getRole() == "ADMIN") {
			return "ADMIN";
		} else {
			return "USER";
		}
	}

	public long getUserId() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByEmail(username);
		long id = user.getId();
		return id;
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
	public boolean deleteUser(long id) {
		userRepo.deleteById(id);
		if (userRepo.findById(id) != null) {
			return false;
		} else {
			return true;
		}
	}

	/* Gets the meal object from the page and adds the current user ID to it */
	public boolean addMeal(Meal meal) {
		meal.setUserId(getUserId());
		System.out.println(getUserId());
		System.out.println(meal.toString());
		if (mealRepo.save(meal) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Meal> getMeals() {
		long userId = getUserId();
		return mealRepo.findByUserId(userId);
	}
	
	public Object getUser() {
		return userRepo.findById(getUserId());
	}
	
	public void updateUser (User user) {
		
	}
}
