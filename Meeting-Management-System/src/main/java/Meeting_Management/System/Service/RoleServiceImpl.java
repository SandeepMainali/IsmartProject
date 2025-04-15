package Meeting_Management.System.Service;

import Meeting_Management.System.ConvertUtil.ConvertUtility;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Dto.RolesDTO;
import Meeting_Management.System.Entity.Role;
import Meeting_Management.System.Repository.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import Meeting_Management.System.Service.Impl.RolesService;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class RoleServiceImpl implements RolesService {
    private final RolesRepo rolesRepo;

    @Autowired
    public RoleServiceImpl(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }

    @Override
    public ResponseDTO getAllRoles(){
        List<Role> roles = rolesRepo.findAll();
        List<RolesDTO> rolesDTOs = ConvertUtility.ConvertUtilityRolesList(roles);

        if (!rolesDTOs.isEmpty()){
            Map<String, Object> details = new HashMap<>();
            details.put("roles", rolesDTOs);

            return new ResponseDTO("Success","M0000","Roles List",details,null);
        }else {
            return new ResponseDTO("Success","M0000","No Roles Found",null,null);
        }
    }

    @Override
    public ResponseDTO getRoleById(Integer id) {
        Role role = rolesRepo.findById(id).orElse(null);
        if (role != null){
            Map<String, Object> details = new HashMap<>();
            details.put("role", role);
            return new ResponseDTO("Success","M0000","Role Details",details,null);
        }else {
            return new ResponseDTO("Success","M0000","No Role Found",null,null);
        }
    }

    @Override
    public ResponseDTO createRole(RolesDTO rolesDTO) {
        String currentUser = getCurrentUsername();
        if (rolesDTO !=null) {
            Role role = new Role();

            role.setRoleAlias(rolesDTO.getRoleAlias());
            role.setRoleName(rolesDTO.getRoleName());
            role.setRemarks(rolesDTO.getRemarks());
            role.setInsertUser(currentUser);
            role.setInsertDate(ZonedDateTime.now());
            rolesRepo.save(role);

            Map<String, Object> detail = new HashMap<>();
            detail.put("role", ConvertUtility.ConvertUtilityRoles(role));
            return new ResponseDTO("Success", "M0000", "Role Created", null, detail);
        }else {
            return new ResponseDTO("Success","M0000","Failed To Create Role",null,null);
        }
    }

    @Override
    public ResponseDTO updateRole(RolesDTO rolesDTO) {
        Optional<Role> role = rolesRepo.findById(rolesDTO.getId());
        String currentUser = getCurrentUsername();
        if (rolesDTO.getRoleAlias() != null && rolesRepo.existsByRoleAlias(rolesDTO.getRoleAlias())) {
            return new ResponseDTO("Error", "M0000", "RoleAlias Already Exists", null,null);
        }

        if(rolesDTO.getRoleName() != null && rolesRepo.existsByRoleName(rolesDTO.getRoleName())) {
            return new ResponseDTO("Error", "M0000", "RoleName Already Exists", null,null);
        }

        if (role.isPresent()) {
            Role existingRole = role.get();

                existingRole.setRoleAlias(rolesDTO.getRoleAlias());
                existingRole.setRoleName(rolesDTO.getRoleName());
                existingRole.setRemarks(rolesDTO.getRemarks());
                existingRole.setEditUser(currentUser);
                existingRole.setEditDate(ZonedDateTime.now());

                rolesRepo.save(existingRole);
                Map<String, Object> detail = new HashMap<>();
                detail.put("role", ConvertUtility.ConvertUtilityRoles(existingRole));

                return new ResponseDTO("Success", "M0000", "Role Updated", null, detail);
        }
        return new ResponseDTO("Success", "M0000", "No Role Found",null,null);
    }

    @Override
    public ResponseDTO deleteRole(Integer id) {
        Optional<Role> role = rolesRepo.findById(id);
        if (role.isPresent()) {
            rolesRepo.delete(role.get());
            Map<String, Object> detail = new HashMap<>();
            detail.put("role", ConvertUtility.ConvertUtilityRoles(role.get()));
            return new ResponseDTO("Success", "M0000", "Role Deleted", null, detail);
        }else {
            return new ResponseDTO("Success", "M0000", "No Role Found",null,null);
        }
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "";
    }
}
