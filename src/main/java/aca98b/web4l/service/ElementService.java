package aca98b.web4l.service;

import aca98b.web4l.model.Element;

import java.util.Collection;


public interface ElementService {
    Element create(Element element);
    Collection<Element> list();
    void delete(Long id);
    void clearTable();
}