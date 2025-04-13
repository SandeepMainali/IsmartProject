package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.MemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemTypeRepo extends JpaRepository<MemType, Integer> {
}
