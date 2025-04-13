package Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="members", schema ="minut")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = true)
    private String fullName;

    @Column(name = "full_name_locale", nullable = true)
    private String fullNameLocale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", nullable = true)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_type_id", nullable = true)
    private MemType memType;

    @Column(name = "mint_depart_id", nullable = true)
    private String mintDepartId;

    @Column(name = "mint_mem_deg_id", nullable = true)
    private String mintMemDegId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ethnicity_id", nullable = true)
    private EthnicCategory ethnicity;

    @Column(name = "is_backward_class", nullable = true)
    private Boolean isBackwardClass;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "mob_no", nullable = true)
    private String mobNo;

    @Column(name = "office_name", nullable = true)
    private String officeName;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "status", nullable = true)
    private Boolean status = true;

    @Column(name = "remarks", nullable = true)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insert_user", nullable = true)
    private User insertUser;

    @Column(name = "insert_date", nullable = true, updatable = true)
    private ZonedDateTime insertDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edit_user", nullable = true)
    private User editUser;

    @Column(name = "edit_date", nullable = true)
    private ZonedDateTime editDate;

    @Column(name= "insert_user_id", nullable = true)
    private Long insertUserId;
}