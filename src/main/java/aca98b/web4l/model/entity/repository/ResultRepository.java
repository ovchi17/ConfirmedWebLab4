package aca98b.web4l.model.entity.repository;

import aca98b.web4l.model.entity.ResultEntity;
import aca98b.web4l.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    List<ResultEntity> findAllByOwnerID(UserEntity entity);

    void deleteAllByOwnerID(UserEntity entity);
}
