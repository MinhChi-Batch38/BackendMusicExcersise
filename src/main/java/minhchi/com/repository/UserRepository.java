package minhchi.com.repository;
import minhchi.com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    User getUserByUsernameAndPassword(String username, String password);
    Optional<User> findUserByUsername(String username);
}
