package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.MeetMinut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetMinutRepo extends JpaRepository<MeetMinut, Integer> {
}
