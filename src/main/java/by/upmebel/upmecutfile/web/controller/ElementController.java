package by.upmebel.upmecutfile.web.controller;

import by.upmebel.upmecutfile.service.ElementService;
import by.upmebel.upmecutfile.service.ElementSideService;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.web.dto.request.element.CreateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.element.UpdateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.hole.CreateHolesRequest;
import by.upmebel.upmecutfile.web.dto.request.hole.UpdateHoleRequest;
import by.upmebel.upmecutfile.web.dto.response.element.CreateElementResponse;
import by.upmebel.upmecutfile.web.dto.response.element.GetElementResponse;
import by.upmebel.upmecutfile.web.dto.response.element.GetElementsPageResponse;
import by.upmebel.upmecutfile.web.dto.response.element.UpdateElementResponse;
import by.upmebel.upmecutfile.web.dto.response.hole.CreateHolesResponse;
import by.upmebel.upmecutfile.web.dto.response.hole.UpdateHoleResponse;
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
    public ResponseEntity<GetElementsPageResponse> getElements(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        GetElementsPageResponse response = elementService.getElements(pageable);
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<CreateElementResponse> createElement(@RequestBody CreateElementRequest request) {
        CreateElementResponse response = elementService.createElement(request);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{elementId}")
    public ResponseEntity<GetElementResponse> getElement(@PathVariable("elementId") Long elementId) {
//        GetElementResponse response = elementService.getElementById(elementId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{elementId}")
    public ResponseEntity<UpdateElementResponse> updateElement(@PathVariable("elementId") Long elementId,
                                                               @RequestBody UpdateElementRequest request) {
//        UpdateElementResponse response = elementService.updateElement(elementId, request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{elementId}")
    public void deleteElement(@PathVariable("elementId") Long elementId) {

//        elementService.deleteById(elementId);
    }

    @PostMapping("/{elementId}/sides/{sideId}/holes")
    public ResponseEntity<CreateHolesResponse> addHoles(@PathVariable("elementId") Long elementId,
                                                        @PathVariable("sideId") Long sideId,
                                                        @RequestBody CreateHolesRequest request) {
//        CreateHolesResponse response = holeService.createHoles(elementId, sideId, request);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public ResponseEntity<UpdateHoleResponse> updateHole(@PathVariable("elementId") Long elementId,
                                                         @PathVariable("sideId") Long sideId,
                                                         @PathVariable("holeId") Long holeId,
                                                         @RequestBody UpdateHoleRequest request) {
//        чекнуть айдишники на валидность
//        CreateHolesResponse response = holeService.updateHole(elementId, sideId, holeId, request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{elementId}/sides/{sideId}/holes/{holeId}")
    public void deleteHole(@PathVariable("elementId") Long elementId,
                           @PathVariable("sideId") String sideId,
                           @PathVariable("holeId") Long holeId) {

//        holeService.deleteById(elementId, sideId, holeId);
    }

}
