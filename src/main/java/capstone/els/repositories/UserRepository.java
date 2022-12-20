package capstone.els.repositories;

import capstone.els.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findUserByEmail(String email);
}
