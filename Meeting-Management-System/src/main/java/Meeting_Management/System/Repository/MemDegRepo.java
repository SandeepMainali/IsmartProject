package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.MemDeg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemDegRepo extends JpaRepository<MemDeg, Integer> {
}
