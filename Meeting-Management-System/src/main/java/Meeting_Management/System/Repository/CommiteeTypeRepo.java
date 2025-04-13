package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.CommiteeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommiteeTypeRepo extends JpaRepository<CommiteeType,Long> {

    public CommiteeType findByCommiteeTypeName(String commiteeTypeName);
    public boolean existsByCommiteeTypeName(String commiteeTypeName);
    public boolean existsById(long id);
//    @Query("SELECT c.committee_type_name FROM CommiteeType c WHERE c.id = :id")
//    public String findNameById(long id);
}
