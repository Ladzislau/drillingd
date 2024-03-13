package by.upmebel.upmecutfile.web.dto.error;

import by.upmebel.upmecutfile.domain.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.List;

public record ErrorResponse(
        @Schema(
                description = "Время возникновения ошибки"
        )
        Date timestamp,

        @Schema(
                description = "Список возникнувших ошибок"
        )
        List<ErrorMessage> errors) {

}
