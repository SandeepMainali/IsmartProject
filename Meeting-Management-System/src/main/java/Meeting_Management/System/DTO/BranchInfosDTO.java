package Meeting_Management.System.DTO;

import Meeting_Management.System.entity.BranchInfos;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BranchInfosDTO {

    private Integer id;
    private Boolean isCorporate;
    private BranchInfos parentBranch;
    private String branchAlias;
    private String fullName;
    private String fullNameLocale;
    private String shortName;
    private String streetName;
    private String streetNameLocale;
    private String wardNo;
    private String wardNoLocale;
    private String localMnc;
    private String localMncLocale;
    private String city;
    private String cityLocale;
    private String district;
    private String districtLocale;
    private String branchZone;
    private String branchZoneLocale;
    private String province;
    private String provinceLocale;
    private String country;
    private String countryLocale;
    private String zipCode;
    private Integer defaultCurrencyId;
    private String regNo;
    private String regNoLocale;
    private String regDate;
    private String pan;
    private String panLocale;
    private String phone;
    private String phoneLocale;
    private String email;
    private String contactPerson;
    private String fax;
    private String url;
    private String timeZone;
    private Boolean showLocaleDate;
    private String branchKey;
    private Boolean status;
    private String remarks;
    private ZonedDateTime insertDate;
    private Integer insertUser;
    private Integer editUser;
    private ZonedDateTime editDate;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCorporate() {
        return isCorporate;
    }

    public void setCorporate(Boolean corporate) {
        isCorporate = corporate;
    }

    public BranchInfos getParentBranch() {
        return parentBranch;
    }

    public void setParentBranch(BranchInfos parentBranch) {
        this.parentBranch = parentBranch;
    }

    public String getBranchAlias() {
        return branchAlias;
    }

    public void setBranchAlias(String branchAlias) {
        this.branchAlias = branchAlias;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNameLocale() {
        return streetNameLocale;
    }

    public void setStreetNameLocale(String streetNameLocale) {
        this.streetNameLocale = streetNameLocale;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public String getWardNoLocale() {
        return wardNoLocale;
    }

    public void setWardNoLocale(String wardNoLocale) {
        this.wardNoLocale = wardNoLocale;
    }

    public String getLocalMnc() {
        return localMnc;
    }

    public void setLocalMnc(String localMnc) {
        this.localMnc = localMnc;
    }

    public String getLocalMncLocale() {
        return localMncLocale;
    }

    public void setLocalMncLocale(String localMncLocale) {
        this.localMncLocale = localMncLocale;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityLocale() {
        return cityLocale;
    }

    public void setCityLocale(String cityLocale) {
        this.cityLocale = cityLocale;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictLocale() {
        return districtLocale;
    }

    public void setDistrictLocale(String districtLocale) {
        this.districtLocale = districtLocale;
    }

    public String getBranchZone() {
        return branchZone;
    }

    public void setBranchZone(String branchZone) {
        this.branchZone = branchZone;
    }

    public String getBranchZoneLocale() {
        return branchZoneLocale;
    }

    public void setBranchZoneLocale(String branchZoneLocale) {
        this.branchZoneLocale = branchZoneLocale;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceLocale() {
        return provinceLocale;
    }

    public void setProvinceLocale(String provinceLocale) {
        this.provinceLocale = provinceLocale;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryLocale() {
        return countryLocale;
    }

    public void setCountryLocale(String countryLocale) {
        this.countryLocale = countryLocale;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getDefaultCurrencyId() {
        return defaultCurrencyId;
    }

    public void setDefaultCurrencyId(Integer defaultCurrencyId) {
        this.defaultCurrencyId = defaultCurrencyId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNoLocale() {
        return regNoLocale;
    }

    public void setRegNoLocale(String regNoLocale) {
        this.regNoLocale = regNoLocale;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanLocale() {
        return panLocale;
    }

    public void setPanLocale(String panLocale) {
        this.panLocale = panLocale;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneLocale() {
        return phoneLocale;
    }

    public void setPhoneLocale(String phoneLocale) {
        this.phoneLocale = phoneLocale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getShowLocaleDate() {
        return showLocaleDate;
    }

    public void setShowLocaleDate(Boolean showLocaleDate) {
        this.showLocaleDate = showLocaleDate;
    }

    public String getBranchKey() {
        return branchKey;
    }

    public void setBranchKey(String branchKey) {
        this.branchKey = branchKey;
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

    public ZonedDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(ZonedDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
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
