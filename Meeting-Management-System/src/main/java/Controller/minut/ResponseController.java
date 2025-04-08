package Controller.minut;

import dto.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResponseController {

    @GetMapping("/api/response")
    public ResponseDTO getResponse() {
        // Create mock details to return in the response
        Map<String, Object> details = new HashMap<>();
        details.put("userId", 123);
        details.put("username", "Swaraj Maharjan");

        Map<String, Object> detail = new HashMap<>();
        detail.put("profile", "It is I yunero, yunero is me");

        // Create and return the ResponseDTO object
        return new ResponseDTO(
                "success",         // status
                "200",             // code
                "User created successfully", // message
                details,           // details
                detail             // detail
        );
    }
}
