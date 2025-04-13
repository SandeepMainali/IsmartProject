package Controller.minut;

import dto.ResponseDTO;
import dto.minut.MeetAttendanceDTO;
import Service.minut.MeetAttendanceService;
import exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meet-attendance")
public class MeetAttendanceController {

    private final MeetAttendanceService meetAttendanceService;

    public MeetAttendanceController(MeetAttendanceService meetAttendanceService) {
        this.meetAttendanceService = meetAttendanceService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllMeetAttendances() {
        try {
            List<MeetAttendanceDTO> attendances = meetAttendanceService.getAllMeetAttendances();

            Map<String, Object> details = new HashMap<>();
            details.put("attendances", attendances);
            details.put("count", attendances.size());

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Successfully retrieved attendances",
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "500",
                    "Error retrieving attendances: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMeetAttendanceById(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id < 1) {
                throw new ResourceNotFoundException("Invalid attendance ID");
            }

            MeetAttendanceDTO attendance = meetAttendanceService.getMeetAttendanceById(id);

            Map<String, Object> details = new HashMap<>();
            details.put("attendance", attendance);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Successfully retrieved attendance",
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "404",
                    e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "500",
                    "Error retrieving attendance: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createMeetAttendance(@RequestBody MeetAttendanceDTO dto) {
        try {
            // Validate request data
            dto.validateForCreate();

            // Create attendance record
            MeetAttendanceDTO createdAttendance = meetAttendanceService.createMeetAttendance(dto);

            Map<String, Object> details = new HashMap<>();
            details.put("attendance", createdAttendance);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "201",
                    "Attendance record created successfully",
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResourceNotFoundException e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "400",
                    e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "500",
                    "Error creating attendance record: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMeetAttendance(@PathVariable Long id, @RequestBody MeetAttendanceDTO dto) {
        try {
            // Set ID from path parameter
            dto.setId(id);

            // Validate request data
            dto.validateForUpdate();

            // Update attendance record
            MeetAttendanceDTO updatedAttendance = meetAttendanceService.updateMeetAttendance(id, dto);

            Map<String, Object> details = new HashMap<>();
            details.put("attendance", updatedAttendance);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Attendance record updated successfully",
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "400",
                    e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "500",
                    "Error updating attendance record: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMeetAttendance(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id < 1) {
                throw new ResourceNotFoundException("Invalid attendance ID");
            }

            // Delete attendance record
            meetAttendanceService.deleteMeetAttendance(id);

            Map<String, Object> details = new HashMap<>();

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Attendance record deleted successfully",
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "404",
                    e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            Map<String, Object> details = new HashMap<>();
            details.put("error", e.getMessage());

            ResponseDTO response = new ResponseDTO(
                    "error",
                    "500",
                    "Error deleting attendance record: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}