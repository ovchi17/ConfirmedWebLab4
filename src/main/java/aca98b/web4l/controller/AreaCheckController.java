package aca98b.web4l.controller;

import aca98b.web4l.model.entities.PointElement;
import aca98b.web4l.model.response.PointsResponse;
import aca98b.web4l.service.implementation.ElementServiceImplementation;
import aca98b.web4l.utils.AreaCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AreaCheckController {
    private final ElementServiceImplementation elementService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @PostMapping("/add")
    public ResponseEntity<PointsResponse> checkArea(@RequestBody PointElement pointElement){

        LocalTime curTime = LocalTime.now();
        String currentTime = curTime.format(formatter);
        long scriptStart = System.nanoTime();
        boolean result = AreaCheck.check(pointElement.getX(), pointElement.getY(), pointElement.getR());
        String scriptTime = String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001);
        pointElement.setTime(currentTime);
        pointElement.setExecutionTime(scriptTime);
        pointElement.setResult(result);


        return ResponseEntity.ok(
                PointsResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .pointsData(Map.of("point", elementService.create(pointElement)))
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<PointsResponse> getElements(){
        return ResponseEntity.ok(
                PointsResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .pointsData(Map.of("points", elementService.list()))
                        .message("points loaded")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }

    @PostMapping("/get")
    public ResponseEntity<PointsResponse> getElementById(@RequestBody Long id) {
        return ResponseEntity.ok(
                PointsResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .pointsData(Map.of("point", elementService.get(id)))
                        .message("user logged in")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
