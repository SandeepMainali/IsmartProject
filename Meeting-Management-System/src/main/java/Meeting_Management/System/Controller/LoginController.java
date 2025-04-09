package Meeting_Management.System.Controller;

import Meeting_Management.System.Dto.LoginDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Dto.UserDTO;
import Meeting_Management.System.Entity.User;
import Meeting_Management.System.Service.Impl.UserService;
import Meeting_Management.System.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            ResponseDTO responseDTO = userServiceImpl.verifyUser(loginDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO responseDTO = new ResponseDTO("Error", "E5000", "Server Error. Please Try Again", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
