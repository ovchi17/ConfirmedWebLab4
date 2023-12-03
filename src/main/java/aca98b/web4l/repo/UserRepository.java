package aca98b.web4l.repo;

import aca98b.web4l.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByUsername(String login);
    void deleteByUsername(String login);
    boolean existsByUsername (String username);
    boolean existsBySessionId(String sessionID);
    boolean sessionNonExpired(boolean isSessionIdNonExpired);
    boolean existsByPassword(String password);
}
