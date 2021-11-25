package com.calorieApp.domain;

import java.util.List;

public interface FunctionsInterface {
	boolean registerUser(User user);

	Object userAuthority();

	boolean createAdmin(User user);

	boolean deleteUser(long id);

	boolean addMeal(Meal meal);

	long getUserId();

	List<Meal> getMeals(long id);

	Object getMeal(long id);

	boolean deleteMeal(long id);

	void deleteAllMeals(long userId);
}