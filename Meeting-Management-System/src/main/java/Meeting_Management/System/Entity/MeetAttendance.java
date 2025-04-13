package Meeting_Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;


@Data
@Entity
@Table(name ="meet_attendance", schema ="minut")
public class MeetAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_id", nullable = false)
    private MeetMinut meetMinut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mint_member_id", nullable = false)
    private Member mintMember;

    @Column(name = "is_attendant")
    private Boolean isAttendant;

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
