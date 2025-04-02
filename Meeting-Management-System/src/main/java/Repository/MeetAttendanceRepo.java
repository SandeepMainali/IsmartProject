package Repository;

import Entity.MeetAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetAttendanceRepo extends JpaRepository<MeetAttendance, Long> {
}
