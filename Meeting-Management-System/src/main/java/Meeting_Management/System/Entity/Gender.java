package Meeting_Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="genders", schema ="kyc")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
}