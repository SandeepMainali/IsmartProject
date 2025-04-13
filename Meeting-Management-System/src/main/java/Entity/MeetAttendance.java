package Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "meet_attendance", schema = "minut")
public class MeetAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_id", nullable = true)
    private MeetMinut meetMinut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mint_member_id", nullable = true)
    private Member mintMember;

    @Column(name = "is_attendant", nullable=true)
    private Boolean isAttendant;

    @Column(name = "status", nullable = true)
    private Boolean status = true;

    @Column(name = "remarks", nullable = true)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insert_user", nullable = true)
    private User insertUser;

    @Column(name = "insert_date", nullable = true, updatable = false)
    private ZonedDateTime insertDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edit_user", nullable = true)
    private User editUser;

    @Column(name = "edit_date", nullable = true)
    private ZonedDateTime editDate;
}
