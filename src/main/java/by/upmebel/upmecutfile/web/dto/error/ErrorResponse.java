package by.upmebel.upmecutfile.web.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public record ErrorResponse(
        @Schema(
                description = "Сообщение ошибки",
                example = "Ошибка X"
        )
        String message,

        @Schema(
                description = "Статус ответа от сервера",
                example = "404"
        )
        int status,

        @Schema(
                description = "Время возникновения ошибки"
        )
        Date timestamp) {

}
