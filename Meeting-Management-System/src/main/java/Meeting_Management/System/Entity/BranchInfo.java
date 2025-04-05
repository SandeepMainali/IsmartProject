package Meeting_Management.System.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name ="branch_infos", schema ="admin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_corporate", nullable = false)
    private Boolean isCorporate = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private BranchInfo parentId;

    @Column(name = "br_alias", length = 10, nullable = false, unique = true)
    private String brAlias;

    @Column(name = "full_name", length = 250, nullable = false, unique = true)
    private String fullName;

    @Column(name = "full_name_locale", length = 250, nullable = false, unique = true)
    private String fullNameLocale;

    @Column(name = "short_name", length = 50, nullable = false, unique = true)
    private String shortName;

    @Column(name = "street_name", length = 50, nullable = false)
    private String streetName;

    @Column(name = "street_name_locale", length = 50, nullable = false)
    private String streetNameLocale;

    @Column(name = "ward_no", length = 50, nullable = false)
    private String wardNo;

    @Column(name = "ward_no_locale", length = 50, nullable = false)
    private String wardNoLocale;

    @Column(name = "local_mnc", length = 50, nullable = false)
    private String localMnc;

    @Column(name = "local_mnc_locale", length = 50, nullable = false)
    private String localMncLocale;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "city_locale", length = 50, nullable = false)
    private String cityLocale;

    @Column(name = "district", length = 50, nullable = false)
    private String district;

    @Column(name = "district_locale", length = 50, nullable = false)
    private String districtLocale;

    @Column(name = "br_zone", length = 50, nullable = false)
    private String brZone;

    @Column(name = "br_zone_locale", length = 50, nullable = false)
    private String brZoneLocale;

    @Column(name = "province", length = 50, nullable = false)
    private String province;

    @Column(name = "province_locale", length = 50, nullable = false)
    private String provinceLocale;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "country_locale", length = 50, nullable = false)
    private String countryLocale;

    @Column(name = "zip_code", length = 50)
    private String zipCode;

    @Column(name = "def_cur_id")
    private Integer defCurId;

    @Column(name = "reg_no", length = 50, unique = true)
    private String regNo;

    @Column(name = "reg_no_locale", length = 50)
    private String regNoLocale;

    @Column(name = "reg_date", length = 50)
    private String regDate;

    @Column(name = "pan", length = 50, unique = true)
    private String pan;

    @Column(name = "pan_locale", length = 50)
    private String panLocale;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "phone_locale", length = 50)
    private String phoneLocale;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "contact_person", length = 50)
    private String contactPerson;

    @Column(name = "fax", length = 50)
    private String fax;

    @Column(name = "url", length = 50)
    private String url;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "show_locale_date")
    private Boolean showLocaleDate = true;

    @Column(name = "br_key")
    private String brKey;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "insert_date", nullable = false, updatable = false)
    private ZonedDateTime insertDate;

    @Column(name = "insert_user")
    private Integer insertUser;

    @Column(name = "edit_user")
    private Integer editUser;

    @Column(name = "edit_date")
    private ZonedDateTime editDate;



    public Boolean getIsCorporate() {
        return isCorporate;
    }

    public void setIsCorporate(Boolean isCorporate) {
        this.isCorporate = isCorporate;
    }

    public BranchInfo getParentId() {
        return parentId;
    }

    public void setParentId(BranchInfo parentId) {
        this.parentId = parentId;
    }

    public String getBrAlias() {
        return brAlias;
    }

    public void setBrAlias(String brAlias) {
        this.brAlias = brAlias;
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

    public String getBrZone() {
        return brZone;
    }

    public void setBrZone(String brZone) {
        this.brZone = brZone;
    }

    public String getBrZoneLocale() {
        return brZoneLocale;
    }

    public void setBrZoneLocale(String brZoneLocale) {
        this.brZoneLocale = brZoneLocale;
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

    public Integer getDefCurId() {
        return defCurId;
    }

    public void setDefCurId(Integer defCurId) {
        this.defCurId = defCurId;
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

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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

    public String getBrKey() {
        return brKey;
    }

    public void setBrKey(String brKey) {
        this.brKey = brKey;
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