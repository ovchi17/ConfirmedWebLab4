package aca98b.web4l.resource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import aca98b.web4l.model.Element;
import aca98b.web4l.model.Response;
import aca98b.web4l.model.User;
import aca98b.web4l.service.implementation.ElementServiceImplementation;
import aca98b.web4l.service.implementation.UserServiceImplementation;
import aca98b.web4l.utils.AreaCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final ElementServiceImplementation elementService;
    private final UserServiceImplementation userService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody User user) throws IOException {
        if(userService.register(user)){
            return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", true))
                        .message("user registred")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()

                );
        } else {
            return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", false))
                        .message("username taken")
                        .status(HttpStatus.CONFLICT)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build()

                );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody User user){
        if(userService.verify(user)){
            return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("logged in", true))
                    .message("user logged in")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
            );
        } else {
            return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("logged in", false))
                    .message("incorrect password or login")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build()
        );
        }

    }

    @PostMapping("/check_area")
    public ResponseEntity<Response> checkArea(@RequestBody Element element){
        
            LocalTime currentTime = LocalTime.now();
            String curTime = currentTime.format(formatter);
            long scriptStart = System.nanoTime();
            boolean result = AreaCheck.check(element.getX(), element.getY(), element.getR());
            String scriptTime = String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001);
            element.setDate(curTime);
            element.setExec(scriptTime);
            element.setResult(result);


            return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(LocalDateTime.now())
                    .data(Map.of("element saved", elementService.create(element)))
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build()
            );
    }
}
