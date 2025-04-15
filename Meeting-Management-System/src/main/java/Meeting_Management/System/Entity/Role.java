package Meeting_Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="roles", schema ="admin")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_alias", length = 10, nullable = false, unique = true)
    private String roleAlias;

    @Column(name = "role_name", length = 100, nullable = false, unique = true)
    private String roleName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "insert_user")
    private String insertUser;

    @Column(name = "insert_date", nullable = false, updatable = false)
    private ZonedDateTime insertDate;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "edit_date")
    private ZonedDateTime editDate;
}