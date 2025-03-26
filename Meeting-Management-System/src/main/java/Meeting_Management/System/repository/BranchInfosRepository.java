package Meeting_Management.System.repository;

import Meeting_Management.System.entity.BranchInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchInfosRepository extends JpaRepository<BranchInfos, Integer> {
}
