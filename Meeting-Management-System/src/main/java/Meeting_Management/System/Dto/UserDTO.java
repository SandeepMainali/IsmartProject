package Meeting_Management.System.Dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserDTO {

    private Integer id;
    private Integer branchId;
    private Integer counterId;
    private Integer roleId;
    private String userName;
    private String passwordSalt;
    private String passwordHash;
    private String fullName;
    private String fullNameLocale;
    private String address;
    private Integer genderId;
    private String primaryContact;
    private String otherContact;
    private String email;
    private Boolean status;
    private ZonedDateTime deactiveDate;
    private String remarks;
    private Integer insertUser;
    private ZonedDateTime insertDate;
    private Integer editUser;
    private ZonedDateTime editDate;
}
