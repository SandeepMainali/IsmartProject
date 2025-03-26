package Meeting_Management.System.service;

import Meeting_Management.System.DTO.UserDTO;
import Meeting_Management.System.entity.Users;
import Meeting_Management.System.enums.RoleTypes;
import Meeting_Management.System.interfaces.User;
import Meeting_Management.System.repository.RolesRepository;
import Meeting_Management.System.repository.UserRepository;
import Meeting_Management.System.utils.ConvertUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements User {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO, String currentUsername) {
        // Find current user to check permissions
        Users currentUser = userRepository.findByUserName(currentUsername)
                .orElseThrow(() -> new AccessDeniedException("Current user not found"));

        // Only Super Admin can create any user, Admin can only create USER role
        if (currentUser.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
            return createUserByAdmin(userDTO, currentUser);
        } else if (currentUser.getRole().getRoleAlias().equals(RoleTypes.ADMIN.getCode())) {
            // Ensure only USER role can be created by Admin
            if (!userDTO.getRole().getRoleAlias().equals(RoleTypes.USER.getCode())) {
                throw new AccessDeniedException("Admin can only create USER role");
            }
            return createUserByAdmin(userDTO, currentUser);
        } else {
            throw new AccessDeniedException("You are not authorized to create users");
        }
    }

    private UserDTO createUserByAdmin(UserDTO userDTO, Users currentUser) {
        Users newUser = new Users();

        // Copy fields from DTO
        newUser.setBranch(userDTO.getBranch());
        newUser.setCounterId(userDTO.getCounterId());
        newUser.setRole(userDTO.getRole());
        newUser.setUserName(userDTO.getUserName());
        newUser.setFullName(userDTO.getFullName());
        newUser.setFullNameLocale(userDTO.getFullNameLocale());
        newUser.setAddress(userDTO.getAddress());
        newUser.setGender(userDTO.getGender());
        newUser.setPrimaryContact(userDTO.getPrimaryContact());
        newUser.setOtherContact(userDTO.getOtherContact());
        newUser.setEmail(userDTO.getEmail());

        // Set default status
        newUser.setStatus(userDTO.getStatus() != null ? userDTO.getStatus() : true);

        // Set password (in a real-world scenario, implement secure password generation)
        String rawPassword = "Temp1234!";
        newUser.setPasswordSalt("SAMPLE_SALT"); // Replace with proper salt generation
        newUser.setPasswordHash(passwordEncoder.encode(rawPassword));

        // Set audit fields
        newUser.setInsertUser(currentUser.getId());
        newUser.setInsertDate(ZonedDateTime.now());

        // Save and convert back to DTO
        Users savedUser = userRepository.save(newUser);
        return ConvertUtility.ConvertUtilityUser(savedUser);
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO, String currentUsername) {
        // Find current user to check permissions
        Users currentUser = userRepository.findByUserName(currentUsername)
                .orElseThrow(() -> new AccessDeniedException("Current user not found"));

        // Find user to update
        Users userToUpdate = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Permission checks
        if (currentUser.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
            // Super Admin can update any user
            updateUserDetails(userToUpdate, userDTO, currentUser);
        } else if (currentUser.getRole().getRoleAlias().equals(RoleTypes.ADMIN.getCode())) {
            // Admin can only update non-Super Admin users
            if (userToUpdate.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
                throw new AccessDeniedException("Not authorized to update this user");
            }
            updateUserDetails(userToUpdate, userDTO, currentUser);
        } else {
            // Regular user can only update themselves
            if (!currentUser.getId().equals(userToUpdate.getId())) {
                throw new AccessDeniedException("Not authorized to update this user");
            }
            updateUserDetails(userToUpdate, userDTO, currentUser);
        }

        // Save and return updated user
        Users updatedUser = userRepository.save(userToUpdate);
        return ConvertUtility.ConvertUtilityUser(updatedUser);
    }

    private void updateUserDetails(Users userToUpdate, UserDTO userDTO, Users currentUser) {
        // Update fields (add more as needed)
        userToUpdate.setFullName(userDTO.getFullName());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setPrimaryContact(userDTO.getPrimaryContact());

        // Update audit fields
        userToUpdate.setEditUser(currentUser.getId());
        userToUpdate.setEditDate(ZonedDateTime.now());
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId, String currentUsername) {
        // Find current user to check permissions
        Users currentUser = userRepository.findByUserName(currentUsername)
                .orElseThrow(() -> new AccessDeniedException("Current user not found"));

        // Only Super Admin can delete users
        if (!currentUser.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
            throw new AccessDeniedException("Only Super Admin can delete users");
        }

        // Find user to delete
        Users userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Prevent deleting Super Admin
        if (userToDelete.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
            throw new AccessDeniedException("Cannot delete Super Admin user");
        }

        userRepository.delete(userToDelete);
    }

    @Override
    public List<UserDTO> getAllUsers(String currentUsername) {
        // Find current user to check permissions
        Users currentUser = userRepository.findByUserName(currentUsername)
                .orElseThrow(() -> new AccessDeniedException("Current user not found"));

        List<Users> users;
        // Super Admin sees all users
        if (currentUser.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
            users = userRepository.findAll();
        }
        // Admin sees non-Super Admin users
        else if (currentUser.getRole().getRoleAlias().equals(RoleTypes.ADMIN.getCode())) {
            users = userRepository.findAll().stream()
                    .filter(u -> !u.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode()))
                    .collect(Collectors.toList());
        }
        // Regular users can only see themselves
        else {
            users = List.of(currentUser);
        }

        // Convert to DTOs
        return ConvertUtility.ConvertUtilityUser(users);
    }

    @Override
    public UserDTO getUserById(Integer userId, String currentUsername) {
        // Find current user to check permissions
        Users currentUser = userRepository.findByUserName(currentUsername)
                .orElseThrow(() -> new AccessDeniedException("Current user not found"));

        // Find target user
        Users targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Permission checks
        if (currentUser.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
            return ConvertUtility.ConvertUtilityUser(targetUser);
        }

        if (currentUser.getRole().getRoleAlias().equals(RoleTypes.ADMIN.getCode())) {
            if (!targetUser.getRole().getRoleAlias().equals(RoleTypes.SUPER_ADMIN.getCode())) {
                return ConvertUtility.ConvertUtilityUser(targetUser);
            }
            throw new AccessDeniedException("Not authorized to view this user");
        }

        // Regular user can only view themselves
        if (currentUser.getId().equals(userId)) {
            return ConvertUtility.ConvertUtilityUser(currentUser);
        }

        throw new AccessDeniedException("Not authorized to view this user");
    }
}
