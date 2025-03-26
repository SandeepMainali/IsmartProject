package Meeting_Management.System.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "genders", schema = "kyc")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "gender_alias", length = 10, nullable = false, unique = true)
    private String genderAlias;

    @Column(name = "gender_name", length = 20, nullable = false, unique = true)
    private String genderName;

    @Column(name = "gender_alias_locale", length = 20, nullable = false, unique = true)
    private String genderAliasLocale;

    @Column(name = "gender_name_locale", length = 50, nullable = false, unique = true)
    private String genderNameLocale;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "insert_user")
    private Integer insertUser;

    @Column(name = "insert_date", nullable = false, updatable = false)
    private ZonedDateTime insertDate;

    @Column(name = "edit_user")
    private Integer editUser;

    @Column(name = "edit_date")
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
