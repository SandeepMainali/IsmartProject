package Meeting_Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="users", schema ="admin")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "br_id", nullable = false)
    private BranchInfo branchInfo;

    @Column(name = "counter_id")
    private Integer counterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "user_name", length = 100, nullable = false, unique = true)
    private String userName;

    @Column(name = "password_salt")
    private String passwordSalt;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "full_name", length = 100, nullable = false, unique = true)
    private String fullName;

    @Column(name = "full_name_locale", length = 100, nullable = false, unique = true)
    private String fullNameLocale;

    @Column(name = "address", length = 100)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @Column(name = "p_contact", length = 10, nullable = false)
    private String pContact;

    @Column(name = "other_contact", length = 100, nullable = false)
    private String otherContact;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "deactive_date")
    private ZonedDateTime deactiveDate;

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
