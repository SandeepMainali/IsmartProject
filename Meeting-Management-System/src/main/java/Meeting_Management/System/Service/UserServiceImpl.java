package Meeting_Management.System.Service;

import Meeting_Management.System.ConvertUtil.ConvertUtility;
import Meeting_Management.System.ConvertUtil.PasswordUtil;
import Meeting_Management.System.Dto.LoginDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Dto.UserDTO;
import Meeting_Management.System.Entity.BranchInfo;
import Meeting_Management.System.Entity.Gender;
import Meeting_Management.System.Entity.Role;
import Meeting_Management.System.Entity.User;
import Meeting_Management.System.Filter.JWTFilter;
import Meeting_Management.System.Repository.BranchInfoRepo;
import Meeting_Management.System.Repository.GenderRepo;
import Meeting_Management.System.Repository.RolesRepo;
import Meeting_Management.System.Repository.UserRepo;
import Meeting_Management.System.Service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.util.*;

import static Meeting_Management.System.ConvertUtil.PasswordUtil.generateSalt;
import static Meeting_Management.System.ConvertUtil.PasswordUtil.hashPassword;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RolesRepo rolesRepo;
    private final BranchInfoRepo branchInfoRepo;
    private final GenderRepo genderRepo;
    private final JWTService jwtService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RolesRepo rolesRepo,
                           BranchInfoRepo branchInfoRepo, GenderRepo genderRepo, JWTService jwtService) {
        this.userRepo = userRepo;
        this.rolesRepo = rolesRepo;
        this.branchInfoRepo = branchInfoRepo;
        this.genderRepo = genderRepo;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseDTO getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = ConvertUtility.toUserDtoList(users);

        if (!userDTOs.isEmpty()) {
            Map<String, Object> details = new HashMap<>();
            details.put("users", userDTOs);

            return new ResponseDTO(
                    "Success",
                    "M0000",
                    "Users retrieved successfully",
                    details,
                    null
            );
        }else {
            return new ResponseDTO(
                    "Error",
                    "E0000",
                    "No users found",
                    null,
                    null
            );
        }
    }

    @Override
    public ResponseDTO getUserById(Integer id) {
        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            return new ResponseDTO("Error", "E0000", "User not found", null, null);
        }else {
            UserDTO userDTO = ConvertUtility.ConvertUtilityUser(user);
            Map<String, Object> details = new HashMap<>();
            details.put("user", userDTO);
            return new ResponseDTO("Success", "M0000", "User found", details, null);
        }
    }

    @Override
    public ResponseDTO createUser(UserDTO userDTO) {
        User user = new User();

        Optional<BranchInfo> branchInfo = branchInfoRepo.findById(userDTO.getBranchId());
        Optional<Role> role = rolesRepo.findById(userDTO.getRoleId());
        Optional<Gender> gender = genderRepo.findById(userDTO.getGenderId());

        String createdBy = getCurrentUsername();

        try{
        if (branchInfo.isEmpty()) {
            return new ResponseDTO("Error", "E0000", "Branch ID Is Invalid", null, null);
        }

        if(role.isEmpty()){
            return new ResponseDTO("Error", "E0000", "Role ID Is Invalid", null, null);
        }

        if (gender.isEmpty()) {
            return new ResponseDTO("Error", "E0000", "Gender Is Invalid", null, null);
        }

        user.setBranchInfo(branchInfo.get());
        user.setRole(role.get());
        user.setGender(gender.get());

        user.setCounterId(userDTO.getCounterId());
        user.setUserName(userDTO.getUserName());
        user.setFullName(userDTO.getFullName());
        user.setFullNameLocale(userDTO.getFullNameLocale());
        user.setAddress(userDTO.getAddress());
        user.setPContact(userDTO.getPrimaryContact());
        user.setOtherContact(userDTO.getOtherContact());
        user.setEmail(userDTO.getEmail());
        user.setStatus(true);
        user.setRemarks(userDTO.getRemarks());

        if (userDTO.getPasswordHash() != null && !userDTO.getPasswordHash().isEmpty()) {
            try {
                String salt = generateSalt();
                String hashedPassword = hashPassword(userDTO.getPasswordHash(), salt);

                user.setPasswordSalt(salt);
                user.setPasswordHash(hashedPassword);
            }catch (Exception e){
                throw new RuntimeException("Failed to hash password", e);
            }
        }

        user.setInsertUser(createdBy);
        user.setInsertDate(ZonedDateTime.now());

            User savedUser = userRepo.save(user);
            UserDTO saveUser = ConvertUtility.ConvertUtilityUser(savedUser);
            Map<String, Object> detail = new HashMap<>();
            detail.put("user", saveUser);
            return new ResponseDTO("Success", "M0000", "User created successfully", null, detail);
        }catch (Exception e){
            return new ResponseDTO("Error", "E0000", "Failed to create user", null, null);
        }
    }

    @Override
    public ResponseDTO updateUser(UserDTO userDTO) {
        Optional<User> existingUserOpt = userRepo.findById(userDTO.getId());
        String editedBy = getCurrentUsername();

        if (existingUserOpt.isEmpty()) {
            return null;
        }

        if (userDTO.getUserName() != null && userRepo.existsByUserNameAndIdNot(userDTO.getUserName(), userDTO.getId())) {
            return new ResponseDTO("Error", "E0001", "Username already taken by another user", null, null);
        }

        if (userDTO.getEmail() != null && userRepo.existsByEmailAndIdNot(userDTO.getEmail(), userDTO.getId())) {
            return new ResponseDTO("Error", "E0002", "Email already taken by another user", null, null);
        }

        User existingUser = existingUserOpt.get();

            if (userDTO.getBranchId() != null) {
                Optional<BranchInfo> branchInfo = branchInfoRepo.findById(userDTO.getBranchId());
                branchInfo.ifPresent(existingUser::setBranchInfo);
            }

            if (userDTO.getRoleId() != null) {
                Optional<Role> role = rolesRepo.findById(userDTO.getRoleId());
                role.ifPresent(existingUser::setRole);
            }

            if (userDTO.getGenderId() != null) {
                Optional<Gender> gender = genderRepo.findById(userDTO.getGenderId());
                gender.ifPresent(existingUser::setGender);
            }

            if (userDTO.getCounterId() != null) {
                existingUser.setCounterId(userDTO.getCounterId());
            }

            if (userDTO.getUserName() != null) {
                existingUser.setUserName(userDTO.getUserName());
            }

            if (userDTO.getFullName() != null) {
                existingUser.setFullName(userDTO.getFullName());
            }

            if (userDTO.getFullNameLocale() != null) {
                existingUser.setFullNameLocale(userDTO.getFullNameLocale());
            }

            if (userDTO.getAddress() != null) {
                existingUser.setAddress(userDTO.getAddress());
            }

            if (userDTO.getPrimaryContact() != null) {
                existingUser.setPContact(userDTO.getPrimaryContact());
            }

            if (userDTO.getOtherContact() != null) {
                existingUser.setOtherContact(userDTO.getOtherContact());
            }

            if (userDTO.getEmail() != null) {
                existingUser.setEmail(userDTO.getEmail());
            }

            if (userDTO.getRemarks() != null) {
                existingUser.setRemarks(userDTO.getRemarks());
            }

            existingUser.setEditUser(editedBy);
            existingUser.setEditDate(ZonedDateTime.now());

            User updatedUser = userRepo.save(existingUser);
            return new ResponseDTO("Success", "M0000", "User updated successfully", null, null);
    }

    @Override
    public ResponseDTO deleteUser(Integer id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return new ResponseDTO("Success", "M0000", "User deleted successfully", null, null);
        }else {
            return new ResponseDTO("Error", "E0000", "User does not exist", null, null);
        }
    }

    @Override
    public ResponseDTO changePassword(Integer id, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        String oldPasswordHash = hashPassword(oldPassword, user.getPasswordSalt());
        if (!oldPasswordHash.equals(user.getPasswordHash())) {
            return new ResponseDTO("Error", "E0001", "Incorrect old password", null, null);
        }

        String newPasswordHashWithOldSalt = hashPassword(newPassword, user.getPasswordSalt());
        if (newPasswordHashWithOldSalt.equals(user.getPasswordHash())) {
            return new ResponseDTO("Error", "E0002", "New password cannot be the same as the old password", null, null);
        }

        String salt = generateSalt();
        String hashedPassword = hashPassword(newPassword, salt);

        user.setPasswordSalt(salt);
        user.setPasswordHash(hashedPassword);
        user.setEditDate(ZonedDateTime.now());

        User updatedUser = userRepo.save(user);
        return new ResponseDTO("Success", "M0000", "Password Changed Successfully", null, null);
    }

    @Override
    public ResponseDTO setUserStatus(Integer id, boolean status) {
        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isEmpty()) {
            return null;
        }

        try {
            User user = userOptional.get();
            user.setStatus(status);

            if (!status) {
                user.setDeactiveDate(ZonedDateTime.now());
            } else {
                user.setDeactiveDate(null);
            }

            user.setEditDate(ZonedDateTime.now());

            User updatedUser = userRepo.save(user);
            UserDTO userDTO = ConvertUtility.ConvertUtilityUser(updatedUser);
            return new ResponseDTO("Success", "M0000", "Status updated successfully", null, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user status", e);
        }
    }

    public ResponseDTO verifyUser(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepo.findByUserName(loginDTO.getUserName());

        if (userOptional.isEmpty()) {
            return new ResponseDTO("M0001", "M0001", "User not found", null, null);
        }

        User user = userOptional.get();

        boolean isPasswordValid = PasswordUtil.verifyPassword(
                loginDTO.getPassword(),
                user.getPasswordHash(),
                user.getPasswordSalt()
        );

        if (!isPasswordValid) {
            return new ResponseDTO("E0000", "E0000", "Password Incorrect", null, null);
        }

        String token = jwtService.generateToken(user.getUserName());

        Map<String, Object> detail = new HashMap<>();
        detail.put("token", token);

        return new ResponseDTO("M0000", "M0000", "Login Successfully", detail, null);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "system";
    }
}
