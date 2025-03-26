package Meeting_Management.System.entity;

import jakarta.persistence.*;

import java.awt.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "menus", schema = "admin")
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_alias", length = 10, nullable = false, unique = true)
    private String menuAlias;

    @Column(name = "menu_name", length = 50, nullable = false, unique = true)
    private String menuName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_menu")
    private Menus parentMenu;

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

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuAlias() {
        return menuAlias;
    }

    public void setMenuAlias(String menuAlias) {
        this.menuAlias = menuAlias;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Menus getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menus parentMenu) {
        this.parentMenu = parentMenu;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMenuNameOther() {
        return menuNameOther;
    }

    public void setMenuNameOther(String menuNameOther) {
        this.menuNameOther = menuNameOther;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuController() {
        return menuController;
    }

    public void setMenuController(String menuController) {
        this.menuController = menuController;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getFaCode() {
        return faCode;
    }

    public void setFaCode(String faCode) {
        this.faCode = faCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    public ZonedDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(ZonedDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getEditUser() {
        return editUser;
    }

    public void setEditUser(Integer editUser) {
        this.editUser = editUser;
    }

    public ZonedDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(ZonedDateTime editDate) {
        this.editDate = editDate;
    }
}
