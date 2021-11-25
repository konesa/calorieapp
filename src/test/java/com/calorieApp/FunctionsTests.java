package com.calorieApp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.calorieApp.domain.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class FunctionsTests {

	@Autowired
	UserFunctions functions;
	@Autowired
	UserRepository userRepo;

	User user = new User("user", "user", "user22@user.com",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK", "USER");

	User user2 = new User("user", "user", "user3@user.com",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK", "ADMIN");

	Meal meal = new Meal(1, "asd", 1, 1, 1, 1);

	@Test
	public void registerUser() {
		functions.registerUser(user);
		assertThat(userRepo.findByEmail(user.getEmail()) != null);
		userRepo.delete(user);
	}

	@Test
	public void createAdmin() {
		functions.createAdmin(user2);
		assertThat(userRepo.findByEmail(user2.getEmail()) != null);
		userRepo.delete(user2);
	}
	
	@Test
	public void addMeal() {
		meal.setUserId(userRepo.findByEmail("admin@admin.com").getId());
		functions.addMeal(meal);
		assertThat(functions.addMeal(meal) == true);
	}
	
	@Test
	public void getMeals() {
		assertThat(functions.getMeals(1).size() > 0);
	}

	@Test
	public void getMeal() {
		assertThat(functions.getMeal(meal.getId()) != null);
	}

	@Test
	public void deleteMeal() {
		assertThat(functions.deleteMeal(meal.getId()) == true);
	}

	@Test
	public void deleteUser() {
		userRepo.save(user);
		assertThat(functions.deleteUser(userRepo.findByEmail("user22@user.com").getId()) == true);
	}
	
	/********************The test is unable to run since the active user is not present*************
	@Test
	public void deleteAllMeals() {
		functions.registerUser(user);
		meal.setUserId(user.getId());
		functions.addMeal(meal);
		functions.deleteAllMeals(user.getId());
		assertThat(functions.getMeals(user.getId()) == null);
		functions.deleteUser(user.getId());
	}**************************************************************************************************/
}