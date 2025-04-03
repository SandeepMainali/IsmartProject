package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Integer> {
}
