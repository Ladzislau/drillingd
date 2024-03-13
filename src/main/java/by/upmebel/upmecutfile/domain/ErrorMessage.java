package by.upmebel.upmecutfile.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorMessage(

        @Schema(
                description = "Название ошибки"
        )
        String title,

        @Schema(
                description = "Код ошибки",
                example = "404"
        )
        int code,

        @Schema(
                description = "Описание ошибки",
                example = "Описание ошибки"
        )
        String message) {
}

