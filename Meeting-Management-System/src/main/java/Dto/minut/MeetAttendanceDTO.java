package dto.minut;

import lombok.Data;
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
}
