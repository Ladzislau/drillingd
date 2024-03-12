package by.upmebel.upmecutfile.web.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ElementsPageResponse(
        @Schema(
                description = "Номер текущей страницы",
                example = "1"
        )
        int pageNumber,

        @Schema(
                description = "Количество вмещаемых элементов на странице",
                example = "1"
        )
        int pageSize,

        @Schema(
                description = "Количество всех страниц",
                example = "1"
        )
        int totalPage,

        @Schema(
                description = "Количество всех элементов",
                example = "1"
        )
        long totalElements,

        @Schema(
                description = "Контент страницы (список деталей)"
        )
        List<ElementResponse> elements) {
}
