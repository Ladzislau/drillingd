package by.upmebel.upmecutfile.web.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

public record ValidationErrorResponse(

        @Schema(
                description = "Название ошибки",
                example = "Ошибка валидации"
        )
        String title,

        @Schema(
                description = "Название ошибки",
                example = "400"
        )
        int status,

        @Schema(
                description = "Поля, которые не прошли валидацию",
                example = "{ \"field1\": [\"Ошибка валидации Х\"], \"field2\": [\"Ошибка валидации Х\"] }"
        )
        Map<String, List<String>> fieldErrors) {

}
