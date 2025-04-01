package Repository;

import Entity.Commitee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommiteeRepo extends JpaRepository<Commitee,Integer> {
}
