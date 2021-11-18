package com.calorieApp.domain;

import java.util.List;

public interface FunctionsInterface {
	boolean registerUser(User user);

	Object userAuthority();

	boolean createAdmin(User user);

	boolean deleteUser(long id);

	boolean addMeal(Meal meal);
	
	long getUserId();
	
	List<Meal> getMeals();

	Object getUser();

	void updateUser(User user);
}