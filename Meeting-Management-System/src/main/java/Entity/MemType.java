package Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="mem_types", schema ="minut")
public class MemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mem_type_name", nullable = false)
    private String memTypeName;

    @Column(name = "mem_type_locale", nullable = false)
    private String memTypeLocale;

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