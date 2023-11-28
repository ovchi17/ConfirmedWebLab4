package aca98b.web4l.service;

import aca98b.web4l.model.Result;
import aca98b.web4l.model.entity.ResultEntity;
import aca98b.web4l.model.entity.repository.ResultRepository;
import aca98b.web4l.model.entity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.ZoneId;
import java.util.List;

@Service
public class ResultService {
    private final UserRepository userRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(final UserRepository userRepository,
                         final ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
    }

    public void pushToDB(Result result, Principal username) {
        final ResultEntity entity = new ResultEntity();
        entity.setX(result.getRequest().getX());
        entity.setY(result.getRequest().getY());
        entity.setR(result.getRequest().getR());
        entity.setResult(result.isResult());
        entity.setTime(result.getTime().atZone(ZoneId.systemDefault()).toLocalDateTime());
        entity.setExecutionTime(result.getExecutionTime());
        entity.setOwnerID(userRepository.findByUsername(username.getName()));
        resultRepository.save(entity);
    }

    public List<Result> getAllByUsername(Principal principal) {
        List<ResultEntity> resultEntity = resultRepository
                .findAllByOwnerID(userRepository.findByUsername(principal.getName()));

        return resultEntity.stream().map(Result::fromEntity).toList();
    }

    @Transactional
    public void removeAllFromUser(Principal principal) {
        resultRepository.deleteAllByOwnerID(userRepository.findByUsername(principal.getName()));
    }
}