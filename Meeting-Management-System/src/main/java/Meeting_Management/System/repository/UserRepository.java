package Meeting_Management.System.repository;

import Meeting_Management.System.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String userName);
    List<Users> findByRole_RoleAlias(String roleAlias);

    @Query("SELECT u FROM Users u WHERE u.role.roleAlias != 'SUPER_ADMIN'")
    List<Users> findAllNonSuperAdminUsers();
}
