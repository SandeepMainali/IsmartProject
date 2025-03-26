package Meeting_Management.System.interfaces;

import Meeting_Management.System.DTO.UserDTO;

import java.util.List;

public interface User {

    UserDTO createUser(UserDTO userDTO, String currentUsername);

    UserDTO updateUser(UserDTO userDTO, String currentUsername);

    void deleteUser(Integer userId, String currentUsername);

    List<UserDTO> getAllUsers(String currentUsername);

    UserDTO getUserById(Integer userId, String currentUsername);

}
