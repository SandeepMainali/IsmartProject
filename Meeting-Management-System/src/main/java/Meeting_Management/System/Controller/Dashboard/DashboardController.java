package Meeting_Management.System.Controller.Dashboard;

import Meeting_Management.System.Dto.DashBoard.DashBoardDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.IDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private IDashBoardService dashboardService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getDashboardData() {
        try {
            DashBoardDTO dashboardDTO = dashboardService.getDashboardData();
            Map<String, Object> details = new HashMap<>();
            details.put("dashboard", dashboardDTO);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    HttpStatus.OK.toString(),
                    "Dashboard data retrieved successfully",
                    details,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "Error retrieving dashboard data: " + e.getMessage(),
                    null,
                    null
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/meetings/{period}")
    public ResponseEntity<ResponseDTO> getMeetingStats(@PathVariable String period) {
        try {
            DashBoardDTO dashboardDTO = dashboardService.getMeetingStatsByPeriod(period);
            Map<String, Object> details = new HashMap<>();
            details.put("meetingStats", dashboardDTO);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    HttpStatus.OK.toString(),
                    "Meeting statistics retrieved successfully",
                    details,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "Error retrieving meeting statistics: " + e.getMessage(),
                    null,
                    null
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/committees")
    public ResponseEntity<ResponseDTO> getCommitteeStats() {
        try {
            DashBoardDTO dashboardDTO = dashboardService.getCommitteeStats();
            Map<String, Object> details = new HashMap<>();
            details.put("committeeStats", dashboardDTO);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    HttpStatus.OK.toString(),
                    "Committee statistics retrieved successfully",
                    details,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "Error retrieving committee statistics: " + e.getMessage(),
                    null,
                    null
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/members")
    public ResponseEntity<ResponseDTO> getMemberStats() {
        try {
            DashBoardDTO dashboardDTO = dashboardService.getMemberStats();
            Map<String, Object> details = new HashMap<>();
            details.put("memberStats", dashboardDTO);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    HttpStatus.OK.toString(),
                    "Member statistics retrieved successfully",
                    details,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "Error retrieving member statistics: " + e.getMessage(),
                    null,
                    null
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/attendance")
    public ResponseEntity<ResponseDTO> getAttendanceStats() {
        try {
            DashBoardDTO dashboardDTO = dashboardService.getAttendanceStats();
            Map<String, Object> details = new HashMap<>();
            details.put("attendanceStats", dashboardDTO);

            ResponseDTO response = new ResponseDTO(
                    "success",
                    HttpStatus.OK.toString(),
                    "Attendance statistics retrieved successfully",
                    details,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "Error retrieving attendance statistics: " + e.getMessage(),
                    null,
                    null
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
