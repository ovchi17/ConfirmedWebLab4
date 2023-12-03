package aca98b.web4l.service.implementation;


import aca98b.web4l.model.PointElementEntity;
import aca98b.web4l.repo.ElementRepository;
import aca98b.web4l.service.ElementService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ElementServiceImplementation implements ElementService {
    private final ElementRepository elementRepository;

    @Override
    public PointElementEntity create(PointElementEntity pointElementEntity) {
        log.info("creating new element");
        return elementRepository.save(pointElementEntity);
    }

    @Override
    public Optional<PointElementEntity> get (Long id) {
        log.info("getting element");
        return elementRepository.findById(id);
    }

    @Override
    public Collection<PointElementEntity> list() {
        log.info("Fetching all elements");

        return Lists.newArrayList(elementRepository.findAll().iterator());
    }

    @Override
    public void delete(Long id) {
        log.info("deleting element {}", id);
        elementRepository.deleteById(id);
    }

    @Override
    public void clearTable() {
        log.info("clearing table");
        elementRepository.deleteAll();
    }
    
}
