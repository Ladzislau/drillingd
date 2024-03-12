package by.upmebel.upmecutfile.web.dto.element;

import by.upmebel.upmecutfile.web.dto.side.ElementSideResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.List;

public record ElementResponse(
        @Schema(
                description = "ID детали",
                example = "1"
        )
        long id,

        @Schema(
                description = "Название детали",
                example = "Король-2341"
        )
        String name,

        @Schema(
                description = "Время добавления детали"
        )
        Date createdAt,

        @Schema(
                description = "Список сторон детали"
        )
        List<ElementSideResponse> sides) {

}
