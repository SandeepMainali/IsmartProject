package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Dto.DashBoard.DashBoardDTO;

public interface IDashBoardService {
    DashBoardDTO getDashboardData();

    DashBoardDTO getMeetingStatsByPeriod(String period);

    DashBoardDTO getCommitteeStats();

    DashBoardDTO getMemberStats();

    DashBoardDTO getAttendanceStats();
}
