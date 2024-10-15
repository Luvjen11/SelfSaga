package jennifer.SelfSaga.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);
    
    // check user with a particular username exists
    Optional<User> findByUsername(String username);

}
