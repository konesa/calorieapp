package com.calorieApp.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatcherValidator.class)
@Documented
public @interface PasswordMatcher {
	String message() default "Passwords are not matching!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
