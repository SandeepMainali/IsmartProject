package Meeting_Management.System.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class RolesDTO {

    private Integer id;
    private String roleAlias;
    private String roleName;
    private String remarks;
    private String insertUser;
    private ZonedDateTime insertDate;
    private String editUser;
    private ZonedDateTime editDate;
}
