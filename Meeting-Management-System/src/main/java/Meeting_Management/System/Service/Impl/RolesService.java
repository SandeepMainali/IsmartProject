package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Dto.RolesDTO;

public interface RolesService {
    ResponseDTO getAllRoles();

    ResponseDTO getRoleById(Integer id);

    ResponseDTO createRole(RolesDTO rolesDTO);

    ResponseDTO updateRole(RolesDTO rolesDTO);

    ResponseDTO deleteRole(Integer id);
}
