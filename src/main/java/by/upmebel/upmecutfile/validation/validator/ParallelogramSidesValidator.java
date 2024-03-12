package by.upmebel.upmecutfile.validation.validator;

import by.upmebel.upmecutfile.validation.annotation.ParallelogramSides;
import by.upmebel.upmecutfile.web.dto.side.ElementSideRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ParallelogramSidesValidator implements ConstraintValidator<ParallelogramSides, List<ElementSideRequest>> {

    @Override
    public void initialize(ParallelogramSides constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<ElementSideRequest> sides, ConstraintValidatorContext constraintValidatorContext) {
        int sidesCount = sides.size();
        if (sidesCount != 2 && sidesCount != 3 && sidesCount != 6) {
            return false;
        }

        Map<Double, Integer> dimensionsFrequency = new HashMap<>();
        sides.forEach(side -> {
            int breadthCount = dimensionsFrequency.getOrDefault(side.breadth(), 0) + 1;
            int lengthCount = dimensionsFrequency.getOrDefault(side.length(), 0) + 1;
            dimensionsFrequency.put(side.breadth(), breadthCount);
            dimensionsFrequency.put(side.length(), lengthCount);
        });

        int dimensionsCount = dimensionsFrequency.size();

        if (dimensionsCount < 1 || dimensionsCount > 3) {
            return false;
        }

        if (sides.size() == 2) {
            return true;
        }

        Set<Integer> frequencySet = new HashSet<>(dimensionsFrequency.values());

        if (frequencySet.size() == 1) {
            double dimensionToCheck = sides.getFirst().length();
            int counter = 0;
            for (ElementSideRequest side : sides) {
                if (side.breadth() == dimensionToCheck || side.length() == dimensionToCheck) {
                    counter++;
                }
            }
            return counter % 2 == 0 || counter % 3 == 0;
        }

        return false;
    }

}

