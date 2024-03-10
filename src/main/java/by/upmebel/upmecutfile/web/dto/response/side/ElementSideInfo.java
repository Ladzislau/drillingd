package by.upmebel.upmecutfile.web.dto.response.side;

import by.upmebel.upmecutfile.web.dto.response.hole.HoleInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ElementSideInfo(

        @Schema(
                description = "ID стороны детали",
                example = "1"
        )
        long id,

        @Schema(
                description = "Длина стороны детали",
                example = "10"
        )
        double length,

        @Schema(
                description = "Ширина стороны детали",
                example = "1"
        )
        double breadth,

        @Schema(
                description = "Список отверстий, расположенных на стороне"
        )
        List<HoleInfo> holes) {

}
