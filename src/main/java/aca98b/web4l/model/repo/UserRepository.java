package aca98b.web4l.model.repo;

import aca98b.web4l.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername (String username);
    boolean existsBySessionId(String sessionID);
    boolean sessionNonExpired(boolean isSessionIdNonExpired);
    boolean existsByPassword(String password);
}
