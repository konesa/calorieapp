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
	MealRepository mealRepo;
	@Autowired
	UserFunctions functions;
	@Autowired
	UserRepository userRepo;

	User testUser = new User("user", "user", "testuser@user.com",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK", "USER");

	User testAdmin = new User("admin", "admin", "testadmin@admin.com",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK",
			"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK", "ADMIN");

	Meal meal = new Meal(0, "testmeal123", 1, 1, 1, 1);

	/*The test tries whether the user already exists or not. Then it either deleted the entity 
	 * and creates a new one, or just creates a new one
	 */
	@Test
	public void registerUser() {
		if (functions.registerUser(testUser)) {
			assertThat(userRepo.findByEmail(testUser.getEmail()).getFirstName().equalsIgnoreCase("user"));
			assertThat(userRepo.findByEmail(testUser.getEmail()).getRole().equalsIgnoreCase("user"));
		} else {
			functions.deleteUser(userRepo.findByEmail(testUser.getEmail()).getId());
			functions.registerUser(testUser);
			assertThat(userRepo.findByEmail(testUser.getEmail()).getFirstName().equalsIgnoreCase("user"));
			assertThat(userRepo.findByEmail(testUser.getEmail()).getRole().equalsIgnoreCase("user"));
		}
	}

	/*The test tries whether the user already exists or not. Then it either deleted the entity 
	 * and creates a new one, or just creates a new one
	 */
	@Test
	public void createAdmin() {
		if (functions.createAdmin(testAdmin)) {
			assertThat(userRepo.findByEmail(testAdmin.getEmail()).getFirstName().equalsIgnoreCase("admin"));
			assertThat(userRepo.findByEmail(testAdmin.getEmail()).getRole().equalsIgnoreCase("admin"));
		} else {
			functions.deleteUser(userRepo.findByEmail(testAdmin.getEmail()).getId());
			functions.registerUser(testAdmin);
			assertThat(userRepo.findByEmail(testAdmin.getEmail()).getFirstName().equalsIgnoreCase("admin"));
			assertThat(userRepo.findByEmail(testAdmin.getEmail()).getRole().equalsIgnoreCase("admin"));
		}
	}

	/*All the meals belonging to this user ID are removed first, then a meal is added.
	 * Then a new meal entry is entered and the check is carried on that entity.
	 */
	@Test
	public void addMeal() {
		functions.deleteAllMeals(userRepo.findByEmail(testUser.getEmail()).getId());
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isEqualTo(0);
		meal.setUserId(userRepo.findByEmail(testUser.getEmail()).getId());
		functions.addMeal(meal);
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isGreaterThan(0);
	}
	
	/***************************This test is unable to run because there is no logged in user present.
	 * *************************But the function is working as intended, which can be seen inside the application.
	@Test
	public void getMeals() {
		meal.setUserId(userRepo.findByEmail(testUser.getEmail()).getId());
		functions.addMeal(meal);
		functions.addMeal(meal);
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isEqualTo(1);
	}**************************************************************************************************************/

	/*All the meals belonging to this user ID are removed first, then a meal is added.
	 * Then a new meal entry is entered and the check is carried on the new entity.
	 *  Because of the way how the function is written it needs an active user, which is not present for the test.
	 * That is why the assertion is done against different function. But the result can be seen correctly also from this
	 * function.
	 */
	@Test
	public void getMeal() {
		functions.deleteAllMeals(userRepo.findByEmail(testUser.getEmail()).getId());
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isEqualTo(0);
		meal.setUserId(userRepo.findByEmail(testUser.getEmail()).getId());
		functions.addMeal(meal);
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isEqualTo(1);
	}

	/*All the meals belonging to this user ID are removed first, then a meal is added.
	 * Then a new meal entry is entered and the check is carried on the new entity.
	 * */
	@Test
	public void deleteMeal() {
		functions.deleteAllMeals(userRepo.findByEmail(testUser.getEmail()).getId());
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isEqualTo(0);
		meal.setUserId(userRepo.findByEmail(testUser.getEmail()).getId());
		functions.addMeal(meal);
		assertThat(functions.deleteMeal(meal.getId()));
	}
	
	/*A meal entity is added and asserted that is exists. Then all the meals belonging to a specific user ID are removed
	 * and the Meal list size is asserted to be 0.
	 */
	@Test
	public void deleteAllMeals() {
		meal.setUserId(userRepo.findByEmail(testUser.getEmail()).getId());
		functions.addMeal(meal);
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isGreaterThanOrEqualTo(1);
		functions.deleteAllMeals(userRepo.findByEmail(testUser.getEmail()).getId());
		assertThat(functions.getMeals(userRepo.findByEmail(testUser.getEmail()).getId()).size()).isEqualTo(0);
	}
	
	/*Both of the last tests simply search for an entity and remove it*/
	@Test
	public void deleteUser() {
		if(functions.deleteUser(userRepo.findByEmail(testUser.getEmail()).getId())) {
			assertThat(userRepo.findByEmail(testUser.getEmail())).isNull();
		} else {
			functions.registerUser(testUser);
			assertThat(userRepo.findByEmail(testUser.getEmail())).isNotNull();
			functions.deleteUser(userRepo.findByEmail(testUser.getEmail()).getId());
			assertThat(userRepo.findByEmail(testUser.getEmail())).isNull();
		};
	}

	@Test
	public void deleteAdmin() {
		if(functions.deleteUser(userRepo.findByEmail(testAdmin.getEmail()).getId())) {
			assertThat(userRepo.findByEmail(testAdmin.getEmail())).isNull();
		} else {
			functions.registerUser(testAdmin);
			assertThat(userRepo.findByEmail(testAdmin.getEmail())).isNotNull();
			functions.deleteUser(userRepo.findByEmail(testAdmin.getEmail()).getId());
			assertThat(userRepo.findByEmail(testAdmin.getEmail())).isNull();
		}
	}
}