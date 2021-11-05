package com.calorieApp.domain;

public interface FunctionsInterface {
	boolean registerUser(User user);

	Object userAuthority();

	boolean createAdmin(User user);
	
	boolean deleteUser(User user);
	
	//boolean addMeal(Meal meal);
}