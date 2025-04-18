package Meeting_Management.System.Dto.DashBoard;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DashBoardDTO {
    private Long totalCommittees;
    private Long totalMembers;
    private Long totalMeetings;
    private Long totalDepartments;
    private Long totalOffices;
    private Long ActiveMembers;
    private Long ActiveBranches;
    private Map<String, Long> EthnicityCount;
    private Map<String, Long> meetingsByType;
    private Map<String, Long> committeesByType;
    private Map<String, Long> membersByType;
    private List<Map<String, Object>> recentMeetings;
    private Map<String, Long> attendanceStats;
    private Map<String, Long> meetingsByMonth;
}
