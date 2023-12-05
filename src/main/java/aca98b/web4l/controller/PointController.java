package aca98b.web4l.controller;

import aca98b.web4l.model.request.PointRequest;
import aca98b.web4l.model.response.PointResponse;
import aca98b.web4l.service.ElementService;
import aca98b.web4l.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointController {
    private final ElementService elementService;
    private final UserService userService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @PostMapping("/add")
    public ResponseEntity<PointResponse> createPoint(@RequestBody PointRequest request) {
        return ResponseEntity.ok(elementService.create(request));
    }

    @GetMapping("/list")
    public ResponseEntity<PointResponse> getElements(){
        return ResponseEntity.ok(elementService.list());
    }

    @PostMapping("/clear")
    public ResponseEntity<PointResponse> deleteAll() {
        return ResponseEntity.ok(elementService.clearTable());
    }
}
