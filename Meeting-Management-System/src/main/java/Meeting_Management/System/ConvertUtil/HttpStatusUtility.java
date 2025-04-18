package Meeting_Management.System.ConvertUtil;

import Meeting_Management.System.Dto.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class HttpStatusUtility {
    public static HttpStatus getHttpStatus(ResponseDTO response) {
        String status = response.getStatus();
        return switch (status.toLowerCase()) {
            case "success" -> HttpStatus.OK;
            case "created" -> HttpStatus.CREATED;
            case "not_found" -> HttpStatus.NOT_FOUND;
            case "bad_request" -> HttpStatus.BAD_REQUEST;
            case "unauthorized" -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
