package Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "members", schema = "minut")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "full_name_locale", nullable = false)
    private String fullNameLocale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_type_id", nullable = false)
    private MemType memType;

    @Column(name = "mint_depart_id")
    private String mintDepartId;

    @Column(name = "mint_mem_deg_id")
    private String mintMemDegId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ethinicity_id")
    private EthnicCategory ethinicity;

    @Column(name = "is_backward_class")
    private Boolean isBackwardClass;

    @Column(name = "email")
    private String email;

    @Column(name = "mob_no")
    private String mobNo;

    @Column(name = "office_name")
    private String officeName;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insert_user", nullable = false)
    private User insertUser;

    @Column(name = "insert_date", nullable = false, updatable = false)
    private ZonedDateTime insertDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edit_user")
    private User editUser;

    @Column(name = "edit_date")
    private ZonedDateTime editDate;
}