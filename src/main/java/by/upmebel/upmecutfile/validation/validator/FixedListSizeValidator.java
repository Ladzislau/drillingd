package by.upmebel.upmecutfile.validation.validator;

import by.upmebel.upmecutfile.validation.annotation.FixedListSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FixedListSizeValidator implements ConstraintValidator<FixedListSize, List<?>> {

    private int[] allowedSizes;

    @Override
    public void initialize(FixedListSize constraintAnnotation) {
        this.allowedSizes = constraintAnnotation.allowedSizes();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext constraintValidatorContext) {
        for (int allowedSize : allowedSizes) {
            if (list.size() == allowedSize){
                return true;
            }
        }

        return false;
    }
}
