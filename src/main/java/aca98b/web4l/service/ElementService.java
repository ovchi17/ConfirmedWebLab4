package aca98b.web4l.service;


import aca98b.web4l.model.entities.Point;
import aca98b.web4l.model.entities.User;
import aca98b.web4l.model.entities.repo.PointRepository;
import aca98b.web4l.model.entities.repo.UserRepository;
import aca98b.web4l.model.request.PointRequest;
import aca98b.web4l.model.response.PointResponse;
import aca98b.web4l.utils.AreaCheck;
import org.apache.commons.compress.utils.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ElementService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    public PointResponse create(PointRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        String currentTime = LocalTime.now().format(formatter);
        long scriptStart = System.nanoTime();

        Point point = Point.builder()
                .x(request.getX())
                .y(request.getY())
                .r(request.getR())
                .result(AreaCheck.check(request.getX(), request.getY(), request.getR()))
                .time(currentTime)
                .executionTime(String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001))
                .ownerId(currentUser)
                .build();

        return PointResponse.builder()
                .pointsData(Map.of("point", pointRepository.save(point)))
                .build();
    }

    public PointResponse list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return PointResponse.builder()
                .pointsData(Map.of("point", Lists.newArrayList(pointRepository.findAll().iterator())))
                .build();
    }

    public PointResponse clearTable() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        List<Point> removedPoints = Lists.newArrayList(pointRepository.findAll().iterator());
        pointRepository.deleteAllByOwnerId(userRepository.findByUsername(currentUser.getUsername()));
        return PointResponse.builder()
                .pointsData(Map.of("cleared", removedPoints))
                .build();
    }
}