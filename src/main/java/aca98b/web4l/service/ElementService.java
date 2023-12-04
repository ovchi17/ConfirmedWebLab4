package aca98b.web4l.service;

import aca98b.web4l.model.entities.PointElement;

import java.util.Collection;
import java.util.Optional;


public interface ElementService {
    PointElement create(PointElement pointElement);
    Optional<PointElement> get(Long id);
    Collection<PointElement> list();
    void delete(Long id);
    void clearTable();
}