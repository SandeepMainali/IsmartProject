package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.Commitee;
import Meeting_Management.System.Entity.CommiteeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommiteeRepo extends JpaRepository<Commitee,Integer> {
    long countByCommiteeType(CommiteeType type);
}
