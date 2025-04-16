package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Integer> {
    List<Menu> findBymenuType(String menu);

    Optional<Menu> findByMenuAlias(String menuAlias);

    boolean existsByMenuName(String menuName);

    boolean existsByMenuNameOther(String menuNameOther);

    List<Menu> findByParentMenu(Menu superMenu);
}
