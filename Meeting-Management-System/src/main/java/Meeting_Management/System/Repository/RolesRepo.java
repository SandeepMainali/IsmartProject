package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RolesRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);
    boolean existsByRoleAlias(String roleAlias);
    boolean existsByRoleName(String roleName);
    boolean existsByRoleNameAndIdNot(String roleName, Integer id);
    boolean existsByRoleAliasAndIdNot(String roleAlias, Integer id);
}
