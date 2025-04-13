package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Dto.UserDTO;


public interface UserService {

    ResponseDTO getAllUsers();

    ResponseDTO getUserById(Integer id);

    ResponseDTO createUser(UserDTO userDTO);

    ResponseDTO updateUser(UserDTO userDTO);

    ResponseDTO deleteUser(Integer id);

    ResponseDTO changePassword(Integer id, String oldPassword, String newPassword);

    ResponseDTO changePasswordByAdmin(Integer id, String newPassword);

    ResponseDTO setUserStatus(Integer id, boolean status);
}
