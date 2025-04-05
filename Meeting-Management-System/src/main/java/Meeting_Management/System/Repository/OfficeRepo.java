package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Integer> {
    List<Office> findByStatusTrue();

    @Query("SELECT o FROM Office o WHERE o.branchInfo.id = :branchId")
    List<Office> findByBranchInfoId(@Param("branchId") Integer branchId);
}
