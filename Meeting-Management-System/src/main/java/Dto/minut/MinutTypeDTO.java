package dto.minut;

import lombok.Data;
import exception.ResourceNotFoundException;

import java.time.ZonedDateTime;

@Data
public class MinutTypeDTO {

    private Long id;
    private String minuteName;
    private String mituteTypeLocale;
    private String description;
    private Boolean status;
    private String remarks;
    private String insertUser; // storing username
    private ZonedDateTime insertDate;
    private String editUser;   // storing username
    private ZonedDateTime editDate;

    public void validateForCreate() {
        if (minuteName == null || minuteName.trim().isEmpty()) {
            throw new ResourceNotFoundException("Minute name cannot be empty");
        }
    }

    public void validateForUpdate() {
        if (id == null || id < 1) {
            throw new ResourceNotFoundException("ID must be a positive number");
        }

        if (minuteName != null && minuteName.trim().isEmpty()) {
            throw new ResourceNotFoundException("Minute name cannot be empty if provided");
        }
    }
}
