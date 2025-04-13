package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.BranchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchInfoRepo extends JpaRepository<BranchInfo,Integer> {
    Optional<BranchInfo> findBybrAlias(String alias);


    boolean existsByFullName(String fullName);
    boolean existsByFullNameLocale(String fullNameLocale);
    boolean existsByShortName(String shortName);
    boolean existsByRegNo(String regNo);
    boolean existsByPan(String pan);
    boolean existsByEmail(String email);

    List<BranchInfo> findByParentIdIsNull();


    List<BranchInfo> findByParentIdIsNotNullOrderByParentIdId();

    List<BranchInfo> findByParentId(BranchInfo parentBranch);
}
