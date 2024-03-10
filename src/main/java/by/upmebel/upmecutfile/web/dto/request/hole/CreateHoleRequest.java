package by.upmebel.upmecutfile.web.dto.request.hole;

import by.upmebel.upmecutfile.validation.annotation.FixedListSize;
import by.upmebel.upmecutfile.validation.annotation.ParallelogramSides;
import by.upmebel.upmecutfile.web.dto.request.side.ElementSideRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.List;

//todo – Валидировать входные данные на деление на ноль
public record CreateHoleRequest(

        @Schema(
                description = "Диаметр отверстия",
                example = "10.5"
        )
        @Positive(message = "Диаметр должен быть больше нуля")
        double diameter,

        @Schema(
                description = "Глубина отверстия",
                example = "5"
        )
        @Positive(message = "Глубина должна быть больше нуля")
        double depth,

        @Schema(
                description = "Скорость входа сверла",
                example = "30"
        )
        @Positive(message = "Скорость входа сверла должна быть больше нуля")
        int drillingEntrySpeed,

        @Schema(
                description = "Скорость выхода сверла",
                example = "35"
        )
        @Positive(message = "Скорость выхода сверла быть больше нуля")
        int drillingExitSpeed,

        @Schema(
                description = "Координата отверстия по длине стороны. Принимается в виде положительного числа или математического выражения с операциями (+-/*) и переменными (L, B, H)",
                example = "35 + H - 20 / B"
        )
        @Pattern(regexp = "(\\s*([LlBbHh]|(\\d+(\\.\\d+)?))(\\s*[+\\-*/]\\s*([LlBbHh]|(\\d+(\\.\\d+)?))\\s*)*)$")
        String coordinateByLength,

        @Schema(
                description = "Координата отверстия по длине стороны. Принимается в виде положительного числа или математического выражения с операциями (+-/*) и переменными (L, B, H)",
                example = "35 + H - 20 / B"
        )
        @Pattern(regexp = "(\\s*([LlBbHh]|(\\d+(\\.\\d+)?))(\\s*[+\\-*/]\\s*([LlBbHh]|(\\d+(\\.\\d+)?))\\s*)*)$")
        String coordinateByBreadth) {

}
