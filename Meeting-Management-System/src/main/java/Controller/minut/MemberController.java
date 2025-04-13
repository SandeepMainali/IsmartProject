package Controller.minut;

import dto.minut.MemberDTO;
import Service.minut.MemberService;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<Object> createMember(@RequestBody MemberDTO dto) {
        try {
            // Basic validation
            if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Full name is required");
            }
            if (dto.getGenderId() == null) {
                return ResponseEntity.badRequest().body("Gender ID is required");
            }
            if (dto.getMemTypeId() == null) {
                return ResponseEntity.badRequest().body("Member Type ID is required");
            }

            MemberDTO createdMember = memberService.createMember(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create member: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllMembers() {
        try {
            List<MemberDTO> members = memberService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve members: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Valid member ID is required");
            }

            MemberDTO member = memberService.getMemberById(id);
            return ResponseEntity.ok(member);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve member: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        try {
            // Validate ID and basic fields
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Valid member ID is required");
            }
            if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Full name is required");
            }

            MemberDTO updatedMember = memberService.updateMember(id, dto);
            return ResponseEntity.ok(updatedMember);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update member: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Long id) {
        try {
            // Validate ID
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Valid member ID is required");
            }

            memberService.deleteMember(id);
            return ResponseEntity.ok("Member deleted successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete member: " + ex.getMessage());
        }
    }
}