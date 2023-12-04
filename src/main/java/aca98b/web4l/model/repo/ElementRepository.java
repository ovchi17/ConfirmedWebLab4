package aca98b.web4l.model.repo;

import aca98b.web4l.model.entities.PointElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends JpaRepository<PointElement, Long> {}