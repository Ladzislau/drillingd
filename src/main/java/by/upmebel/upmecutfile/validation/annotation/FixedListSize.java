package by.upmebel.upmecutfile.validation.annotation;

import by.upmebel.upmecutfile.validation.validator.FixedListSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= FixedListSizeValidator.class)
public @interface FixedListSize {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] allowedSizes();
}
