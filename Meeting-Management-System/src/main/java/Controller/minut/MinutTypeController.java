package Controller.minut;

import dto.ResponseDTO;
import dto.minut.MinutTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exception.ResourceNotFoundException;

import Service.minut.MinutTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/minuttype")
public class MinutTypeController {

    @Autowired
    private MinutTypeService minutTypeService;

    // Get all MinutType records
    @GetMapping
    public ResponseEntity<Object> getAllMinutTypes() {
        try {
            List<MinutTypeDTO> minutTypes = minutTypeService.getAllMinutTypes();

            Map<String, Object> details = new HashMap<>();
            details.put("minutTypes", minutTypes);
            details.put("count", minutTypes.size());

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Successfully retrieved minute types",
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
                    "Error retrieving minute types: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get MinutType by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMinutTypeById(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id < 1) {
                throw new ResourceNotFoundException("Invalid minute type ID");
            }

            MinutTypeDTO minutType = minutTypeService.getMinutTypeById(id);

            Map<String, Object> details = new HashMap<>();
            details.put("minutType", minutType);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Successfully retrieved minute type",
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
                    "Error retrieving minute type: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Create a new MinutType
    @PostMapping("/create")
    public ResponseEntity<Object> createMinutType(@RequestBody MinutTypeDTO minutTypeDTO) {
        try {
            // Validate request data
            minutTypeDTO.validateForCreate();

            // Create minute type
            MinutTypeDTO createdMinutType = minutTypeService.createMinutType(minutTypeDTO);

            Map<String, Object> details = new HashMap<>();
            details.put("minutType", createdMinutType);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "201",
                    "Minute type created successfully",
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
                    "Error creating minute type: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update an existing MinutType
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMinutType(@PathVariable Long id, @RequestBody MinutTypeDTO minutTypeDTO) {
        try {
            // Set ID from path parameter
            minutTypeDTO.setId(id);

            // Validate request data
            minutTypeDTO.validateForUpdate();

            // Update minute type
            MinutTypeDTO updatedMinutType = minutTypeService.updateMinutType(id, minutTypeDTO);

            Map<String, Object> details = new HashMap<>();
            details.put("minutType", updatedMinutType);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Minute type updated successfully",
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
                    "Error updating minute type: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Delete a MinutType by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMinutType(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id < 1) {
                throw new ResourceNotFoundException("Invalid minute type ID");
            }

            // Delete minute type
//            minutTypeService.deleteMinutType(id);

            Map<String, Object> details = new HashMap<>();

            ResponseDTO response = new ResponseDTO(
                    "success",
                    "200",
                    "Minute type deleted successfully",
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
                    "Error deleting minute type: " + e.getMessage(),
                    details,
                    new HashMap<>()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}