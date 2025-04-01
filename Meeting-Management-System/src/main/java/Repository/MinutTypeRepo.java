package Repository;

import Entity.MinutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinutTypeRepo extends JpaRepository<MinutType, Integer> {
}
