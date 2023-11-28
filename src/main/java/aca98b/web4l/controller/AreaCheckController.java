package aca98b.web4l.controller;

import aca98b.web4l.model.Result;
import aca98b.web4l.model.request.CheckHitRequest;
import aca98b.web4l.service.ResultService;
import aca98b.web4l.util.AreaChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/get-collision")
public class AreaCheckController {
    private final ResultService db;

    @Autowired
    public AreaCheckController(final ResultService db){
        this.db = db;
    }

    @PostMapping
    public ResponseEntity<Result> newCheckResult(final Principal principal,
                                                 @RequestBody CheckHitRequest request) {
        final Result result = new Result();
        result.setRequest(request);
        final long startTime = System.nanoTime();
        result.setResult(AreaChecker.getResult(request.getX(), request.getY(), request.getR()));
        final long endTime = System.nanoTime();
        result.setTime(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        result.setExecutionTime(endTime - startTime);

        db.pushToDB(result, principal);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Result>> allResultsByUser(Principal principal) {
        return ResponseEntity.ok(db.getAllByUsername(principal));
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<?> deleteAll(Principal principal) {
        db.removeAllFromUser(principal);
        return ResponseEntity.noContent().build();
    }
}