package Meeting_Management.System.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="branch_infos", schema ="admin")
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

    @Column(name = "reg_no", length = 50)
    private String regNo;

    @Column(name = "reg_no_locale", length = 50)
    private String regNoLocale;

    @Column(name = "reg_date", length = 50)
    private String regDate;

    @Column(name = "pan", length = 50)
    private String pan;

    @Column(name = "pan_locale", length = 50)
    private String panLocale;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "phone_locale", length = 50)
    private String phoneLocale;

    @Column(name = "email", length = 50)
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
}