package by.upmebel.upmecutfile.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/component")
public class ComponentController {

    @GetMapping
    public ResponseEntity<?> getAllComponents(){

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{componentId}")
    public ResponseEntity<?> getComponent(@PathVariable("componentId") Long componentId){

        return ResponseEntity.ok(null);
    }

    @PostMapping
    public void addComponent(){

    }

    @PostMapping("/{componentId}/hole")
    public void addHoles(@PathVariable("componentId") Long componentId){

    }

    @PutMapping("/{componentId}")
    public void updateComponent(@PathVariable("componentId") Long componentId){

    }

    @PutMapping("/{componentId}/hole/{holeId}")
    public void updateHole(@PathVariable("componentId") Long componentId,
                           @PathVariable("holeId") Long holeId){

    }

    @DeleteMapping("/{componentId}")
    public void deleteComponent(@PathVariable("componentId") Long componentId){

    }

    @DeleteMapping("/{componentId}/hole/{holeId}")
    public void deleteHole(@PathVariable("componentId") Long componentId,
                           @PathVariable("holeId") Long holeId){

    }

}
