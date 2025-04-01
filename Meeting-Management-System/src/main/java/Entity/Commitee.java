package Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "commitee", schema = "minut")
public class Commitee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commitee_type_id", nullable = false)
    private CommiteeType commiteeType;

    @Column(name = "committee_name", nullable = false)
    private String committeeName;

    @Column(name = "committee_locale", nullable = false)
    private String committeeLocale;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "nick_name_locale")
    private String nickNameLocale;

    @Column(name = "est_date")
    private LocalDate estDate;

    @Column(name = "est_body")
    private String estBody;

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