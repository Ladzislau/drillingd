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

    @GetMapping
    public ResponseEntity<ElementsPageResponse> getElements(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ElementsPageResponse response = elementService.getElements(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ElementInfo> createElement(@RequestBody @Valid CreateElementRequest request) {
        ElementInfo response = elementService.createElement(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{elementId}")
    public ResponseEntity<ElementInfo> getElement(@PathVariable("elementId") Long elementId) {
        ElementInfo response = elementService.getElementById(elementId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{elementId}")
    public ResponseEntity<ElementInfo> updateElement(@PathVariable("elementId") Long elementId,
                                                     @RequestBody @Valid UpdateElementRequest request) {
        ElementInfo response = elementService.updateElement(elementId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{elementId}")
    public void deleteElement(@PathVariable("elementId") Long elementId) {
        elementService.deleteById(elementId);
    }

    @GetMapping("/{elementId}/sides/{sideId}")
    public ResponseEntity<ElementSideInfo> getSide(@PathVariable("elementId") Long elementId,
                                                   @PathVariable("sideId") Long sideId){
        ElementSideInfo response = elementSideService.getSideByIds(elementId, sideId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{elementId}/sides/{sideId}/holes")
    public ResponseEntity<HoleInfo> addHole(@PathVariable("elementId") Long elementId,
                                            @PathVariable("sideId") Long sideId,
                                            @RequestBody @Valid CreateHoleRequest request) {
        HoleInfo response = holeService.createHole(elementId, sideId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public ResponseEntity<HoleInfo> getHole(@PathVariable("elementId") Long elementId,
                                               @PathVariable("sideId") Long sideId,
                                               @PathVariable("holeId") Long holeId) {
        HoleInfo response = holeService.getHoleByIds(elementId, sideId, holeId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public ResponseEntity<HoleInfo> updateHole(@PathVariable("elementId") Long elementId,
                                               @PathVariable("sideId") Long sideId,
                                               @PathVariable("holeId") Long holeId,
                                               @RequestBody @Valid UpdateHoleRequest request) {
        HoleInfo response = holeService.updateHole(elementId, sideId, holeId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public void deleteHole(@PathVariable("elementId") Long elementId,
                           @PathVariable("sideId") Long sideId,
                           @PathVariable("holeId") Long holeId) {
        holeService.deleteById(elementId, sideId, holeId);
    }

}
