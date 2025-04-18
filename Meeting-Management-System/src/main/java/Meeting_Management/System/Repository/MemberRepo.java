package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.EthnicCategory;
import Meeting_Management.System.Entity.MemType;
import Meeting_Management.System.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {
    long countByMemType(MemType type);

    Long countByStatusTrue();

    long countByEthinicity(EthnicCategory category);
}
