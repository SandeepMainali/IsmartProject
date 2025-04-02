package dto.minut;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Data
public class MeetMinutDTO {

    private Long id;
    private String mintType;
    private String finYear;
    private Integer meetCount;
    private LocalDate meetDate;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private String meetPlace;
    private String chairPerson;
    private String propositions;
    private String decisions;
    private Boolean status;
    private String remarks;
    private String insertUser;
    private ZonedDateTime insertDate;
    private String editUser;
    private ZonedDateTime editDate;
}
