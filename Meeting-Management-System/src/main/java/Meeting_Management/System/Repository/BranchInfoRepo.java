package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.BranchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchInfoRepo extends JpaRepository<BranchInfo,Integer> {
    Optional<BranchInfo> findBybrAlias(String alias);




}
