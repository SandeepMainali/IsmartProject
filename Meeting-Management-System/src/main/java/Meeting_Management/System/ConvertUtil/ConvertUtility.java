package Meeting_Management.System.ConvertUtil;

import Meeting_Management.System.Dto.RolesDTO;
import Meeting_Management.System.Dto.UserDTO;
import Meeting_Management.System.Entity.Role;
import Meeting_Management.System.Entity.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConvertUtility {

    public static UserDTO ConvertUtilityUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setBranchId(user.getBranchInfo().getId());
        userDTO.setCounterId(user.getCounterId());
        userDTO.setRoleId(user.getRole().getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPasswordSalt(user.getPasswordSalt());
        userDTO.setPasswordHash(user.getPasswordHash());
        userDTO.setFullName(user.getFullName());
        userDTO.setFullNameLocale(user.getFullNameLocale());
        userDTO.setAddress(user.getAddress());
        userDTO.setGenderId(user.getGender().getId());
        userDTO.setPrimaryContact(user.getPContact());
        userDTO.setOtherContact(user.getOtherContact());
        userDTO.setEmail(user.getEmail());
        userDTO.setStatus(user.getStatus());
        userDTO.setDeactiveDate(user.getDeactiveDate());
        userDTO.setRemarks(user.getRemarks());
        userDTO.setInsertUser(user.getInsertUser());
        userDTO.setInsertDate(user.getInsertDate());
        userDTO.setEditUser(user.getEditUser());
        userDTO.setEditDate(user.getEditDate());
        return userDTO;
    }

    public static List<UserDTO> toUserDtoList(List<User> usersList) {
        return usersList.stream()
                .map(ConvertUtility::ConvertUtilityUser)
                .collect(Collectors.toList());
    }

    public static RolesDTO ConvertUtilityRoles(Role role) {
        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setId(role.getId());
        rolesDTO.setRoleAlias(role.getRoleAlias());
        rolesDTO.setRoleName(role.getRoleName());
        rolesDTO.setRemarks(role.getRemarks());
        rolesDTO.setInsertUser(role.getInsertUser());
        rolesDTO.setInsertDate(role.getInsertDate());
        rolesDTO.setEditUser(role.getEditUser());
        rolesDTO.setEditDate(role.getEditDate());
        return rolesDTO;
    }
}
