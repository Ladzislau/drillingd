package by.upmebel.upmecutfile.web.dto.response.hole;

import io.swagger.v3.oas.annotations.media.Schema;

public record HoleInfo(

        @Schema(
                description = "ID отверстия",
                example = "1"
        )
        long id,

        @Schema(
                description = "Диаметр отверстия",
                example = "10.5"
        )
        double diameter,

        @Schema(
                description = "Глубина отверстия",
                example = "5"
        )
        double depth,

        @Schema(
                description = "Скорость входа сверла",
                example = "30"
        )
        int drillingEntrySpeed,

        @Schema(
                description = "Скорость выхода сверла",
                example = "35"
        )
        int drillingExitSpeed,

        @Schema(
                description = "Координата отверстия по длине стороны. Принимается в виде положительного числа или математического выражения с операциями (+-/*) и переменными (L, B, H)",
                example = "24"
        )
        double coordinateByLength,

        @Schema(
                description = "Координата отверстия по длине стороны. Принимается в виде положительного числа или математического выражения с операциями (+-/*) и переменными (L, B, H)",
                example = "10"
        )
        double coordinateByBreadth) {
}
