package aca98b.web4l.service;

import aca98b.web4l.model.PointElementEntity;

import java.util.Collection;
import java.util.Optional;


public interface ElementService {
    PointElementEntity create(PointElementEntity pointElementEntity);
    Optional<PointElementEntity> get(Long id);
    Collection<PointElementEntity> list();
    void delete(Long id);
    void clearTable();
}