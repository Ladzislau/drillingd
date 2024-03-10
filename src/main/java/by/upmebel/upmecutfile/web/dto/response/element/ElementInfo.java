package by.upmebel.upmecutfile.web.dto.response.element;

import by.upmebel.upmecutfile.web.dto.response.side.ElementSideInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.List;

public record ElementInfo(
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
        List<ElementSideInfo> sides) {

}
