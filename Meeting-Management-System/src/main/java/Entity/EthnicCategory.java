package Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="ethnic_category", schema ="kyc")
public class EthnicCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "ethnic_name", length = 50, nullable = false, unique = true)
    private String ethnicName;

    @Column(name = "ehnic_alias", length = 20, nullable = false)
    private String ehnicAlias;

    @Column(name = "ethnic_locale", length = 50, nullable = false, unique = true)
    private String ethnicLocale;

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