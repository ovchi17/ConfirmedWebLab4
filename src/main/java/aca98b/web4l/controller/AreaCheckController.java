package aca98b.web4l.controller;

import aca98b.web4l.model.PointElementEntity;
import aca98b.web4l.model.response.Response;
import aca98b.web4l.service.implementation.ElementServiceImplementation;
import aca98b.web4l.service.implementation.UserServiceImplementation;
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
    public ResponseEntity<Response> checkArea(@RequestBody PointElementEntity pointElementEntity){

        LocalTime curTime = LocalTime.now();
        String currentTime = curTime.format(formatter);
        long scriptStart = System.nanoTime();
        boolean result = AreaCheck.check(pointElementEntity.getX(), pointElementEntity.getY(), pointElementEntity.getR());
        String scriptTime = String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001);
        pointElementEntity.setTime(currentTime);
        pointElementEntity.setExecutionTime(scriptTime);
        pointElementEntity.setResult(result);


        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("element saved", elementService.create(pointElementEntity)))
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getElements(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("elements", elementService.list()))
                        .message("elements loaded")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()

        );
    }

    @PostMapping("/get")
    public ResponseEntity<Response> getElementById(@RequestBody Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("point", elementService.get(id)))
                        .message("user logged in")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
