package Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "meet_minuts", schema = "minut")
public class MeetMinut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mint_type_id", nullable = false)
    private MinutType mintType;

    @Column(name = "fin_year")
    private String finYear;

    @Column(name = "meet_count")
    private Integer meetCount;

    @Column(name = "meet_date")
    private LocalDate meetDate;

    @Column(name = "time_from", nullable = false)
    private LocalTime timeFrom;

    @Column(name = "time_to", nullable = false)
    private LocalTime timeTo;

    @Column(name = "meet_place")
    private String meetPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chair_person", nullable = false)
    private Member chairPerson;

    @Column(name = "propositions")
    private String propositions;

    @Column(name = "decisions")
    private String decisions;

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
