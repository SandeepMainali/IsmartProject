package Controller.minut;

import dto.minut.MeetMinutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Service.minut.MeetMinutService;
import exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/meetminut")
public class MeetMinutController {

    @Autowired
    private MeetMinutService meetMinutService;

    // Get all MeetMinut records
    @GetMapping
    public ResponseEntity<Object> getAllMeetMinuts() {
        try {
            List<MeetMinutDTO> minutes = meetMinutService.getAllMeetMinuts();
            return ResponseEntity.ok(minutes);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve meeting minutes: " + ex.getMessage());
        }
    }

    // Get MeetMinut by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMeetMinutById(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Valid meeting minute ID is required");
            }

            MeetMinutDTO minute = meetMinutService.getMeetMinutById(id);
            return ResponseEntity.ok(minute);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve meeting minute: " + ex.getMessage());
        }
    }

    // Create new MeetMinut
    @PostMapping("/create")
    public ResponseEntity<Object> createMeetMinut(@RequestBody MeetMinutDTO dto) {
        try {
            // Basic validation
            if (dto.getMintType() == null || dto.getMintType().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Minute type is required");
            }
            if (dto.getFinYear() == null || dto.getFinYear().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Financial year is required");
            }
            if (dto.getMeetDate() == null) {
                return ResponseEntity.badRequest().body("Meeting date is required");
            }
            if (dto.getChairPerson() == null || dto.getChairPerson().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Chair person is required");
            }

            MeetMinutDTO created = meetMinutService.createMeetMinut(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create meeting minute: " + ex.getMessage());
        }
    }

    // Update MeetMinut
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMeetMinut(@PathVariable Long id, @RequestBody MeetMinutDTO dto) {
        try {
            // Validate ID and basic fields
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Valid meeting minute ID is required");
            }
            if (dto.getMintType() == null || dto.getMintType().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Minute type is required");
            }
            if (dto.getFinYear() == null || dto.getFinYear().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Financial year is required");
            }
            if (dto.getMeetDate() == null) {
                return ResponseEntity.badRequest().body("Meeting date is required");
            }

            MeetMinutDTO updated = meetMinutService.updateMeetMinut(id, dto);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update meeting minute: " + ex.getMessage());
        }
    }

    // Delete MeetMinut
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMeetMinut(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Valid meeting minute ID is required");
            }

            meetMinutService.deleteMeetMinut(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete meeting minute: " + ex.getMessage());
        }
    }
}