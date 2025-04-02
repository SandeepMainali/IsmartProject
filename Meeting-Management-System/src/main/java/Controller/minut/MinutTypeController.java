package Controller.minut;

import dto.minut.MinutTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Service.minut.MinutTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/minuttype")
public class MinutTypeController {

    @Autowired
    private MinutTypeService minutTypeService;

    // Get all MinutType records
    @GetMapping
    public List<MinutTypeDTO> getAllMinutTypes() {
        return minutTypeService.getAllMinutTypes();
    }

    // Get MinutType by ID
    @GetMapping("/{id}")
    public ResponseEntity<MinutTypeDTO> getMinutTypeById(@PathVariable Long id) {
        MinutTypeDTO minutTypeDTO = minutTypeService.getMinutTypeById(id);
        return ResponseEntity.ok(minutTypeDTO);
    }

    // Create a new MinutType
    @PostMapping
    public ResponseEntity<MinutTypeDTO> createMinutType(@RequestBody MinutTypeDTO minutTypeDTO) {
        MinutTypeDTO createdMinutType = minutTypeService.createMinutType(minutTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMinutType);
    }

    // Update an existing MinutType
    @PutMapping("/{id}")
    public ResponseEntity<MinutTypeDTO> updateMinutType(@PathVariable Long id, @RequestBody MinutTypeDTO minutTypeDTO) {
        MinutTypeDTO updatedMinutType = minutTypeService.updateMinutType(id, minutTypeDTO);
        return ResponseEntity.ok(updatedMinutType);
    }

    // Delete a MinutType by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMinutType(@PathVariable Long id) {
        minutTypeService.deleteMinutType(id);
        return ResponseEntity.noContent().build();
    }
}
