package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.BranchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchInfoRepo extends JpaRepository<BranchInfo,Integer> {
}
