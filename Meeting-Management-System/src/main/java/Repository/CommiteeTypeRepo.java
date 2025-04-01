package Repository;

import Entity.CommiteeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommiteeTypeRepo extends JpaRepository<CommiteeType,Integer> {
}
