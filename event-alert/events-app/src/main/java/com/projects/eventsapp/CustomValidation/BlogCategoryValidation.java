package com.projects.eventsapp.CustomValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BlogCategoryValidator.class)
public @interface BlogCategoryValidation {

    //error message
    public String message() default "Invalid category: must be STARS, ACCIDENT or SUSPICIOUS_BEHAVIOUR";
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
