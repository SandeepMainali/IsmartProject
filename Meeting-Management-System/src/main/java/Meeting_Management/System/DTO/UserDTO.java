package Meeting_Management.System.DTO;

import Meeting_Management.System.entity.BranchInfos;
import Meeting_Management.System.entity.Gender;
import Meeting_Management.System.entity.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserDTO {

    private Integer id;
    private BranchInfos branch;
    private Integer counterId;
    private Roles role;
    private String userName;
    private String passwordSalt;
    private String passwordHash;
    private String fullName;
    private String fullNameLocale;
    private String address;
    private Gender gender;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BranchInfos getBranch() {
        return branch;
    }

    public void setBranch(BranchInfos branch) {
        this.branch = branch;
    }

    public Integer getCounterId() {
        return counterId;
    }

    public void setCounterId(Integer counterId) {
        this.counterId = counterId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameLocale() {
        return fullNameLocale;
    }

    public void setFullNameLocale(String fullNameLocale) {
        this.fullNameLocale = fullNameLocale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ZonedDateTime getDeactiveDate() {
        return deactiveDate;
    }

    public void setDeactiveDate(ZonedDateTime deactiveDate) {
        this.deactiveDate = deactiveDate;
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
