package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByUserNameAndIdNot(String userName, Integer id);
    boolean existsByEmailAndIdNot(String email, Integer id);
//    boolean existsByPContactAndIdNot(String pcontact, Integer id);
    Optional<User> findByUserName(String userName);
}
