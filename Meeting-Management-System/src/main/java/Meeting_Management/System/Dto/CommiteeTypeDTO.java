package Meeting_Management.System.Dto;

import Meeting_Management.System.Entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CommiteeTypeDTO {
    @NotNull(message = "Commitee Type name cannot be null.")
    private String commiteeTypeName;
    @NotNull(message = "Commitee Type Locale cannot be null.")
    private String commiteeTypeLocale;

    private String description;

    private Boolean status = true;

    private String remarks;



}
