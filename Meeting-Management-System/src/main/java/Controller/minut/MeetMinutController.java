package Controller.minut;

import dto.minut.MeetMinutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Service.minut.MeetMinutService;

import java.util.List;

@RestController
@RequestMapping("/api/meetminut")
public class MeetMinutController {

    @Autowired
    private MeetMinutService meetMinutService;

    // Get all MeetMinut records
    @GetMapping
    public ResponseEntity<List<MeetMinutDTO>> getAllMeetMinuts() {
        return ResponseEntity.ok(meetMinutService.getAllMeetMinuts());
    }

    // Get MeetMinut by ID
    @GetMapping("/{id}")
    public ResponseEntity<MeetMinutDTO> getMeetMinutById(@PathVariable Long id) {
        return ResponseEntity.ok(meetMinutService.getMeetMinutById(id));
    }

    // Create new MeetMinut
    @PostMapping
    public ResponseEntity<MeetMinutDTO> createMeetMinut(@RequestBody MeetMinutDTO dto) {
        MeetMinutDTO created = meetMinutService.createMeetMinut(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Update MeetMinut
    @PutMapping("/{id}")
    public ResponseEntity<MeetMinutDTO> updateMeetMinut(@PathVariable Long id, @RequestBody MeetMinutDTO dto) {
        MeetMinutDTO updated = meetMinutService.updateMeetMinut(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete MeetMinut
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeetMinut(@PathVariable Long id) {
        meetMinutService.deleteMeetMinut(id);
        return ResponseEntity.noContent().build();
    }
}