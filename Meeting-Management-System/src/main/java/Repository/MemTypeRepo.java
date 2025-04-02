package Repository;

import Entity.MemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemTypeRepo extends JpaRepository<MemType, Long> {
}
