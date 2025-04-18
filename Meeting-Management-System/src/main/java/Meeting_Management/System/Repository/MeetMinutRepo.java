package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.MeetMinut;
import Meeting_Management.System.Entity.MinutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MeetMinutRepo extends JpaRepository<MeetMinut, Integer> {
    List<MeetMinut> findByMeetDateBetween(LocalDate startDate, LocalDate currentDate);

    long countByMintType(MinutType type);

    List<MeetMinut> findTop5ByOrderByMeetDateDesc();

    List<MeetMinut> findByMeetDateAfter(LocalDate sixMonthsAgo);
}
