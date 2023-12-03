package aca98b.web4l.repo;

import aca98b.web4l.model.PointElementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends CrudRepository<PointElementEntity, Long>{}