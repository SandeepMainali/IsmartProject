package dto.minut;

import lombok.Data;
import exception.ResourceNotFoundException;
import java.time.ZonedDateTime;

@Data
public class MeetAttendanceDTO {

    private Long id;
    private Long meetId;
    private Long mintMemberId;
    private Boolean isAttendant;
    private Boolean status;
    private String remarks;
    private Long insertUserId;
    private ZonedDateTime insertDate;
    private Long editUserId;
    private ZonedDateTime editDate;

    /**
     * Validates this DTO for create operations
     * @throws ResourceNotFoundException if validation fails
     */
    public void validateForCreate() {
        if (meetId == null || meetId < 1) {
            throw new ResourceNotFoundException("Meeting ID must be a positive number");
        }
        if (mintMemberId == null || mintMemberId < 1) {
            throw new ResourceNotFoundException("Member ID must be a positive number");
        }
        if (isAttendant == null) {
            throw new ResourceNotFoundException("Attendance status cannot be null");
        }
        if (insertUserId == null || insertUserId < 1) {
            throw new ResourceNotFoundException("Insert User ID must be a positive number");
        }
    }

    /**
     * Validates this DTO for update operations
     * @throws ResourceNotFoundException if validation fails
     */
    public void validateForUpdate() {
        if (id == null || id < 1) {
            throw new ResourceNotFoundException("ID must be a positive number");
        }
        if (meetId != null && meetId < 1) {
            throw new ResourceNotFoundException("Meeting ID must be a positive number");
        }
        if (mintMemberId != null && mintMemberId < 1) {
            throw new ResourceNotFoundException("Member ID must be a positive number");
        }
        if (editUserId == null || editUserId < 1) {
            throw new ResourceNotFoundException("Edit User ID must be a positive number");
        }
    }
}