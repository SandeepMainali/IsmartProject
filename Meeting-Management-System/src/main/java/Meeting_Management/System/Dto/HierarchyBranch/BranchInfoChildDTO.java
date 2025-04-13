package Meeting_Management.System.Dto.HierarchyBranch;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BranchInfoChildDTO {
    private Integer id;
    private Boolean isCorporate;
    private Integer parentId;
    private String brAlias;
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
    private String brZone;
    private String brZoneLocale;
    private String province;
    private String provinceLocale;
    private String country;
    private String countryLocale;
    private String zipCode;
    private Integer defCurId;
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
    private String brKey;
    private Boolean status;
    private String remarks;
    private ZonedDateTime insertDate;
    private Integer insertUser;
    private Integer editUser;
    private ZonedDateTime editDate;
}
