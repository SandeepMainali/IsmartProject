package Meeting_Management.System.Entity;
import jakarta.persistence.*;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="menus", schema ="admin")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_alias", length = 10, nullable = false, unique = true)
    private String menuAlias;

    @Column(name = "menu_name", length = 50, nullable = false, unique = true)
    private String menuName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_menu")
    private Menu parentMenu;

    @Column(name = "menu_type", length = 5, nullable = false)
    private String menuType;

    @Column(name = "version", length = 5, nullable = false)
    private String version;

    @Column(name = "menu_name_other", length = 50, nullable = false, unique = true)
    private String menuNameOther;

    @Column(name = "menu_url", length = 200)
    private String menuUrl;

    @Column(name = "menu_controller", length = 50)
    private String menuController;

    @Column(name = "menu_action", length = 50)
    private String menuAction;

    @Column(name = "display_index")
    private Integer displayIndex;

    @Column(name = "fa_code", length = 50)
    private String faCode;

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
