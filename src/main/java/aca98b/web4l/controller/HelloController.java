package aca98b.web4l.controller;

import aca98b.web4l.model.response.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    @CrossOrigin
    public ResponseEntity<PostResponse> printHello() {
        final var response = new PostResponse();
        response.setMessage("Alexander Babushkin is a CEO of DYNX Finance...");
        response.setMessage("Ilya Ovchinnikov has never scammed grannies...");
        return ResponseEntity.ok(response);
    }
}