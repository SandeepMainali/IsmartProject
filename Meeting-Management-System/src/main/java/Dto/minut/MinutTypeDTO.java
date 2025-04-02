package dto.minut;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class MinutTypeDTO {

    private Long id;
    private String minuteName;
    private String mituteTypeLocale;
    private String description;
    private Boolean status;
    private String remarks;
    private String insertUser;
    private ZonedDateTime insertDate;
    private String editUser;
    private ZonedDateTime editDate;
}
