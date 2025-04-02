package dto.minut;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class MemberDTO {
    private Long id;
    private String fullName;
    private String fullNameLocale;
    private Long genderId;
    private Long memTypeId;
    private String mintDepartId;
    private String mintMemDegId;
    private Long ethnicityId;
    private Boolean isBackwardClass;
    private String email;
    private String mobNo;
    private String officeName;
    private String description;
    private Boolean status;
    private String remarks;
    private Long insertUserId;
    private ZonedDateTime insertDate;
    private Long editUserId;
    private ZonedDateTime editDate;
}
