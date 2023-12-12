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

    public PointResponse create(PointRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        User currentUser = (User) authentication.getPrincipal();;
        String currentTime = LocalTime.now().format(formatter);
        long scriptStart = System.nanoTime();
        String validationMessage = validate(request.getX(), request.getY(), request.getR());

//        if (currentUser.isEnable()) {
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
//        } else {
//            return PointResponse.builder()
//                    .timeStamp(LocalDateTime.now())
//                    .message("You have to authorize.")
//                    .devMessage("Account disabled.")
//                    .status(HttpStatus.UNAUTHORIZED)
//                    .statusCode(HttpStatus.UNAUTHORIZED.value())
//                    .build();
//        }
    }

    public PointResponse list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        User currentUser = (User) authentication.getPrincipal();;
        List<Point> points = pointRepository.findAllByOwnerId(currentUser);
//        if (currentUser.isEnable()) {
            return PointResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Points loaded.")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .devMessage(String.valueOf(points.size()))
                    .pointsData(Map.of("point", Lists.newArrayList(points.iterator())))
                    .build();
//        } else {
//            return PointResponse.builder()
//                    .timeStamp(LocalDateTime.now())
//                    .message("You have to authorize.")
//                    .devMessage("Account disabled.")
//                    .status(HttpStatus.UNAUTHORIZED)
//                    .statusCode(HttpStatus.UNAUTHORIZED.value())
//                    .build();
//        }
    }

    public PointResponse clearTable() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
//        if (currentUser.isEnable()) {
            List<Point> removedPoints = Lists.newArrayList(pointRepository.findAllByOwnerId(currentUser).iterator());
            pointRepository.deleteAllByOwnerId(userRepository.findByUsername(currentUser.getUsername()));
            return PointResponse.builder()
                    .timeStamp(LocalDateTime.now())
                    .message("Table cleared.")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .pointsData(Map.of("cleared", removedPoints))
                    .build();
//        } else {
//            return PointResponse.builder()
//                    .timeStamp(LocalDateTime.now())
//                    .message("You have to authorize.")
//                    .devMessage("Account disabled.")
//                    .status(HttpStatus.UNAUTHORIZED)
//                    .statusCode(HttpStatus.UNAUTHORIZED.value())
//                    .build();
//        }
    }

    private String validate(Object x, Object y, Object r){
        if (x instanceof Float && y instanceof Float && r instanceof Float) {
            float MIN_X = -2f;
            float MAX_X = 2f;
            float MIN_Y = -5f;
            float MAX_Y = 3f;
            float MIN_R = 0f;
            float MAX_R = 2f;
            if ((float) x >= MIN_X && (float) x <= MAX_X && (float) y >= MIN_Y && (float) y <= MAX_Y && (float) r > MIN_R && (float) r <= MAX_R) {
                return "success";
            }
            return "wrong float value";
        }
        return "wrong parameter type";
    }

}