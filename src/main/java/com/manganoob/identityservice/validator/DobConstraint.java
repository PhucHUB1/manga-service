package com.manganoob.identityservice.validator;
import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = DobValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DobConstraint {
    String message() default "Invalid date of birth";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min();
}
