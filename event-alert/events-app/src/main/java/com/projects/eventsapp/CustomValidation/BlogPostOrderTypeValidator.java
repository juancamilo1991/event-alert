package com.projects.eventsapp.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class BlogPostOrderTypeValidator implements ConstraintValidator<BlogPostOrderTypeValidation, String> {

    @Override
    public boolean isValid(String orderType, ConstraintValidatorContext constraintValidatorContext) {
        List list = Arrays.asList(new String[]{"ASC, DESC"});
        return list.contains(orderType);
    }
}
