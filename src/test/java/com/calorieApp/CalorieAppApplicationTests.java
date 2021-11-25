package com.calorieApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import com.calorieApp.web.*;

@SpringBootTest
class CalorieAppApplicationTests {

	@Autowired
	private AddMealController controller1;
	@Autowired
	private CreateAdminController controller2;
	@Autowired
	private DeleteMealController controller3;
	@Autowired
	private DeleteUserController controller4;
	@Autowired
	private EditMealController controller5;
	@Autowired
	private IndexController controller6;
	@Autowired
	private LoginController controller7;
	@Autowired
	private RegisterController controller8;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller1).isNotNull();
		assertThat(controller2).isNotNull();
		assertThat(controller3).isNotNull();
		assertThat(controller4).isNotNull();
		assertThat(controller5).isNotNull();
		assertThat(controller6).isNotNull();
		assertThat(controller7).isNotNull();
		assertThat(controller8).isNotNull();
	}
}