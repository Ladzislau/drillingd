package by.upmebel.upmecutfile.web.dto.element;

import by.upmebel.upmecutfile.validation.annotation.FixedListSize;
import by.upmebel.upmecutfile.validation.annotation.ParallelogramSides;
import by.upmebel.upmecutfile.web.dto.side.ElementSideRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateElementRequest(
        @Schema(
                description = "Название детали",
                example = "ОМПК18-c"
        )
        @NotEmpty(message = "У детали должно быть название")
        String name,

        @Schema(
                description = "Список сторон параллелепипеда (размер: 2, 3 или 6)"
        )
        @NotNull(message = "Деталь должна иметь стороны")
        @FixedListSize(
                allowedSizes = {2, 3, 6},
                message = "Для составления детали в виде параллелограмма необходимо указать 2, 3 или 6 сторон"
        )
        @ParallelogramSides(message = "Из предоставленных сторон невозможно составить деталь в виде параллелограмма")
        @Valid List<ElementSideRequest> sides) {

}
