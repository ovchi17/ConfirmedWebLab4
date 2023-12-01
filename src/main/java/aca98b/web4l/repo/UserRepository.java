package aca98b.web4l.repo;

import aca98b.web4l.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByLogin(String login);
    void deleteByLogin(String login);
    boolean existsByPassword(String password);
}
