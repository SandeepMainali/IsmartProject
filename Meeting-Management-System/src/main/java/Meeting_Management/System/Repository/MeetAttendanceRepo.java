package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.MeetAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetAttendanceRepo extends JpaRepository<MeetAttendance, Integer> {
}
