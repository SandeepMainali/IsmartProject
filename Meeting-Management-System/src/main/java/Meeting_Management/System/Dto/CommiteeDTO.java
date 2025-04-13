package Meeting_Management.System.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;
import java.time.ZonedDateTime;
@Data
public class CommiteeDTO {
    @NotNull(message = "Commitee type name cannot be null")
    private String committeeTypeName;
    @NotNull(message = "Commitee name cannot be null")
    private String committeeName;
    @NotNull(message = "Commitee Locale name cannot be null")
    private String committeeLocale;
    private String nickName;
    private String nickNameLocale;
    private LocalDate estDate;
    private String description;
    private String remarks;
    private String estBody;
    @NotNull(message = "Department Name cannot be null.")
    private String departName;// User specifies departmentr
    @NotNull(message = "Office Name cannot be null.")
    private String officeName; // This is actually stored
//    private int insertUserId;
//    private ZonedDateTime insertDate;
//    private Long editUserId;
//    private ZonedDateTime editDate;
}


