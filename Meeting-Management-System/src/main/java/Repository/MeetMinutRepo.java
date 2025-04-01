package Repository;

import Entity.MeetMinut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetMinutRepo extends JpaRepository<MeetMinut, Integer> {
}
