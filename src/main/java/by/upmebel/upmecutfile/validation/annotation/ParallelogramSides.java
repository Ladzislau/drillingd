package by.upmebel.upmecutfile.validation.annotation;

import by.upmebel.upmecutfile.validation.validator.ParallelogramSidesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ParallelogramSidesValidator.class)
public @interface ParallelogramSides {
    String message() default "Из сторон нельзя составить параллелограмм";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
