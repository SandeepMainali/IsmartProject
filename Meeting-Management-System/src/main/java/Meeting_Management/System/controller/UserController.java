package Meeting_Management.System.controller;

import Meeting_Management.System.DTO.UserDTO;
import Meeting_Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO createdUser = userService.createUser(userDTO, auth.getName());
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Integer userId,
            @RequestBody UserDTO userDTO
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userDTO.setId(userId);
        UserDTO updatedUser = userService.updateUser(userDTO, auth.getName());
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(userId, auth.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<UserDTO> users = userService.getAllUsers(auth.getName());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO user = userService.getUserById(userId, auth.getName());
        return ResponseEntity.ok(user);
    }
}
