package Meeting_Management.System.Controller;

import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Dto.RolesDTO;
import Meeting_Management.System.Service.Impl.RolesService;
import Meeting_Management.System.Service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RolesService rolesService;

    @Autowired
    public RoleController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> getRoles(){
        try{
            ResponseDTO responseDTO = rolesService.getAllRoles();
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO("Error","E5000", "Something Went Wrong", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getRoleById(@PathVariable Integer id){
        if(rolesService.getRoleById(id) != null){
            return new ResponseEntity<>(rolesService.getRoleById(id), HttpStatus.OK);
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Error","E5000", "Role Not Found", null, null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createRole(@RequestBody RolesDTO rolesDTO){
        if (rolesService.createRole(rolesDTO) != null){
            return new ResponseEntity<>(rolesService.createRole(rolesDTO), HttpStatus.CREATED);
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Error","E5000", "Failed To Create Role", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateRole(@RequestBody RolesDTO rolesDTO){
        if (rolesService.updateRole(rolesDTO) != null){
            return new ResponseEntity<>(rolesService.updateRole(rolesDTO), HttpStatus.OK);
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Error","E5000", "Failed to Update Role", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteRole(@PathVariable Integer id){
        if (rolesService.deleteRole(id) != null){
            return new ResponseEntity<>(rolesService.deleteRole(id), HttpStatus.OK);
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Error","E5000", "Failed to Delete Role", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
