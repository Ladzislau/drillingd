package by.upmebel.upmecutfile.web.dto.side;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record ElementSideRequest(

        @Schema(
                description = "Длина стороны детали (параллелепипеда)",
                example = "23"
        )
        @Positive(message = "Длина должна быть больше нуля")
        double length,

        @Schema(
                description = "Ширина стороны детали (параллелепипеда)",
                example = "18"
        )
        @Positive(message = "Ширина должна быть больше нуля")
        double breadth) {

}
