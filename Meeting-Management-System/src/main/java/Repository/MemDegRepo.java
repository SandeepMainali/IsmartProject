package Repository;

import Entity.MemDeg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemDegRepo extends JpaRepository<MemDeg, Integer> {
}
