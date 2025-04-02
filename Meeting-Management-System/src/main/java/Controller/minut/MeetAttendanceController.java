package Controller.minut;

import dto.minut.MeetAttendanceDTO;
import Service.minut.MeetAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meet-attendance")
public class MeetAttendanceController {

    private final MeetAttendanceService meetAttendanceService;

    public MeetAttendanceController(MeetAttendanceService meetAttendanceService) {
        this.meetAttendanceService = meetAttendanceService;
    }

    @GetMapping
    public ResponseEntity<List<MeetAttendanceDTO>> getAllMeetAttendances() {
        return ResponseEntity.ok(meetAttendanceService.getAllMeetAttendances());
    }

    @PostMapping
    public ResponseEntity<MeetAttendanceDTO> createMeetAttendance(@RequestBody MeetAttendanceDTO dto) {
        return ResponseEntity.ok(meetAttendanceService.createMeetAttendance(dto));
    }
}
