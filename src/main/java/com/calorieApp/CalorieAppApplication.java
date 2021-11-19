package com.calorieApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.calorieApp.domain.FunctionsInterface;
import com.calorieApp.domain.User;
import com.calorieApp.domain.UserRepository;

@SpringBootApplication
public class CalorieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalorieAppApplication.class, args);
	}

	@Autowired
	UserRepository userRepo;
	@Autowired
	FunctionsInterface functions;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return (args) -> {
			User user = new User("user", "user", "user@user.com",
					"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK",
					"$2a$10$T.fEvFa9UpgdacosOAUvaubtI/hPcGlEV2kMzAJTSLa70Zg72GHhK", "USER");
			User user2 = new User("admin", "admin", "admin@admin.com",
					"$2a$10$sOonsPAgDI.vBCRr.t9lueyfXxP1eEKLaRmf1PAfmb4GiWG0zU5lu",
					"$2a$10$sOonsPAgDI.vBCRr.t9lueyfXxP1eEKLaRmf1PAfmb4GiWG0zU5lu", "ADMIN");
			if (userRepo.findByEmail(user.getEmail()) == null) {
				userRepo.save(user);
			}
			if (userRepo.findByEmail(user2.getEmail()) == null) {
				userRepo.save(user2);
			}
		};
	};
}