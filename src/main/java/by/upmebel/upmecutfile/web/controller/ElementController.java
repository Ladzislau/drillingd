package by.upmebel.upmecutfile.web.controller;

import by.upmebel.upmecutfile.service.ElementService;
import by.upmebel.upmecutfile.service.ElementSideService;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.web.dto.request.element.CreateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.element.UpdateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.request.hole.UpdateHoleRequest;
import by.upmebel.upmecutfile.web.dto.response.element.ElementInfo;
import by.upmebel.upmecutfile.web.dto.response.element.ElementsPageResponse;
import by.upmebel.upmecutfile.web.dto.response.hole.HoleInfo;
import by.upmebel.upmecutfile.web.dto.response.side.ElementSideInfo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/elements")
@RequiredArgsConstructor
public class ElementController {

    private final ElementService elementService;
    private final ElementSideService elementSideService;
    private final HoleService holeService;

    @Operation(
            summary = "Получение детали",
            description = "Получение детали по её id"
    )
    @GetMapping("/{elementId}")
    public ResponseEntity<ElementInfo> getElement(@PathVariable("elementId") Long elementId) {
        ElementInfo response = elementService.getElementById(elementId);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Получение деталей",
            description = "Получение страницы списка деталей. Пагинация настраивается параметрами запроса"
    )
    @GetMapping
    public ResponseEntity<ElementsPageResponse> getElements(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ElementsPageResponse response = elementService.getElements(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Создание детали",
            description = "Для создания детали, необходимо предоставить ее название и список сторон. " +
                    "Список должен состоять из 2, 3 или 6 сторон, причем из предоставленных сторон, " +
                    "должна иметься возможность составить параллелепипед. Если предоставлено 2 стороны, " +
                    "то 3-яя будет рассчитана автоматически. " +
                    "Когда стороны 3 – они клонируются и сохраняется все 6 сторон параллелепипеда"
    )
    @PostMapping
    public ResponseEntity<ElementInfo> createElement(@RequestBody @Valid CreateElementRequest request) {
        ElementInfo response = elementService.createElement(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Обновление детали",
            description = "Для обновления детали, необходимо предоставить id в ее обновленные название и список сторон. " +
                    "Список должен состоять из 2, 3 или 6 сторон, причем из предоставленных сторон, " +
                    "должна иметься возможность составить параллелепипед. Если предоставлено 2 стороны, " +
                    "то 3-яя будет рассчитана автоматически. Когда стороны 3 – они клонируются и сохраняется все 6 сторон параллелепипеда. " +
                    "При обновлении детали, все отверстия автоматически удаляются"
    )
    @PutMapping("/{elementId}")
    public ResponseEntity<ElementInfo> updateElement(@PathVariable("elementId") Long elementId,
                                                     @RequestBody @Valid UpdateElementRequest request) {
        ElementInfo response = elementService.updateElement(elementId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Удаление детали",
            description = "При удалении детали, удаляются все связанные с ней стороны и отверстия"
    )
    @DeleteMapping("/{elementId}")
    public void deleteElement(@PathVariable("elementId") Long elementId) {
        elementService.deleteById(elementId);
    }

    @Operation(
            summary = "Получение стороны",
            description = "Получение стороны по id"
    )
    @GetMapping("/{elementId}/sides/{sideId}")
    public ResponseEntity<ElementSideInfo> getSide(@PathVariable("elementId") Long elementId,
                                                   @PathVariable("sideId") Long sideId) {
        ElementSideInfo response = elementSideService.getSideByIds(elementId, sideId);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Получение отверстия",
            description = "Получение отверстия по id"
    )
    @GetMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public ResponseEntity<HoleInfo> getHole(@PathVariable("elementId") Long elementId,
                                            @PathVariable("sideId") Long sideId,
                                            @PathVariable("holeId") Long holeId) {
        HoleInfo response = holeService.getHoleByIds(elementId, sideId, holeId);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Добавление отверстия",
            description = "Добавление отверстия на определенную сторону детали. Глубина отверстия не должна превышать " +
                    "высоту детали по соответствующей стороне. Координаты отверстия задаются либо положительным числом, " +
                    "либо математическим выражением, с операторами '+-*/' и аргументами 'L' – длина стороны, " +
                    "'B' – ширина стороны, 'H' – высота детали относительно стороны, которые впоследствии заменяются " +
                    "соответствующими значениями"
    )
    @PostMapping("/{elementId}/sides/{sideId}/holes")
    public ResponseEntity<HoleInfo> addHole(@PathVariable("elementId") Long elementId,
                                            @PathVariable("sideId") Long sideId,
                                            @RequestBody @Valid CreateHoleRequest request) {
        HoleInfo response = holeService.createHole(elementId, sideId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Обновление отверстия",
            description = "Обновление отверстия на определенную сторону детали. Глубина отверстия не должна превышать " +
                    "высоту детали по соответствующей стороне. Координаты отверстия задаются либо положительным числом, " +
                    "либо математическим выражением, с операторами '+-*/' и аргументами 'L' – длина стороны, " +
                    "'B' – ширина стороны, 'H' – высота детали относительно стороны, которые впоследствии заменяются " +
                    "соответствующими значениями"
    )
    @PutMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public ResponseEntity<HoleInfo> updateHole(@PathVariable("elementId") Long elementId,
                                               @PathVariable("sideId") Long sideId,
                                               @PathVariable("holeId") Long holeId,
                                               @RequestBody @Valid UpdateHoleRequest request) {
        HoleInfo response = holeService.updateHole(elementId, sideId, holeId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Удаление отверстия"
    )
    @DeleteMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public void deleteHole(@PathVariable("elementId") Long elementId,
                           @PathVariable("sideId") Long sideId,
                           @PathVariable("holeId") Long holeId) {
        holeService.deleteById(elementId, sideId, holeId);
    }

}
