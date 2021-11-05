package com.calorieApp.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, Object> {

	@Override
	public void initialize(PasswordMatcher constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		User user = (User) obj;
		boolean valid = user.getPassword().equals(user.getMatchingPassword());
		
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("The passwords do not match!")
                    .addPropertyNode( "matchingPassword" ).addConstraintViolation();
        }

        return valid;
	}
}