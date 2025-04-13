package Meeting_Management.System.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDTO {
    private Long id;
    private Integer branchInfoId;
    private String officeName;
    private String officeLocale;
    private String description;
    private Boolean status = true;
    private String remarks;
    private String insertUser;
    private ZonedDateTime insertDate;
    private String editUser;
    private ZonedDateTime editDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBranchInfoId() {
        return branchInfoId;
    }

    public void setBranchInfoId(Integer branchInfoId) {
        this.branchInfoId = branchInfoId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeLocale() {
        return officeLocale;
    }

    public void setOfficeLocale(String officeLocale) {
        this.officeLocale = officeLocale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(String insertUserId) {
        this.insertUser = insertUserId;
    }

    public ZonedDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(ZonedDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUserId) {
        this.editUser = editUserId;
    }

    public ZonedDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(ZonedDateTime editDate) {
        this.editDate = editDate;
    }
}
