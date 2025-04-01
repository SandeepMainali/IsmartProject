package Repository;

import Entity.BranchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchInfoRepo extends JpaRepository<BranchInfo,Integer> {
}
