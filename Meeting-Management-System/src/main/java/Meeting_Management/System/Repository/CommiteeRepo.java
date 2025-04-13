package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.Commitee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommiteeRepo extends JpaRepository<Commitee,Long> {
    public boolean existsByCommitteeName(String commiteeName);
    public Commitee findByCommitteeName(String commiteeName);
    @Query(value = "SELECT * FROM minut.commitee WHERE commitee_type_id = :commiteeTypeId", nativeQuery = true)
    List<Commitee> findAllByCommitee(@Param("commiteeTypeId") long commiteeTypeId);

    @Query(value = "SELECT * FROM minut.commitee WHERE office = :officeId", nativeQuery = true)
    List<Commitee> findAllByOffice(@Param("officeId") long officeId);

}
