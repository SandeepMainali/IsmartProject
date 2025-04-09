package Meeting_Management.System.Controller;

import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Dto.UserDTO;
import Meeting_Management.System.Repository.UserRepo;
import Meeting_Management.System.Service.Impl.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserRepo userRepo;

    @Autowired
    public UserController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {
            ResponseDTO responseDTO = userService.getAllUsers();
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO("Error", "E5000", "Server Error. Please Try Again", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Integer id) {
        try {
            ResponseDTO responseDTO = userService.getUserById(id);
            if (responseDTO != null) {
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO("Error", "E5000", "Server Error. Please Try Again", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            if (userRepo.existsByUserName(userDTO.getUserName())) {
                ResponseDTO responseDTO = new ResponseDTO("Error", "E0001", "Username already exists", null, null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }

            if (userRepo.existsByEmail(userDTO.getEmail())) {
                ResponseDTO responseDTO = new ResponseDTO("Error", "E0002", "Email already exists", null, null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
                ResponseDTO responseDTO = userService.createUser(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO("Error", "E5000", "Server Error, Please Try Again Later", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        try {
            userDTO.setId(id);
            ResponseDTO responseDTO = userService.updateUser(userDTO);
            if (responseDTO != null) {
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                ResponseDTO errorResponse = new ResponseDTO("Error", "E0000", "User not found", null, null);
                return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO("Error", "E5000", "Server Error, Please Try Again", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Integer id) {
        try {
            ResponseDTO responseDTO = userService.deleteUser(id);
            if (responseDTO != null) {
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO =  new ResponseDTO("Error", "E5000", "Server Error, Please Try Again", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<ResponseDTO> changePassword(@PathVariable Integer id, @RequestBody PasswordChangeRequest request) {
        try {
            ResponseDTO responseDTO = userService.changePassword(id, request.getOldPassword(), request.getNewPassword());
            if (responseDTO != null) {
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO("Error", "E5000", "Server Error, Please Try Again", null, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<ResponseDTO> activateUser(@PathVariable Integer id) {
        try {
            ResponseDTO responseDTO = userService.setUserStatus(id, true);
            if (responseDTO != null) {
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO("Error", "E5000", "Server Error, Please Try Again", null, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ResponseDTO> deactivateUser(@PathVariable Integer id) {
        try {
            ResponseDTO responseDTO = userService.setUserStatus(id, false);
            if (responseDTO != null) {
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO("Error", "E5000", "Server Error, Please Try Again", null, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Setter
    @Getter
    public static class PasswordChangeRequest {
        private String oldPassword;
        private String newPassword;
    }
}
