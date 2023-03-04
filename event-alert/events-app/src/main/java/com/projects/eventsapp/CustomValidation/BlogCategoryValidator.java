package com.projects.eventsapp.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class BlogCategoryValidator implements ConstraintValidator<BlogCategoryValidation, String> {

    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {
        List list = Arrays.asList(new String[]{"STARS", "ACCIDENT", "SUSPICIOUS_BEHAVIOUR"});
        return list.contains(category);
    }
}
