package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.MeetAttendance;
import Meeting_Management.System.Entity.MeetMinut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetAttendanceRepo extends JpaRepository<MeetAttendance, Integer> {
    List<MeetAttendance> findByMeetMinut(MeetMinut meeting);
}
