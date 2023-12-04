package aca98b.web4l.model.repo;

import aca98b.web4l.model.entities.PointElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends CrudRepository<PointElement, Long>{}