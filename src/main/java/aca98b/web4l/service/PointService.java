package aca98b.web4l.service;


import aca98b.web4l.model.entities.Point;
import aca98b.web4l.model.entities.User;
import aca98b.web4l.model.entities.repo.PointRepository;
import aca98b.web4l.model.entities.repo.UserRepository;
import aca98b.web4l.model.request.PointRequest;
import aca98b.web4l.model.response.PointResponse;
import aca98b.web4l.utils.AreaChecker;
import org.apache.commons.compress.utils.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PointService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private final float MIN_X = -2f;
    private final float MAX_X = 2f;
    private final float MIN_Y = -5f;
    private final float MAX_Y = 3f;
    private final float MIN_R = 0f;
    private final float MAX_R = 2f;


    public PointResponse create(PointRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        String currentTime = LocalTime.now().format(formatter);
        long scriptStart = System.nanoTime();
        String validationMessage = validate(request.getX(), request.getY(), request.getR());

        if (validationMessage.equals("success")) {
            Point point = Point.builder()
                    .x(request.getX())
                    .y(request.getY())
                    .r(request.getR())
                    .result(AreaChecker.getResult(request.getX(), request.getY(), request.getR()))
                    .time(currentTime)
                    .executionTime(String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001))
                    .ownerId(currentUser)
                    .build();

            pointRepository.save(point);

            currentUser.setPassword("NOT YOUR BUSINESS.");
            point.setOwnerId(currentUser);

            return PointResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Point created.")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .pointsData(Map.of("point", point))
                    .build();
        } else {
            return PointResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message(validationMessage)
                    .status(HttpStatus.FORBIDDEN)
                    .statusCode(HttpStatus.FORBIDDEN.value())
                    .build();
        }
    }

    public PointResponse list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<Point> points = pointRepository.findAllByOwnerId(currentUser);

        for (Point point : points) {
            User owner = point.getOwnerId();
            owner.setPassword("NOT YOUR BUSINESS");
        }

        return PointResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message("Points loaded.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .devMessage(String.valueOf(points.size()))
                .pointsData(Map.of("point", Lists.newArrayList(points.iterator())))
                .build();
    }

    public PointResponse clearTable() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<Point> removedPoints = Lists.newArrayList(pointRepository.findAllByOwnerId(currentUser).iterator());

        for (Point point : removedPoints) {
            User owner = point.getOwnerId();
            owner.setPassword("NOT YOUR BUSINESS");
        }

        pointRepository.deleteAllByOwnerId(userRepository.findByUsername(currentUser.getUsername()));
        return PointResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message("Table cleared.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .pointsData(Map.of("cleared", removedPoints))
                .build();
    }

    private String validate(Object x, Object y, Object r){
        if (x instanceof Float && y instanceof Float && r instanceof Float) {
            if ((float) x >= MIN_X && (float) x <= MAX_X && (float) y >= MIN_Y && (float) y <= MAX_Y && (float) r > MIN_R && (float) r <= MAX_R) {
                return "success";
            }
            return "wrong float value";
        }
        return "wrong parameter type";
    }

}