package Meeting_Management.System.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class GendersDTO {
    private Short id;
    private String genderAlias;
    private String genderName;
    private String genderAliasLocale;
    private String genderNameLocale;
    private String remarks;
    private Integer insertUser;
    private ZonedDateTime insertDate;
    private Integer editUser;
    private ZonedDateTime editDate;

    // Getters and setters

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getGenderAlias() {
        return genderAlias;
    }

    public void setGenderAlias(String genderAlias) {
        this.genderAlias = genderAlias;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderAliasLocale() {
        return genderAliasLocale;
    }

    public void setGenderAliasLocale(String genderAliasLocale) {
        this.genderAliasLocale = genderAliasLocale;
    }

    public String getGenderNameLocale() {
        return genderNameLocale;
    }

    public void setGenderNameLocale(String genderNameLocale) {
        this.genderNameLocale = genderNameLocale;
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
