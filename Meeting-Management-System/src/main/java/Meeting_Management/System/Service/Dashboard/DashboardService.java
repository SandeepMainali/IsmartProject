package Meeting_Management.System.Service.Dashboard;

import Meeting_Management.System.Dto.DashBoard.DashBoardDTO;
import Meeting_Management.System.Entity.*;
import Meeting_Management.System.Repository.*;
import Meeting_Management.System.Service.Impl.IDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService implements IDashBoardService{

    @Autowired
    private CommiteeRepo commiteeRepository;

    @Autowired
    private BranchInfoRepo branchInfoRepository;

    @Autowired
    private CommiteeTypeRepo commiteeTypeRepository;

    @Autowired
    private MemberRepo memberRepository;

    @Autowired
    private MemTypeRepo memTypeRepository;

    @Autowired
    private MeetMinutRepo meetMinutRepository;

    @Autowired
    private MinutTypeRepo minutTypeRepository;

    @Autowired
    private MeetAttendanceRepo meetAttendanceRepository;

    @Autowired
    private DepartRepo departRepository;

    @Autowired
    private OfficeRepo officeRepository;

    @Autowired
    private EthnicCategoryRepo ethnicCategoryRepository;

    @Override
    public DashBoardDTO getDashboardData() {
        DashBoardDTO dashboardDTO = new DashBoardDTO();

        // Set total counts
        dashboardDTO.setTotalCommittees(commiteeRepository.count());
        dashboardDTO.setTotalMembers(memberRepository.count());
        dashboardDTO.setTotalMeetings(meetMinutRepository.count());
        dashboardDTO.setTotalDepartments(departRepository.count());
        dashboardDTO.setTotalOffices(officeRepository.count());
        dashboardDTO.setActiveBranches(branchInfoRepository.countByStatusTrue());
        dashboardDTO.setActiveMembers(memberRepository.countByStatusTrue());

        // Set distribution data
        dashboardDTO.setEthnicityCount(getEthnicityCount());
        dashboardDTO.setMeetingsByType(getMeetingsByType());
        dashboardDTO.setCommitteesByType(getCommitteesByType());
        dashboardDTO.setMembersByType(getMembersByType());
        dashboardDTO.setRecentMeetings(getRecentMeetings());
        dashboardDTO.setAttendanceStats(getAttendanceRateStats());
        dashboardDTO.setMeetingsByMonth(getMeetingsByMonth());

        return dashboardDTO;
    }

    @Override
    public DashBoardDTO getMeetingStatsByPeriod(String period) {
        DashBoardDTO dashboardDTO = new DashBoardDTO();
        LocalDate startDate;
        LocalDate currentDate = LocalDate.now();

        // Define the period
        startDate = switch (period.toLowerCase()) {
            case "week" -> currentDate.minusWeeks(1);
            case "month" -> currentDate.minusMonths(1);
            case "year" -> currentDate.minusYears(1);
            default -> currentDate.minusMonths(1); // Default to month
        };

        // Get meetings in the period
        List<MeetMinut> meetings = meetMinutRepository.findByMeetDateBetween(startDate, currentDate);

        dashboardDTO.setTotalMeetings((long) meetings.size());

        // Get meetings by type in this period
        Map<String, Long> meetingsByType = meetings.stream()
                .collect(Collectors.groupingBy(
                        m -> m.getMintType().getMinuteName(),
                        Collectors.counting()
                ));

        dashboardDTO.setMeetingsByType(meetingsByType);

        // Get attendance stats for this period
        Map<String, Long> attendanceStats = new HashMap<>();
        long totalAttendees = 0;
        long totalExpectedAttendees = 0;

        for (MeetMinut meeting : meetings) {
            List<MeetAttendance> attendances = meetAttendanceRepository.findByMeetMinut(meeting);

            long presentCount = attendances.stream()
                    .filter(MeetAttendance::getIsAttendant)
                    .count();

            totalAttendees += presentCount;
            totalExpectedAttendees += attendances.size();
        }

        double attendanceRate = totalExpectedAttendees > 0
                ? (double) totalAttendees / totalExpectedAttendees * 100
                : 0;

        attendanceStats.put("attendanceRate", Math.round(attendanceRate));
        attendanceStats.put("totalAttendees", totalAttendees);
        attendanceStats.put("totalExpectedAttendees", totalExpectedAttendees);

        dashboardDTO.setAttendanceStats(attendanceStats);

        // Recent meetings
        dashboardDTO.setRecentMeetings(meetings.stream()
                .sorted(Comparator.comparing(MeetMinut::getMeetDate).reversed())
                .limit(5)
                .map(this::convertMeetingToMap)
                .collect(Collectors.toList()));

        return dashboardDTO;
    }

    @Override
    public DashBoardDTO getCommitteeStats() {
        DashBoardDTO dashboardDTO = new DashBoardDTO();

        dashboardDTO.setTotalCommittees(commiteeRepository.count());
        dashboardDTO.setCommitteesByType(getCommitteesByType());

        return dashboardDTO;
    }

    @Override
    public DashBoardDTO getMemberStats() {
        DashBoardDTO dashboardDTO = new DashBoardDTO();

        dashboardDTO.setTotalMembers(memberRepository.count());
        dashboardDTO.setMembersByType(getMembersByType());

        // Additional member statistics could be added here
        // Such as distribution by gender, departments, etc.

        return dashboardDTO;
    }

    @Override
    public DashBoardDTO getAttendanceStats() {
        DashBoardDTO dashboardDTO = new DashBoardDTO();

        dashboardDTO.setAttendanceStats(getAttendanceRateStats());

        // Get attendance trends over time
        // This would require more complex queries

        return dashboardDTO;
    }

    // Helper methods
    private Map<String, Long> getEthnicityCount() {
        List<EthnicCategory> ethnicCategories = ethnicCategoryRepository.findAll();
        Map<String, Long> result = new HashMap<>();
        for (EthnicCategory category : ethnicCategories) {
            long count = memberRepository.countByEthinicity(category);
            result.put(category.getEthnicName(), count);
        }
        return result;
    }

    private Map<String, Long> getMeetingsByType() {
        List<MinutType> minutTypes = minutTypeRepository.findAll();
        Map<String, Long> result = new HashMap<>();

        for (MinutType type : minutTypes) {
            long count = meetMinutRepository.countByMintType(type);
            result.put(type.getMinuteName(), count);
        }

        return result;
    }

    private Map<String, Long> getCommitteesByType() {
        List<CommiteeType> commiteeTypes = commiteeTypeRepository.findAll();
        Map<String, Long> result = new HashMap<>();

        for (CommiteeType type : commiteeTypes) {
            long count = commiteeRepository.countByCommiteeType(type);
            result.put(type.getCommitteeTypeName(), count);
        }

        return result;
    }

    private Map<String, Long> getMembersByType() {
        List<MemType> memTypes = memTypeRepository.findAll();
        Map<String, Long> result = new HashMap<>();

        for (MemType type : memTypes) {
            long count = memberRepository.countByMemType(type);
            result.put(type.getMemTypeName(), count);
        }

        return result;
    }

    private List<Map<String, Object>> getRecentMeetings() {
        List<MeetMinut> recentMeetings = meetMinutRepository.findTop5ByOrderByMeetDateDesc();

        return recentMeetings.stream()
                .map(this::convertMeetingToMap)
                .collect(Collectors.toList());
    }

    private Map<String, Object> convertMeetingToMap(MeetMinut meetMinut) {
        Map<String, Object> meetingMap = new HashMap<>();
        meetingMap.put("id", meetMinut.getId());
        meetingMap.put("type", meetMinut.getMintType().getMinuteName());
        meetingMap.put("date", meetMinut.getMeetDate().toString());
        meetingMap.put("timeFrom", meetMinut.getTimeFrom().toString());
        meetingMap.put("timeTo", meetMinut.getTimeTo().toString());
        meetingMap.put("place", meetMinut.getMeetPlace());
        meetingMap.put("chairPerson", meetMinut.getChairPerson().getFullName());

        // Calculate attendance
        List<MeetAttendance> attendances = meetAttendanceRepository.findByMeetMinut(meetMinut);
        long presentCount = attendances.stream()
                .filter(MeetAttendance::getIsAttendant)
                .count();

        meetingMap.put("attendanceCount", presentCount);
        meetingMap.put("totalMembers", attendances.size());

        return meetingMap;
    }

    private Map<String, Long> getAttendanceRateStats() {
        Map<String, Long> stats = new HashMap<>();
        long totalAttendees = 0;
        long totalExpectedAttendees = 0;

        List<MeetMinut> allMeetings = meetMinutRepository.findAll();

        for (MeetMinut meeting : allMeetings) {
            List<MeetAttendance> attendances = meetAttendanceRepository.findByMeetMinut(meeting);

            long presentCount = attendances.stream()
                    .filter(MeetAttendance::getIsAttendant)
                    .count();

            totalAttendees += presentCount;
            totalExpectedAttendees += attendances.size();
        }

        double attendanceRate = totalExpectedAttendees > 0
                ? (double) totalAttendees / totalExpectedAttendees * 100
                : 0;

        stats.put("attendanceRate", Math.round(attendanceRate));
        stats.put("totalAttendees", totalAttendees);
        stats.put("totalExpectedAttendees", totalExpectedAttendees);

        return stats;
    }

    private Map<String, Long> getMeetingsByMonth() {
        Map<String, Long> meetingsByMonth = new HashMap<>();
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        List<MeetMinut> meetings = meetMinutRepository.findByMeetDateAfter(sixMonthsAgo);

        for (int i = 0; i < 6; i++) {
            LocalDate monthDate = LocalDate.now().minusMonths(i);
            String monthName = monthDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            int year = monthDate.getYear();
            String key = monthName + " " + year;

            int month = monthDate.getMonthValue();

            long count = meetings.stream()
                    .filter(m -> {
                        LocalDate meetDate = m.getMeetDate();
                        return meetDate.getMonthValue() == month && meetDate.getYear() == year;
                    })
                    .count();

            meetingsByMonth.put(key, count);
        }

        return meetingsByMonth;
    }
}
