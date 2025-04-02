package Dto;

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
    private Integer insertUser;
    private ZonedDateTime insertDate;
    private Integer editUser;
    private ZonedDateTime editDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleAlias() {
        return roleAlias;
    }

    public void setRoleAlias(String roleAlias) {
        this.roleAlias = roleAlias;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    public ZonedDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(ZonedDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getEditUser() {
        return editUser;
    }

    public void setEditUser(Integer editUser) {
        this.editUser = editUser;
    }

    public ZonedDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(ZonedDateTime editDate) {
        this.editDate = editDate;
    }
}
